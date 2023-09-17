package com.hydroyura.prodms.fileserver.data.repository;

import com.hydroyura.prodms.fileserver.data.entity.File;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "FileRepository")
public interface FileRepository extends BaseRepository<File> {}
