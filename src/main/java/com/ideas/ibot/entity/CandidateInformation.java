package com.ideas.ibot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "candidateInformation")
public class CandidateInformation {
    private Long candidateInformationId;
    private Candidate candidate;
    private Skill skill;
    private Float score;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="candidate_id",nullable = false)
    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="candidate_informations_id")
    public Long getCandidateInformationId() {
        return this.candidateInformationId;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public void setCandidateInformationId(Long candidateInformationId) {
        this.candidateInformationId = candidateInformationId;
    }


    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    @JoinColumn(name="skill_id")
    public Skill getSkill() {
        return this.skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}
