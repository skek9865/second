package com.with.second.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

public interface UploadService {

    Map<String,String> upload(String uploadPath, MultipartFile uploadFile);

    File getReal (String uploadPath, Long ino);

    File getFiction (String uploadPath, Long ino);

    boolean remove(String uploadPath, Long ino);

    Long getBno(Long ino);
}
