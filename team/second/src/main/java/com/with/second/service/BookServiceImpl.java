package com.with.second.service;

import com.with.second.dto.BookDto;
import com.with.second.dto.Book_ImgDto;
import com.with.second.entity.BookEntity;
import com.with.second.entity.Book_ImgEntity;
import com.with.second.repository.BookRepository;
import com.with.second.repository.Book_ImgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    private final Book_ImgRepository book_imgRepository;

    @Override
    public void register(BookDto bookDto) {

        Map<String, Object> resultMap = dtoToEntity(bookDto);

        BookEntity bookEntity = (BookEntity) resultMap.get("book");

        log.info("bookEntity : " + bookEntity);

        Book_ImgEntity book_imgEntity = (Book_ImgEntity) resultMap.get("book_img");

        log.info("book_imgEntity : " + book_imgEntity);

        bookRepository.save(bookEntity);
        book_imgRepository.save(book_imgEntity);
    }

    @Override
    public List<BookDto> getList() {

        List<Object[]> bookList = bookRepository.getList(Sort.by("bno").descending());

        List<BookDto> bookDtos = new ArrayList<>();

        for(Object[] result : bookList){

            BookDto bookDto = entitiesToDTO((BookEntity) result[0], (Book_ImgEntity) result[1]);

            bookDtos.add(bookDto);
        }

        return bookDtos;
    }
}
