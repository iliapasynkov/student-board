package com.epam.rdlab.online.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Getter
public class GitPullRequest {
	private String url;
	private Long number;
	private String state;
	private String body;
}
