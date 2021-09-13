package com.with.second.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

public interface UploadService {

    Map<String,String> upload(String uploadPath, MultipartFile uploadFile);

    File getReal (String uploadPath, Long inum);

    File getFiction (String uploadPath, Long inum);

    boolean remove(String uploadPath, Long inum);

    Long getBno(Long inum);
}
