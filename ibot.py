import math
import glob
import errno
import re
import nltk, string
from nltk.corpus import stopwords
stop = stopwords.words('english')
from nltk import ne_chunk
from nltk import word_tokenize, pos_tag, ne_chunk
import os
import bisect 
from word2number import w2n
import MySQLdb as mysql
from operator import itemgetter
from socket import *
import json
import datetime
import PyPDF2



stemmer = nltk.stem.porter.PorterStemmer()
remove_punctuation_map = dict((ord(char), " ") for char in string.punctuation)

def calculatgeTermFrequency(term, normalizeDocument):  
    if(float(len(normalizeDocument)) != 0):
        return normalizeDocument.count(term.lower()) / float(len(normalizeDocument))
    else:
        return 0;
    

def inverseDocumentFrequency(term, allDocuments):
    numDocumentsWithThisTerm = 0
    for normalizeDocument in allDocuments:        
        if term.lower() in normalizeDocument:
            numDocumentsWithThisTerm = numDocumentsWithThisTerm + 1
 
    if numDocumentsWithThisTerm > 0:
        return 1.0 + math.log(float(len(allDocuments)) / numDocumentsWithThisTerm)
    else:
        return 1.0  

def stem_tokens(tokens):
    return [stemmer.stem(item) for item in tokens]

'''remove punctuation, lowercase, stem'''
def normalize(text):
    text = ' '.join([i.lower() for i in text.split() if i not in stop])
    stem=stem_tokens(nltk.word_tokenize(text.lower().translate(remove_punctuation_map)))
    return stem

def calculateTfIdf(folderPath,query):   
    files = glob.glob(folderPath)
    documents=[]
    frequency={}
    searchTerm=normalize(query)
    for name in files:
        print(name)
        try:
             with open(name,'rb') as f:
                resume = ""
                pdfReader = PyPDF2.PdfFileReader(f)
                for i in range(0,pdfReader.numPages):
                    pageObj = pdfReader.getPage(i)
                    resume = resume + pageObj.extractText()
                # resume=f.read()
                frequency[name] = {}
                             
                for term in searchTerm: 
                    normalizeData=normalize(resume)              
                    documents.append(normalizeData)
                    frequency[name][term]= calculatgeTermFrequency(term,normalizeData)
                expr=extract_experience(resume)                
                frequency[name]['experience']=expr     

        except IOError as exc:
            if exc.errno != errno.EISDIR:
                raise    
    termMap={}            
    for term in searchTerm:           
        termMap[term] = inverseDocumentFrequency(term,documents)  
    
    #calculted tf*idf
    for fileName in frequency.keys():  
        for term in frequency[fileName].keys():
            if term!='experience':
                frequency[fileName][term] = frequency[fileName][term] *  termMap[term]      
        

    return frequency

def calculateTfIdfForQuery(query):
    queryFrequency={}
    termMap={}  
    normaliseData=normalize(query)
    for term in normaliseData:
        queryFrequency[term]= calculatgeTermFrequency(term,normaliseData)
        termMap[term] = inverseDocumentFrequency(term,[normaliseData])
    #calculted tf*idf
    for key in queryFrequency:     
        queryFrequency[key] = queryFrequency[key] *  termMap[key]  

    return queryFrequency    


def cosine_sim(vector1,vector2,requiredExperience):
    dot=0
    sqrQuery=0
    sqrDoc=0
    cosine_sim=0
    sqrt=0
    cosineForTerm={}
    for term in vector1.keys():
        dot+=vector1[term]*vector2[term]
        sqrQuery+=vector1[term]*vector1[term]
        sqrDoc+=vector2[term]*vector2[term]
    sqrt=math.sqrt(sqrQuery)*math.sqrt(sqrDoc)
  
    counter =0
    if(sqrt>0):
        cosine_sim=dot/sqrt
        for term in vector1.keys():                      
            cosineForTerm[term]=(vector1[term]*vector2[term])/sqrt    
        experience = vector2["experience"]
        scoreForExperience = (experience - requiredExperience)*0.01
        if cosine_sim + scoreForExperience > 100:
            cosineForTerm["total"] = 100
        else:
            cosineForTerm["total"]= cosine_sim + scoreForExperience
        cosineForTerm["experience"] = scoreForExperience
    return cosineForTerm  

