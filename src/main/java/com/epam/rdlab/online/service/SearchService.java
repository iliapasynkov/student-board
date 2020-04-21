package com.epam.rdlab.online.service;

import com.epam.rdlab.online.database.entity.GitUser;
import com.epam.rdlab.online.service.outbound.GitSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

	private final GitSearchService gitSearchService;

	public GitUser findGitUserByLogin(String login){
		return gitSearchService.findUserByLogin(login);
	}

	public List<GitUser> findGitUsersByLogin(List<String> loginList){
		return gitSearchService.findUsersByLogin(loginList);
	}

	public List<GitUser> findWhichGitUsersByForkedRepo(List<GitUser> userList, String repoName) {
		return gitSearchService.findWhichGitUsersByForkedRepo(userList, repoName);
	}
}
