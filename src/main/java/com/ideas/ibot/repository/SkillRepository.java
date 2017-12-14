package com.ideas.ibot.repository;

import com.ideas.ibot.entity.Candidate;
import com.ideas.ibot.entity.Skill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SkillRepository extends CrudRepository<Skill, Long> {

    @Query(value = "select skill_name from skill", nativeQuery = true)
    List<String> getSkills();

    Skill findBySkillName(String key);
}
