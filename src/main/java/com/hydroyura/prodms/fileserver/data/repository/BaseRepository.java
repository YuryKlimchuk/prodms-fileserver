package com.hydroyura.prodms.fileserver.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<Entity> extends MongoRepository<Entity, String>, QuerydslPredicateExecutor<Entity> {}
