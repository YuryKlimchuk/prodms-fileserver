package com.hydroyura.prodms.fileserver.data.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<Entity> extends ReactiveMongoRepository<Entity, String>, ReactiveQuerydslPredicateExecutor<Entity> {}
