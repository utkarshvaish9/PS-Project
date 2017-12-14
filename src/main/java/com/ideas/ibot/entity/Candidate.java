package com.ideas.ibot.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "candidate")
public class Candidate {

    private Long candidateId;
    private String candidateName;
    private double phoneNo;
    private int experience;
    private String location;
    private String resumePath;
    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }





    private Set<CandidateInformation> candidateInformations;

    @OneToMany(mappedBy = "candidate",cascade={CascadeType.ALL})
    public Set<CandidateInformation> getCandidateInformations() {
        return this.candidateInformations;
    }

    public void setCandidateInformations(Set<CandidateInformation> candidateInformations) {
        this.candidateInformations = candidateInformations;
    }

    public Set<CandidateInformation> addCandidateInformation(CandidateInformation candidateInformation){
        if (candidateInformations == null){
            candidateInformations = new HashSet<>();
        }
        candidateInformation.setCandidate(this);
        candidateInformations.add(candidateInformation);
        return candidateInformations;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="candidate_id")
    public Long getCandidateId() {
        return this.candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public double getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(double phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

}
