package com.alert.team.repository;

import org.springframework.stereotype.Repository;

import com.alert.team.model.Developer;
import com.alert.team.model.Team;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface DeveloperRepository  extends CrudRepository<Developer, Long> {

}