package com.ideas.ibot.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by idnutv on 10/24/2017.
 */
@Entity
@Table(name = "SaveQuery")
public class SaveQuery
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String requirement;
    private int experience;
    private String status;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private Timestamp timeStamp;

    public SaveQuery() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public SaveQuery(String requirement, int experience, Timestamp timeStamp) {
        this.requirement = requirement;
        this.experience = experience;
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
