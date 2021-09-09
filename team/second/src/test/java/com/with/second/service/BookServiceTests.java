package com.with.second.service;

import com.with.second.dto.BookDto;
import com.with.second.dto.Book_ImgDto;
import com.with.second.entity.BookEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class BookServiceTests {

    @Autowired
    private BookService service;

    @Transactional
    @Test
    public void register() {

        IntStream.rangeClosed(1, 100).forEach(i -> {

            Book_ImgDto book_imgDto = Book_ImgDto.builder()
                    .iname("JPA 프로그래밍..." + i)
                    .uuid(UUID.randomUUID().toString())
                    .build();

            System.out.println("book_imgDto : " + book_imgDto);

            BookDto bookDto = BookDto.builder()
                    .name("자바 ORM 표준 JPA 프로그래밍..." + i)
                    .price(38700)
                    .department("컴퓨터공학과")
                    .isNew(true)
                    .status("신간")
                    .book_img(book_imgDto)
                    .build();

            System.out.println("bookDTO : " + bookDto);

            service.register(bookDto);

        });
    }

    @Test
    public void getList(){

        List<BookDto> list = service.getList();

        for (BookDto dto : list){

            System.out.println("dto : " + dto);
        }
    }

    @Test
    public void read(){

        BookDto read = service.read(30L);

        System.out.println("read : " + read);
    }

    @Test
    public void remove(){

        service.remove(100L);
    }

}
