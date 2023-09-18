package com.hydroyura.prodms.fileserver.data.dto;

import com.hydroyura.prodms.fileserver.data.entity.FileType;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;

public class FileDTO {

    private String number;
    private long version;
    private FileType fileType;
    private byte[] content;


    public FileDTO() {}


    public String getNumber() {
        return number;
    }

    public FileDTO setNumber(String number) {
        this.number = number;
        return this;
    }

    public long getVersion() {
        return version;
    }

    public FileDTO setVersion(long version) {
        this.version = version;
        return this;
    }

    public FileType getFileType() {
        return fileType;
    }

    public FileDTO setFileType(FileType fileType) {
        this.fileType = fileType;
        return this;
    }

    public byte[] getContent() {
        return content;
    }

    public FileDTO setContent(byte[] content) {
        this.content = content;
        return this;
    }
}
