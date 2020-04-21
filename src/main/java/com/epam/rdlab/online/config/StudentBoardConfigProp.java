package com.epam.rdlab.online.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "student.board")
@Getter
@Setter
public class StudentBoardConfigProp {

	Map<String, String> repo;
	Map<String, String> theme;
}
