package com.alert.team.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="developer")
public class Developer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long developerId;
	private String name;
	private String phonenumber;
	
	@ManyToOne
	@JoinColumn(name="teamId")
    private Team teams;

}
