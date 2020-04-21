package com.epam.rdlab.online.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Getter
public class GitRepo {
	//@Id
	private Long id;
	private String node_id;
	private String name;
	private String full_name;
	private GitUser owner;
	private Boolean isPrivate;
	private String html_url;
	private String description;
	private Boolean fork;
	private String url;
	private String language;
	private Long forks_count;
	private String master_branch;
	private String default_branch;
}
