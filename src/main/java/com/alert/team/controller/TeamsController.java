package com.alert.team.controller;



import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alert.exception.ClientCallException;
import com.alert.exception.GenericException;
import com.alert.team.model.Team;
import com.alert.team.service.AlertTeamService;


@RestController
public class TeamsController {
	
	@Autowired
    private AlertTeamService alertTeamService;
	
	 @PostMapping("/team")
	    public ResponseEntity<Long> createTeams(@RequestBody Team team) throws ValidationException, GenericException {
	     try { 
		 if (!isTeamValid(team)) {
	        	 throw new ValidationException("Invalid request");
	        }
	       Long teamid= alertTeamService.saveTeam(team);
	        return ResponseEntity.ok(teamid);
	     }
	     catch(Exception e) {
	    	 throw new GenericException("exception ");
	     }
	    }

	private boolean isTeamValid(Team team)  {
		if(null!=team  && !CollectionUtils.isEmpty(team.getDevelopers())) 
			return true;
		return false;
	}
	
	
	@PostMapping("/{teamid}/alert")
    public ResponseEntity<Object> sendAlert(@PathVariable String teamid) throws ClientCallException   {
		try {
			alertTeamService.sendAlert(teamid);
		} catch (ClientCallException e) {
			throw new ClientCallException(e.getMessage());
		}
        return ResponseEntity.ok("Success");
    }



}
