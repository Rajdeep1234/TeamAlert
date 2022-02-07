package com.alert.team;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.alert.team.controller.TeamsController;
import com.alert.team.model.Developer;
import com.alert.team.model.Team;
import com.alert.team.service.AlertTeamService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(TeamsController.class)
class TeamApplicationTests {

	@Autowired
	MockMvc mockmvc;
	
	@MockBean
    private AlertTeamService alertTeamService;
	
	@Test
	void createTeams_Test() throws Exception {
		
		Team team=new Team();
		team.setTeamId(Long.valueOf(11));
		team.setTeamName("team1");
		
		Developer d1=new Developer();
		d1.setDeveloperId(Long.valueOf(1));
		d1.setName("abc");
		d1.setPhonenumber("8888999922");
		
		Developer d2=new Developer();
		d2.setDeveloperId(Long.valueOf(2));
		d2.setName("def");
		d2.setPhonenumber("9999888822");
		
		List<Developer> list=new ArrayList<Developer>();
		list.add(d1);
		list.add(d2);
		
		team.setDevelopers(list);
		
		when(alertTeamService.saveTeam(team)).thenReturn(Long.valueOf(11));
		
		mockmvc.perform(MockMvcRequestBuilders.post("/team").content(asJsonString(team)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	private String asJsonString(Object obj) {
         ObjectMapper mapper=new ObjectMapper();
      try {
		return      mapper.writeValueAsString(obj);
	} catch (Exception e) {
		
		throw new RuntimeException();
		
	}
	
	}

	
	@Test
	void saveTeams_Test() throws Exception {
		
		
		
		when(alertTeamService.sendAlert("1")).thenReturn("");
		
		mockmvc.perform(post("/{teamid}/alert","1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
}
