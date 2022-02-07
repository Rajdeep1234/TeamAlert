package com.alert.team.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.alert.team.model.Developer;

@Repository
public class RandomDeveloperRepositoryImpl {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public List<Developer> findOrderedByLimitedTo(Long id) {
        return entityManager.createQuery("SELECT d FROM Developer d Where EXISTS (Select 1 from  Team t Where  t.teamId="+ id+") ORDER BY RAND()",
          Developer.class).setMaxResults(1).getResultList();
    }

}
