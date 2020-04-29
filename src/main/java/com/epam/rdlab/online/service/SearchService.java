package com.epam.rdlab.online.service;

import com.epam.rdlab.online.controller.Topic;
import com.epam.rdlab.online.database.entity.GitUser;
import com.epam.rdlab.online.service.outbound.GitSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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

	@Async
	public CompletableFuture<Topic> findWhichGitUsersByForkedRepo(List<GitUser> userList, String repoName) {
		return CompletableFuture.supplyAsync(() -> new Topic(repoName, gitSearchService.findWhichGitUsersByForkedRepo(userList, repoName)));
	}
}
