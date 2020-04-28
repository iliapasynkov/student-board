package com.epam.rdlab.online.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "student.board")
data class StudentBoardConfigProp (
    val topicToRepository: Map<String, String>,
    val logins: List<String>
)