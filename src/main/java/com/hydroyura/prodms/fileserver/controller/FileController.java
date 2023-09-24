package com.hydroyura.prodms.fileserver.controller;


import com.hydroyura.prodms.fileserver.data.dto.FileDTO;
import com.hydroyura.prodms.fileserver.data.entity.File;
import com.hydroyura.prodms.fileserver.data.entity.FileType;
import com.hydroyura.prodms.fileserver.data.repository.BaseRepository;
import com.hydroyura.prodms.fileserver.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/v1/files")
public class FileController implements IFileController {


    @Autowired @Qualifier(value = "FileRepository")
    private BaseRepository<File> repository;

    @Autowired @Qualifier(value = "FileService")
    private FileService fileService;

    @RequestMapping(method = RequestMethod.POST, value = "/{number}/pdf" )
    public ResponseEntity<?> save(@PathVariable String number, @RequestBody byte[] content) {

        fileService.save(number, FileType.PDF, content);
        return ResponseEntity.ok("saved");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{number}/pdf", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Mono<ResponseEntity<FileDTO>> fetchPdf(@PathVariable String number) {
        return fileService.find(number, FileType.PDF)
                .map(arg1 -> new ResponseEntity<>(arg1, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
