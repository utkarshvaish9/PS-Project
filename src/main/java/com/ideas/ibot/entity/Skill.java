package com.ideas.ibot.entity;

import javax.persistence.*;

@Entity
@Table(name="skill")
public class Skill {

    private Long skillId;


    private String skillName;
    private String type;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="skill_id")
    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }
    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}