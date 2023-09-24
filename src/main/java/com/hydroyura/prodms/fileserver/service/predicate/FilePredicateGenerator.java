package com.hydroyura.prodms.fileserver.service.predicate;

import com.hydroyura.prodms.fileserver.data.entity.FileType;
import com.hydroyura.prodms.fileserver.data.entity.QFile;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Configuration(value = "FilePredicateGenerator")
public class FilePredicateGenerator implements IPredicateGenerator {

    @Override
    public Predicate generate(Map<String, String> params) {
        if (params == null || params.isEmpty()) return Expressions.TRUE;
        Collection<Predicate> predicates = new ArrayList<>();

        Optional<String> number = Optional.ofNullable(params.get("NUMBER"));
        number.ifPresent(value -> predicates.add(QFile.file.number.eq(value)));

        Optional<String> type = Optional.ofNullable(params.get("TYPE"));
        type.ifPresent(value -> predicates.add(QFile.file.fileType.eq(FileType.valueOf(value))));

        if(predicates.isEmpty()) return Expressions.TRUE;
        return ExpressionUtils.allOf(predicates);
    }
}
