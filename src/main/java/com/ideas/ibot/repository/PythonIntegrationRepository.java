package com.ideas.ibot.repository;

import com.ideas.ibot.entity.PythonIntegration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by idnutv on 10/5/2017.
 */
public interface PythonIntegrationRepository extends CrudRepository<PythonIntegration,Long> {


}
