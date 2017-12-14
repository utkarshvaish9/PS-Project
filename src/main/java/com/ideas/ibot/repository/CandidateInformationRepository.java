package com.ideas.ibot.repository;

import com.ideas.ibot.entity.Candidate;
import com.ideas.ibot.entity.CandidateInformation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by idnutv on 9/12/2017.
 */
public interface CandidateInformationRepository extends CrudRepository<CandidateInformation,Long> {

    List<CandidateInformation> findByCandidate(Candidate candidate);

}
