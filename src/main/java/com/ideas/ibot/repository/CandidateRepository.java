package com.ideas.ibot.repository;

import javafx.util.Pair;
import com.ideas.ibot.entity.Candidate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CandidateRepository extends CrudRepository<Candidate, Long> {
    List<Candidate> findByExperience(int exp);

    List<Candidate> findByCandidateName(String name);

    List<Candidate> findAllByOrderByCandidateInformations_Score();

    Candidate findByCandidateId(long id);

    @Query("select c,sum(ci.score) AS score from Candidate c,CandidateInformation ci" +
            " where c.candidateId = ci.candidate.candidateId " +
           " group by c.candidateName order by sum(ci.score) desc")
    List<Candidate> findAllOrderByCandidateInformation_score();
}
