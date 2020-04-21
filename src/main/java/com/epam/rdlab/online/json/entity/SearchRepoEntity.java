package com.epam.rdlab.online.json.entity;

import com.epam.rdlab.online.database.entity.GitRepo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Getter
public class SearchRepoEntity {
	private Long total_count;
	private Boolean incomplete_results;
	private GitRepo[] items;
}
