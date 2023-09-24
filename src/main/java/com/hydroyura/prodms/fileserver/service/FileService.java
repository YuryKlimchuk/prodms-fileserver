package com.hydroyura.prodms.fileserver.service;


import com.hydroyura.prodms.fileserver.data.dto.FileDTO;
import com.hydroyura.prodms.fileserver.data.entity.File;
import com.hydroyura.prodms.fileserver.data.entity.FileType;
import com.hydroyura.prodms.fileserver.data.repository.BaseRepository;
import com.hydroyura.prodms.fileserver.service.predicate.IPredicateGenerator;
import com.querydsl.core.types.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import java.util.Map;

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
        /*
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

         */
        return true;
    };

    public Mono<FileDTO> find(String number, FileType type) {
        Map<String, String> params = Map.of("NUMBER", number, "TYPE", type.name());
        Predicate predicate = predicateGenerator.generate(params);

        return repository.findAll(predicate)
                        .reduce((first, second) -> (first.getVersion() > second.getVersion()) ? first : second)
                        .map(file -> new FileDTO()
                                .setFileType(file.getFileType())
                                .setNumber(file.getNumber())
                                .setVersion(file.getVersion())
                                .setContent(file.getContent().getData()))
                        .single();
    }


}