def findResumeFromDirectoryWithCriteria(dirPath,query,queryExp,requiredExperience):
    data=[]
    resumeTfIdf=calculateTfIdf(dirPath,query)
  
    queryTfidf=calculateTfIdfForQuery(query)
    
    
    for fileName in resumeTfIdf.keys():   
        try:
            cosine_value={}
            values=cosine_sim(queryTfidf,resumeTfIdf[fileName],requiredExperience)
            for term in values.keys():
                cosine_value[term]=values[term]*100
            cosine_value["fileName"] = fileName
            print(fileName)
            if(len(values)>0):
                data.append(cosine_value)
                
        except IOError as exc:
            if exc.errno != errno.EISDIR:
                raise
    data = sorted(data, key=itemgetter('total'), reverse = True)   
    return data;

def ie_preprocess(document):
    document = ' '.join([i.lower() for i in document.split() if i not in stop])
    sentences = nltk.sent_tokenize(document)
    sentences = [nltk.word_tokenize(sent) for sent in sentences]
    sentences = [nltk.pos_tag(sent) for sent in sentences]
    return sentences


def extract_experience(document):
    expr=[]
    experience = []
    grammar = "EXPERIENCE: {<CD><NNS|NN><RB|JJ>|<RB|JJ><CD><NNS>|<NN|MD|JJ><CD><NN|MD|JJ><CD>|<CD><NN|JJ|MD><CD><CD><NN|JJ|MD><CD>}" 
    cp = nltk.RegexpParser(grammar)
    print("insideExperience")
    for tagged_sentence in ie_preprocess(document): 
        result = cp.parse(nltk.ne_chunk(tagged_sentence))   
        for chunk in result.subtrees(filter=lambda t: t.label() == 'EXPERIENCE' and type(t) == nltk.tree.Tree):
            leaf_values = chunk.leaves()
            counter = 0
            for leaf in leaf_values:
                if leaf[1] == 'CD': 
                    result = re.findall(r'\d+', leaf[0])
                    result = str(''.join(result))
                    if result != "":
                        result = int(result)                                     
                        if result is None:
                            counter = 0
                        elif int(result) < 1900:
                            counter = 1
                        elif counter == 1:
                            counter = 0
                        else:
                            expr.append((leaf[0]))                            
  
    if len(expr)==0:
        return 0
    for exp in expr:
        if re.findall("[0-9]{4}",exp):
            experience.append(exp)
    experience.sort()
    print(experience)
    now = datetime.datetime.now()
    currentYear = now.year
    actualExperience = currentYear - int(experience[0])          
    return actualExperience


def insertIntoDatabase(finalList):
    conn = mysql.connect(host = "localhost",
    port = 3306,
    user = "root",
    passwd = "root",
    db = "candidateinfo")
    cursor = conn.cursor()
    counter=0

    for entry in finalList: 
        print("""INSERT INTO python_integration(resume_path,score) VALUES  (%s,%s)""", (entry["fileName"],str(entry)))
        cursor.execute("""INSERT INTO python_integration(resume_path,score) VALUES  (%s,%s)""", (entry["fileName"],str(entry)))  
        print("Inserted")
        conn.commit()
        counter+=1;


connection = mysql.connect(host = "localhost",
    port = 3306,
    user = "root",
    passwd = "root",
    db = "candidateinfo")
cursor = connection.cursor()
cursor.execute("SELECT experience,requirement,path,status FROM save_query ORDER BY id DESC LIMIT 1 ")
rows =  cursor.fetchall()
print(rows)
requiredExperience = rows[0][0]
fetchedQuery = rows[0][1]
requiredPath = rows[0][2]
requiredStatus = rows[0][3]
if requiredStatus == "FETCHING_COMPLETED":    
    fetchedQuery = fetchedQuery.split()
    query = ','.join(fetchedQuery)  
    path= requiredPath + "\\*.pdf"

    candidateList=findResumeFromDirectoryWithCriteria(path,query,1,requiredExperience)
    threshold = 60
    finalCandidateList=[]
    for candidate in candidateList:
        if candidate["total"] > threshold:
            finalCandidateList.append(candidate)
        
    insertIntoDatabase(finalCandidateList)
else:
    print("hello moto")