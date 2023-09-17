package com.hydroyura.prodms.fileserver.data.entity;

import com.querydsl.core.annotations.QueryEntity;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "files")
@QueryEntity
public class File {

    @Id
    private String id;
    private String number;
    private long version;
    private FileType fileType;
    private Binary content;


    public File() {}


    public String getId() {
        return id;
    }

    public File setId(String id) {
        this.id = id;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public File setNumber(String number) {
        this.number = number;
        return this;
    }

    public long getVersion() {
        return version;
    }

    public File setVersion(long version) {
        this.version = version;
        return this;
    }

    public FileType getFileType() {
        return fileType;
    }

    public File setFileType(FileType fileType) {
        this.fileType = fileType;
        return this;
    }

    public Binary getContent() {
        return content;
    }

    public File setContent(Binary content) {
        this.content = content;
        return this;
    }
}
