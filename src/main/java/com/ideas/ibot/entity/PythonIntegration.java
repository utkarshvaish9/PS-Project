package com.ideas.ibot.entity;

import javax.persistence.*;

/**
 * Created by idnutv on 10/5/2017.
 */

@Entity
@Table(name="python_integration")
public class PythonIntegration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "resumePath")
    private String resumePath;
    private String score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
