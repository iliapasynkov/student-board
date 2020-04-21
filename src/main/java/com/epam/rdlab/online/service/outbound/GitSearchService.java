package com.epam.rdlab.online.service.outbound;

import com.epam.rdlab.online.database.entity.GitPullRequest;
import com.epam.rdlab.online.database.entity.GitRepo;
import com.epam.rdlab.online.database.entity.GitUser;
import com.epam.rdlab.online.json.entity.SearchPullRequestEntity;
import com.epam.rdlab.online.json.entity.SearchRepoEntity;
import com.epam.rdlab.online.json.entity.SearchUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GitSearchService {

	private final RestTemplate restTemplate;
	private final String USER_SEARCH_API_URL = "https://api.github.com/search/users?q=";
	private final String USER_SEARCH_BOUND_USER = "user:";
	private final String ANY_SEARCH_ADD_CRITERIA = "+";
	private final String REPO_SEARCH_API_URL = "https://api.github.com/search/repositories?q=";
	private final String REPO_SEARCH_API_CRITERIA = "fork:only+in:name";
	private final String PULL_SEARCH_API_URL = "https://api.github.com/search/issues?q=type:pr+repo:";

	public GitUser findUserByLogin(String login) {
		SearchUserEntity entity = restTemplate.getForObject(USER_SEARCH_API_URL + USER_SEARCH_BOUND_USER + login,
															SearchUserEntity.class);
		if (entity != null && entity.getTotal_count() != 0) {
			Optional<GitUser> user = Arrays.stream(entity.getItems())
										   .filter(gitUser -> {return gitUser.getLogin().equals(login);})
										   .findFirst();
			return user.isPresent() ? user.get() : null;
		}
		return null;
	}

	public List<GitUser> findUsersByLogin(List<String> loginList) {
		StringBuilder sb = new StringBuilder(USER_SEARCH_API_URL);
		loginList.forEach(s -> {
			sb.append(USER_SEARCH_BOUND_USER).append(s).append(ANY_SEARCH_ADD_CRITERIA);
		});
		sb.deleteCharAt(sb.length() - 1);

		SearchUserEntity entity = restTemplate.getForObject(sb.toString(), SearchUserEntity.class);
		List<GitUser> userList = new ArrayList<>();

		if (entity != null && entity.getTotal_count() != 0) {
			List<GitUser> items = Arrays.asList(entity.getItems());
			items.forEach(gitUser -> {
				if (loginList.contains(gitUser.getLogin())) {
					userList.add(gitUser);
				}
			});
		}
		return userList;
	}

	public List<GitUser> findWhichGitUsersByForkedRepo(List<GitUser> userList, String repoName) {
		StringBuilder sb = new StringBuilder(REPO_SEARCH_API_URL);
		sb.append(repoName).append(ANY_SEARCH_ADD_CRITERIA);
		userList.forEach(s -> {
			sb.append(USER_SEARCH_BOUND_USER).append(s.getLogin()).append(ANY_SEARCH_ADD_CRITERIA);
		});
		sb.append(REPO_SEARCH_API_CRITERIA);

		SearchRepoEntity entity = restTemplate.getForObject(sb.toString(), SearchRepoEntity.class);
		List<GitUser> userResultList = new ArrayList<>();

		if (entity != null && entity.getTotal_count() != 0) {
			List<GitRepo> items = Arrays.asList(entity.getItems());
			items.forEach(gitRepo -> {
				userResultList.add(gitRepo.getOwner());
			});
		}
		/*
		for (GitUser u : userResultList) {
			u.setHasOpenPR("");
			StringBuilder sb2 = new StringBuilder(PULL_SEARCH_API_URL);
			sb2.append(u.getLogin()).append("/").append(repoName);
			SearchPullRequestEntity prEntity = restTemplate.getForObject(sb2.toString(), SearchPullRequestEntity.class);
			if (prEntity != null && prEntity.getTotal_count() != 0) {
				List<GitPullRequest> prList = Arrays.asList(prEntity.getItems());
				for (GitPullRequest pr : prList) {
					if(pr.getState().equals("open")){
						u.setHasOpenPR("(open)");
					}
				}
			}
		}
		*/
		return userResultList;
	}
}
