package com.with.second.service;

import com.with.second.entity.Book_ImgEntity;
import com.with.second.repository.BookRepository;
import com.with.second.repository.Book_ImgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService{

    private final BookRepository bookRepository;

    private final Book_ImgRepository repository;

    @Override
    public Map<String, String> upload(String uploadPath, MultipartFile uploadFile) {

        Map<String,String> resultMap = new HashMap();

        if (uploadFile.getContentType().startsWith("image") == false) {
            log.warn("this file is not image type");
            String No = "this file is not image type";
            resultMap.put("No",No);
            return resultMap;
        }

        String originalName = uploadFile.getOriginalFilename();

        String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

        log.info("fileName : " + fileName);

        String folderPath = makeFolder(uploadPath);

        String uuid = UUID.randomUUID().toString();

        String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

        Path savePath = Paths.get(saveName);

        try {
            uploadFile.transferTo(savePath);

            String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator
                    + "s_" + uuid + "_" + fileName;

            File thumbnailFile = new File(thumbnailSaveName);

            Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 100, 100);

            resultMap.put("iname",fileName);
            resultMap.put("uuid",uuid);
            resultMap.put("path",saveName);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    @Override
    public File getReal(String uploadPath, Long ino) {

        Optional<Book_ImgEntity> byId = repository.findById(ino);

        Book_ImgEntity book_imgEntity = byId.get();

        File file = new File(uploadPath + File.separator + book_imgEntity.getPath() + File.separator +
                book_imgEntity.getUuid() + "_" + book_imgEntity.getIname());

        return file;
    }

    @Override
    public File getFiction(String uploadPath, Long ino) {

        Optional<Book_ImgEntity> byId = repository.findById(ino);

        Book_ImgEntity book_imgEntity = byId.get();

        File file = new File(uploadPath + File.separator + book_imgEntity.getPath() + File.separator +
                "s_" + book_imgEntity.getUuid() + "_" + book_imgEntity.getIname());

        return file;
    }

    @Override
    public boolean remove(String uploadPath, Long ino) {

        Optional<Book_ImgEntity> byId = repository.findById(ino);

        Book_ImgEntity book_imgEntity = byId.get();

        File file = new File(uploadPath + File.separator + book_imgEntity.getPath() + File.separator +
                book_imgEntity.getUuid() + "_" + book_imgEntity.getIname());

        boolean result = file.delete();

        File thumbnail = new File(file.getParent(), "s_" + file.getName());

        result = thumbnail.delete();

        return result;
    }


    private String makeFolder(String uploadPath) {

        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath =  str.replace("//", File.separator);

        // make folder --------
        File uploadPathFolder = new File(uploadPath, folderPath);

        if (uploadPathFolder.exists() == false) {
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }

    @Override
    public Long getBno(Long ino) {

        Long bno = bookRepository.getBno(ino);

        return bno;
    }
}
