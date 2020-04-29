package com.epam.rdlab.online.json.entity;

import com.epam.rdlab.online.database.entity.GitUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class SearchUserEntity {

	private Long total_count;
	private Boolean incomplete_results;
	private GitUser[] items;

	public Long getTotal_count() {
		return total_count;
	}

	public Boolean getIncomplete_results() {
		return incomplete_results;
	}

	public GitUser[] getItems() {
		return items;
	}
}
