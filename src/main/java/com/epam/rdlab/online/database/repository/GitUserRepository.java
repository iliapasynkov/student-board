package com.epam.rdlab.online.database.repository;

import com.epam.rdlab.online.database.entity.GitUser;
import org.springframework.data.repository.CrudRepository;

public interface GitUserRepository extends CrudRepository<GitUser, Long> {
}
