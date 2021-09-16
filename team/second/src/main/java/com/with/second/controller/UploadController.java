package com.with.second.controller;

import com.with.second.service.BookService;
import com.with.second.service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/file")
@RequiredArgsConstructor
public class UploadController {

    private final UploadService uploadService;

    private final BookService bookService;

    @Value("${com.with.upload.path}")
    private String uploadPath;

    @PostMapping("/upload")
    public ResponseEntity<Map<String,String>> uploadFile(MultipartFile uploadFile){

        log.info("uploadFile : " + uploadFile);

        Map<String, String> resultMap = uploadService.upload(uploadPath, uploadFile);

        return new ResponseEntity<>(resultMap,HttpStatus.OK);
    }


    @GetMapping("/getFiction")
    public ResponseEntity<byte[]> getFiction(Long ino) throws IOException {

        File file = uploadService.getFiction(uploadPath,ino);

        HttpHeaders header = new HttpHeaders();

        header.add("Content-Type", Files.probeContentType(file.toPath()));

        return new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),header, HttpStatus.OK);
    }

    @GetMapping("/getReal")
    public ResponseEntity<byte[]> getReal(Long ino) throws IOException {

        File file = uploadService.getReal(uploadPath,ino);

        HttpHeaders header = new HttpHeaders();

        header.add("Content-Type", Files.probeContentType(file.toPath()));

        return new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),header, HttpStatus.OK);

    }

    @DeleteMapping("/remove")
    public ResponseEntity<Boolean> removeFile(Long ino){

        boolean remove = uploadService.remove(uploadPath, ino);

        Long bno = uploadService.getBno(ino);

        if (remove == true && bno != 0L){
            bookService.remove(bno);
        }

        return new ResponseEntity<>(remove, HttpStatus.OK);
    }
}
