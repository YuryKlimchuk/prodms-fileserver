package com.hydroyura.prodms.fileserver.service.predicate;

import com.querydsl.core.types.Predicate;

import java.util.Map;

public interface IPredicateGenerator{

    public Predicate generate(Map<String, String> params);

}

