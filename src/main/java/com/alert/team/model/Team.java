package com.alert.team.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="team")
public class Team {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long teamId;
	private String teamName;
	
	@OneToMany(targetEntity=Developer.class, mappedBy="teams", fetch=FetchType.EAGER)
	private List<Developer> developers;
	

}
