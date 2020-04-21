package com.epam.rdlab.online.config;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Getter
public class StudentBoardThemes {

	private final Map<String, String> themeToRepo;

	public StudentBoardThemes(){
		themeToRepo = new LinkedHashMap<>();
		themeToRepo.put("Collections", "collections-template");
		themeToRepo.put("Exceptions", "java-exceptions-template");
		themeToRepo.put("Spring Core", "spring-core-template");
		themeToRepo.put("JDBC Template", "java-jdbc-template");
		themeToRepo.put("Data Handling", "java-data-handling-template");
		themeToRepo.put("HTTP", "http-template");
		themeToRepo.put("Final Project", "final-project-template");
	}
}
