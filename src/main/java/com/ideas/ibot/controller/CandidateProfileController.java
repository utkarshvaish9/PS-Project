package com.ideas.ibot.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ideas.ibot.entity.*;
import com.ideas.ibot.repository.CandidateRepository;
import com.ideas.ibot.repository.PythonIntegrationRepository;
import com.ideas.ibot.repository.SaveQueryRepository;
import com.ideas.ibot.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

import static com.ideas.ibot.Selenium.GetResumes.fetchResumesFromIndeed;

@Controller
public class CandidateProfileController {

    public static final String FETCHING_STARTED = "FETCHING_STARTED";
    public static final String FETCHING_COMPLETED = "FETCHING_COMPLETED";
    public static final String baseAddress = "C:\\Users\\idnutv\\Downloads\\allResumes";
    public static final String EXTRACTION_COMPLETED = "EXTRACTION COMPLETED";
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    PythonIntegrationRepository pythonIntegrationRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    SaveQueryRepository saveQueryRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        model.addAttribute("message", "Spring 3 MVC Hello World");
        return "candidateProfile1";

    }

    @RequestMapping(value = "/candidateProfile/{name}", method = RequestMethod.GET, produces = "text/html")
    public String hello(Model model, @PathVariable("name") String name) {
        List<PythonIntegration> allScores = (List<PythonIntegration>) pythonIntegrationRepository.findAll();
        List<String> allSkills = (List<String>) skillRepository.getSkills();

        JsonParser parser = new JsonParser();
        List<String> presentSkills = new ArrayList<>();
        allScores.stream().forEach(p -> {
            JsonElement parseScore = parser.parse(p.getScore());
            Candidate candidate = new Candidate();
            candidate.setCandidateName("Candidate" + p.getId());
            candidate.setExperience(1);
            candidate.setPhoneNo(Math.pow(2, 3) + 100);
            candidate.setLocation("Delhi");
            candidate.setResumePath(p.getResumePath());
            Set<CandidateInformation> candidateInformations = new HashSet<>();
            ((JsonObject) parseScore).entrySet().stream().forEach(s -> {
                if ((!s.getKey().equals("fileName")) && !s.getKey().equals("total")) {
                    CandidateInformation candidateInformation = new CandidateInformation();
                    candidateInformation.setCandidate(candidate);
                    if (!(allSkills.contains(s.getKey()))) {
                        allSkills.add(s.getKey());
                        Skill skill = new Skill();
                        skill.setSkillName(s.getKey());
                        skill.setType("Mandatory");
                        candidateInformation.setSkill(skill);
                    } else {
                        Skill skillByName = (Skill) skillRepository.findBySkillName(s.getKey());
                        candidateInformation.setSkill(skillByName);
                    }
                    candidateInformation.setScore(Float.valueOf(s.getValue().toString()));
                    candidateInformations.add(candidateInformation);
                }

            });
            candidate.setCandidateInformations(candidateInformations);
            candidateRepository.save(candidate);
        });
        model.addAttribute("msg", name);
        pythonIntegrationRepository.deleteAll();
        return "candidateProfile";

    }

    @RequestMapping(value = "/something", method = RequestMethod.GET)
    public String hello2(Model model, @RequestParam("catagory") String catagory, @RequestParam("experience") String
            experience, @RequestParam("dropdown") String department) {

        SaveQuery saveQuery = new SaveQuery(catagory, Integer.parseInt(experience), new Timestamp(System.currentTimeMillis()));
        saveQuery.setStatus(FETCHING_STARTED);
        saveQuery = saveQueryRepository.save(saveQuery);
        fetchResumesFromIndeed(catagory, department);
        saveQuery.setPath(baseAddress + "\\" + department + "\\" + catagory);
        saveQuery.setStatus(FETCHING_COMPLETED);
        saveQueryRepository.save(saveQuery);
        return "something";
    }

    @RequestMapping(value = "/getCategory", method = RequestMethod.GET)
    public String getCatogory(Model model) {
        return "getCategory";
    }


}