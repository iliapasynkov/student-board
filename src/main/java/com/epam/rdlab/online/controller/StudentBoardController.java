package com.epam.rdlab.online.controller;

import com.epam.rdlab.online.config.StudentBoardConfigProp;
import com.epam.rdlab.online.config.StudentBoardThemes;
import com.epam.rdlab.online.database.entity.GitUser;
import com.epam.rdlab.online.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class StudentBoardController {

	private final SearchService searchService;
	//private final StudentBoardConfigProp configProp;
	private final StudentBoardThemes themes;

	@Value("${student.board.logins}")
	private List<String> userLoginList;

	@Value("${student.board.rdlab.login}")
	private String repoPrefix;

	@GetMapping
	public String buildStartPage(Model model) {
		List<GitUser> userList = searchService.findGitUsersByLogin(userLoginList);
		model.addAttribute("userList", userList);
		/*
		Map<String, String> themeToRepo = themes.getThemeToRepo();
		Set<String> themeSet = themeToRepo.keySet();
		Map<String, List<GitUser>> themeUserMap = new HashMap<>();


		for(String themeName : themeSet){
			String repoName = themeToRepo.get(themeName);
			List<GitUser> repoUserList = searchService.findWhichGitUsersByForkedRepo(userList, repoName);
			themeUserMap.put(repoName, repoUserList);
			model.addAttribute(repoName, repoUserList);
		}
		*/

		// long way
		List<GitUser> repoUserList1 = searchService.findWhichGitUsersByForkedRepo(userList, "java-collections-template");
		List<GitUser> repoUserList2 = searchService.findWhichGitUsersByForkedRepo(userList, "java-exceptions-template");
		List<GitUser> repoUserList3 = searchService.findWhichGitUsersByForkedRepo(userList, "spring-core-template");
		List<GitUser> repoUserList4 = searchService.findWhichGitUsersByForkedRepo(userList, "java-jdbc-template");
		List<GitUser> repoUserList5 = searchService.findWhichGitUsersByForkedRepo(userList, "java-data-handling-template");
		List<GitUser> repoUserList6 = searchService.findWhichGitUsersByForkedRepo(userList, "http-template");
		List<GitUser> repoUserList7 = searchService.findWhichGitUsersByForkedRepo(userList, "final-project-template");


		model.addAttribute("collectionList", repoUserList1);
		model.addAttribute("exceptionList", repoUserList2);
		model.addAttribute("springcoreList", repoUserList3);
		model.addAttribute("jdbcList", repoUserList4);
		model.addAttribute("dataList", repoUserList5);
		model.addAttribute("httpList", repoUserList6);
		model.addAttribute("finalList", repoUserList7);
		return "board";
	}

	@GetMapping("time")
	@ResponseBody
	public String getTime() {
		return LocalDateTime.now().toString();
	}
}
