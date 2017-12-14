

var myvar = '<li id="dbId" class = "userNames" ><a href="#">name</a> </li>';

$(document).ready(function(){
        $.ajax({
                url: "/names",
                success: function(data){
                    var $container = $("#listContainer");
                    for(var i=0; i < data.length;i++){
                        if(i==0){
                            var information = data[0][0].candidateInformations;
                            var scoreForEachSkill = [];
                            var skillList = [];
                            for(var j =0;j<information.length;j++){
                                scoreForEachSkill.push(information[j].score);
                                skillList.push(information[j].skill.skillName);
                            }
                            $('#name').text(data[0][0].candidateName);
                            $('#From').text(data[0][0].location);
                            $('#score').text(data[0][1]);
                            $('#resume').attr('src',data[0][0].resumePath);
                            google.charts.load('current', {'packages':['corechart', 'bar']});
                            google.charts.setOnLoadCallback(function() {
                                   drawChart(scoreForEachSkill,skillList);
                            });
                        }
                        var candidate = data[i][0];
                        var replacedName = myvar.replace(/name/g,candidate.candidateName);
                        var replacedId = replacedName.replace(/dbId/g, candidate.candidateId);
                        $container.append(replacedId);
                    }
                    addEventListner();
                }

         });
});
function addEventListner(){
    $('#listContainer li').click(function(){
        getCandidateProfile(this.id);
    }
)};

function getCandidateProfile(userId){
     $.ajax({
               type:'GET',
               url: '/getCandidateInfo?candidate_id=' + userId
               ,success: function(result){
                       $('#name').text(result.candidateName);
                       $('#From').text(result.location);
                       $('#resume').attr('src',result.resumePath);
                       var totalScore = 0;
                       var skillList = [];
                       var scoreForEachSkill = [];
                       for(var i =0;i< result.candidateInformations.length;i++)
                        {
                              totalScore += result.candidateInformations[i].score;
                              scoreForEachSkill.push(result.candidateInformations[i].score);
                              skillList.push(result.candidateInformations[i].skill.skillName);
                        }
                        $('#score').text(totalScore);
                        google.charts.load('current', {'packages':['corechart', 'bar']});
                        google.charts.setOnLoadCallback(function() {
                               drawChart(scoreForEachSkill,skillList);
                        });
               }
     });

     $("#resume").attr('src', "/getResume/"+userId);
}

function drawChart(scoreForEachSkill, skillList) {
    var data = new google.visualization.DataTable();

    data.addColumn('string','Skill');
    data.addColumn('number','Score');
    data.addColumn({type: 'string',role: 'style'});
    var colorss = ["#0000FF","green","#BCBCBC"];
    for(var i=0;i< scoreForEachSkill.length;i++)
    {
        data.addRow([skillList[i],scoreForEachSkill[i],colorss[i]]);
    }
    var options = {'title':'Skills', 'width':610, 'height':390,legend: {position: 'none'}};

    var chart = new google.visualization.ColumnChart(document.getElementById('pieChart'));
    chart.draw(data, options);
}





