package com.hydroyura.prodms.fileserver.service;


import com.hydroyura.prodms.fileserver.data.entity.File;
import com.hydroyura.prodms.fileserver.data.entity.FileType;
import com.hydroyura.prodms.fileserver.data.repository.BaseRepository;
import com.hydroyura.prodms.fileserver.service.predicate.IPredicateGenerator;
import com.querydsl.core.types.Predicate;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.stream.StreamSupport;

@Service(value = "FileService")
public class FileService {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired @Qualifier(value = "FileRepository")
    private BaseRepository<File> repository;

    @Autowired @Qualifier(value = "FilePredicateGenerator")
    private IPredicateGenerator predicateGenerator;


    public boolean save(String number, FileType type, @RequestBody byte[] content) {
        Map<String, String> params = Map.of("NUMBER", number, "TYPE", type.name());
        Predicate predicate = predicateGenerator.generate(params);

        try {
            OptionalLong version = StreamSupport.stream(repository.findAll(predicate).spliterator(), false)
                    .mapToLong(File::getVersion)
                    .max();
            File file = new File()
                    .setContent(new Binary(BsonBinarySubType.BINARY, content))
                    .setNumber(number)
                    .setVersion(version.isPresent() ? (version.getAsLong() + 1) : 1L)
                    .setFileType(type);
            repository.save(file);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    };

    public Optional<File> find(String number, FileType type) {
        Map<String, String> params = Map.of("NUMBER", number, "TYPE", type.name());
        Predicate predicate = predicateGenerator.generate(params);

        Optional<File> result = StreamSupport.stream(repository.findAll(predicate).spliterator(), false)
                .reduce((first, second) -> (first.getVersion() > second.getVersion()) ? first : second);
        return result;
    }


}
