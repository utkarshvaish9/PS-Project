package com.ideas.ibot.service;

import com.ideas.ibot.entity.Candidate;
import com.ideas.ibot.entity.CandidateInformation;
import com.ideas.ibot.entity.PythonIntegration;
import com.ideas.ibot.repository.CandidateInformationRepository;
import com.ideas.ibot.repository.CandidateRepository;
import com.ideas.ibot.repository.PythonIntegrationRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@RestController
public class TestService {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    CandidateInformationRepository candidateInformationRepository;

    @Autowired
    PythonIntegrationRepository pythonIntegrationRepository;

   @RequestMapping(value = "/candidates", method = RequestMethod.GET)
    public List<Candidate> grtAll() {
        List<Candidate> candidates = (List<Candidate>) candidateRepository.findAll();
        return candidates;
    }
    @RequestMapping(value = "/names",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Candidate> getAllNamesSorted()
    {
        List<Candidate> allCandidate = candidateRepository.findAllOrderByCandidateInformation_score();
        return allCandidate;
    }
    @RequestMapping(value = "/getCandidateInfo",method = RequestMethod.GET)
    public Candidate getAllInfo(@RequestParam("candidate_id") long id)
    {
        Candidate candidate = candidateRepository.findByCandidateId(id);
        return candidate;
    }
    @RequestMapping(value = "/getAllScores",method = RequestMethod.GET)
    public List<PythonIntegration> getAllScores()
    {
        return (List<PythonIntegration>) pythonIntegrationRepository.findAll();
    }

    @RequestMapping(value="/getResume/{userId}")
    private ResponseEntity<InputStreamResource> getResume(@PathVariable("userId") Long userId, HttpServletResponse response) throws IOException {
        response.setHeader("MediaType","application/pdf");
        ByteArrayInputStream fileByteArray = getResumeByteArray(userId);
        return ResponseEntity.ok()
                .header("Content-Disposition", "inline; filename=citiesreport.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(fileByteArray));
    }

    private ByteArrayInputStream getResumeByteArray(Long userId) throws IOException {
        Candidate candidate = candidateRepository.findByCandidateId(userId);
        File file = new File(candidate.getResumePath());
        return new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
    }

}
