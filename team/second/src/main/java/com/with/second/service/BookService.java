package com.with.second.service;

import com.with.second.dto.BookDto;
import com.with.second.dto.Book_ImgDto;
import com.with.second.entity.BookEntity;
import com.with.second.entity.Book_ImgEntity;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BookService {

    void register(BookDto bookDto);

    List<BookDto> getList();

    List<BookDto> getListSearchByName(String keyword);

    List<BookDto> getListSearchByDepartment(String department);

    List<BookDto> getListSearchByDepartmentAndName (String keyword, String department);

    List<BookDto> getNewOrOldList(boolean isNew);

    List<BookDto> getOldOrNewListSearchByDepartment(String department, boolean isNew);

    List<BookDto> getOldOrNewListSearchByName(String keyword, boolean isNew);

    List<BookDto> getOldOrNewListSearchByNameAndDepartment(String keyword, String department, boolean isNew);

    BookDto read(Long bno);

    void remove(Long bno);

    default Map<String, Object> dtoToEntity(BookDto dto){

        Map<String, Object> entityMap = new HashMap<>();

        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath =  str.replace("//", File.separator);

        Book_ImgDto result = dto.getBook_img();

        Book_ImgEntity imgEntity = Book_ImgEntity.builder()
                .uuid(result.getUuid())
                .iname(result.getIname())
                .path(folderPath)
                .build();

        BookEntity entity = BookEntity.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .department(dto.getDepartment())
                .isNew(dto.isNew())
                .status(dto.getStatus())
                .book_imgEntity(imgEntity)
                .build();

        entityMap.put("book",entity);

        entityMap.put("book_img",imgEntity);

        return entityMap;
    }

    default BookDto entitiesToDTO(BookEntity bookEntity, Book_ImgEntity book_imgEntity){

        BookDto bookDto = BookDto.builder()
                .bno(bookEntity.getBno())
                .name(bookEntity.getName())
                .price(bookEntity.getPrice())
                .department(bookEntity.getDepartment())
                .isNew(bookEntity.isNew())
                .regDate(bookEntity.getRegdate())
                .status(bookEntity.getStatus())
                .build();

        Book_ImgDto book_imgDto = Book_ImgDto.builder()
                .ino(book_imgEntity.getIno())
                .iname(book_imgEntity.getIname())
                .uuid(book_imgEntity.getUuid())
                .path(book_imgEntity.getPath())
                .build();

        bookDto.setBook_img(book_imgDto);

        return bookDto;
    }
}
