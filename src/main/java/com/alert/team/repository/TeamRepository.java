package com.alert.team.repository;

import org.springframework.stereotype.Repository;

import com.alert.team.model.Developer;
import com.alert.team.model.Team;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface TeamRepository  extends CrudRepository<Team, Long> {

	
}