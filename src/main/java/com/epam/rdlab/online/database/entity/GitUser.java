package com.epam.rdlab.online.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class GitUser {

	private String login;
	@Id	private Long id;
	private String node_id;
	private String avatar_url;
	private String url;
	private String html_url;
	private String repos_url;
	private String type;

	// wrong. should be in domain object
	@Setter
	private String hasOpenPR = "NULL";

	public String getLogin() {
		return login;
	}

	public Long getId() {
		return id;
	}

	public String getNode_id() {
		return node_id;
	}

	public String getAvatar_url() {
		return avatar_url;
	}

	public String getUrl() {
		return url;
	}

	public String getHtml_url() {
		return html_url;
	}

	public String getRepos_url() {
		return repos_url;
	}

	public String getType() {
		return type;
	}

	public String getHasOpenPR() {
		return hasOpenPR;
	}
}
