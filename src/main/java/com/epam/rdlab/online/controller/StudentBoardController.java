package com.epam.rdlab.online.controller;

import com.epam.rdlab.online.config.StudentBoardConfigProp;
import com.epam.rdlab.online.database.entity.GitUser;
import com.epam.rdlab.online.service.SearchService;
import com.epam.rdlab.online.service.TopicCollectorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class StudentBoardController {

	private final SearchService searchService;
	private final StudentBoardConfigProp configProp;

	@GetMapping("/rest/board")
	@ResponseBody
	public List<Topic> getBoard() {
		List<GitUser> userList = searchService.findGitUsersByLogin(configProp.getLogins());
		Map<String, String> topicToRepo = configProp.getTopicToRepository();

		List<CompletableFuture<Topic>> futures = new ArrayList<>();
		for (String topic: topicToRepo.keySet()) {
			futures.add(searchService.findWhichGitUsersByForkedRepo(userList, topic));
		}
		return futures.stream().map(CompletableFuture::join).collect(TopicCollectorFactory.getCollector());
	}
}
