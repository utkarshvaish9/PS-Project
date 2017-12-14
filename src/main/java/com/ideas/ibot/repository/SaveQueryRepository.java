package com.ideas.ibot.repository;

import com.ideas.ibot.entity.Candidate;
import com.ideas.ibot.entity.SaveQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by idnutv on 10/24/2017.
 */
public interface SaveQueryRepository extends CrudRepository<SaveQuery, Integer>{



}
