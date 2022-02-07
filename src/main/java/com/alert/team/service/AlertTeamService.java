package com.alert.team.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alert.exception.ClientCallException;
import com.alert.team.model.Alert;
import com.alert.team.model.Developer;
import com.alert.team.model.Team;
import com.alert.team.repository.DeveloperRepository;
import com.alert.team.repository.RandomDeveloperRepositoryImpl;
import com.alert.team.repository.TeamRepository;

@Service
public class AlertTeamService {

	@Value("${sms.url}")
	String url;
	
	@Autowired
    private TeamRepository teamRepository;
	
	@Autowired
	private DeveloperRepository devRepository;
	
	@Autowired
	private RandomDeveloperRepositoryImpl repo;
	
     public Long saveTeam(Team team) {
	
	Team teamreturn=teamRepository.save(team);
	
	List<Developer> list=new ArrayList<Developer>();
	for(Developer dev:team.getDevelopers()) {
		dev.setTeams(teamreturn);
		list.add(dev);
	}
	
	devRepository.saveAll(list);
	return teamreturn.getTeamId();
	
}

public String sendAlert(String teamid) throws ClientCallException  {
	
	try {
	List<Developer> dev1=  repo.findOrderedByLimitedTo(Long.valueOf(teamid));
	
	RestTemplate restTemplate = new RestTemplate();
	URI uri = new URI(url);
	if(null!=dev1 && dev1.size()==1) {
			Alert alert=new Alert(dev1.get(0).getPhonenumber());
			ResponseEntity<String> result = restTemplate.postForEntity(uri, alert, String.class);
			return result!=null?result.getBody():"";
	}
	}catch(Exception e){
		throw new ClientCallException(e.getMessage());
	}
	return "";
	
}

	
	
}
