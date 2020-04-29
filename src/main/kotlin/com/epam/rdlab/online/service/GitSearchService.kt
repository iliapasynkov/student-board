package com.epam.rdlab.online.service

import com.epam.rdlab.online.database.entity.GitUser
import com.epam.rdlab.online.json.entity.SearchUserEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component("kotlinGitSearchService")
class GitSearchService(private val restTemplate: RestTemplate) {

    fun search(names: List<String>): List<GitUser> {
        val query = names.joinToString(prefix = SEARCH_API_USER_QUERY_PREFIX, postfix = SEARCH_API_USER_QUERY_SUFFIX, separator = ",")
        val endpoint = "${SEARCH_API_URL}?q=$query}"

        val entity: SearchUserEntity = restTemplate.getForObject(endpoint, SearchUserEntity::class.java) ?: return emptyList()

        if (entity.total_count == 0L) {
            return emptyList()
        }

        return listOf(*entity.items)
                .filter { names.contains(it.login) }
                .fold(mutableListOf()) { list, user -> list.add(user); list }
    }

    companion object {
        const val SEARCH_API_URL = "https://api.github.com/search/users"
        const val SEARCH_API_USER_QUERY_PREFIX = "user:"
        const val SEARCH_API_USER_QUERY_SUFFIX = "+"
    }
}