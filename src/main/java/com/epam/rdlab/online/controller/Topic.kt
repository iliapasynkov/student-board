package com.epam.rdlab.online.controller

import com.epam.rdlab.online.database.entity.GitUser

data class Topic(
        val label: String,
        val students: List<GitUser>
)