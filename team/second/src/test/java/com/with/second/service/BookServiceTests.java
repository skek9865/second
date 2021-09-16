package com.with.second.service;

import com.with.second.dto.BookDto;
import com.with.second.dto.Book_ImgDto;
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

    @Test
    public void register() {

        IntStream.rangeClosed(1, 200).forEach(i -> {

            if(i <= 30){
                Book_ImgDto book_imgDto = Book_ImgDto.builder()
                        .iname("JPA 프로그래밍..." + i)
                        .uuid(UUID.randomUUID().toString())
                        .build();

                System.out.println("book_imgDto : " + book_imgDto);

                BookDto bookDto = BookDto.builder()
                        .name("자바 ORM 표준 JPA 프로그래밍..." + i)
                        .price(38700)
                        .department("전기공학과")
                        .isNew(false)
                        .status("상")
                        .book_img(book_imgDto)
                        .build();

                System.out.println("bookDTO : " + bookDto);

                service.register(bookDto);
            }

           else if (i <= 60){
                Book_ImgDto book_imgDto = Book_ImgDto.builder()
                        .iname("JPA 프로그래밍..." + i)
                        .uuid(UUID.randomUUID().toString())
                        .build();

                System.out.println("book_imgDto : " + book_imgDto);

                BookDto bookDto = BookDto.builder()
                        .name("자바 ORM 표준 JPA 프로그래밍..." + i)
                        .price(38700)
                        .department("전자공학과")
                        .isNew(false)
                        .status("중")
                        .book_img(book_imgDto)
                        .build();

                System.out.println("bookDTO : " + bookDto);

                service.register(bookDto);
            }
           else if (i <= 100){
                Book_ImgDto book_imgDto = Book_ImgDto.builder()
                        .iname("JPA 프로그래밍..." + i)
                        .uuid(UUID.randomUUID().toString())
                        .build();

                System.out.println("book_imgDto : " + book_imgDto);

                BookDto bookDto = BookDto.builder()
                        .name("자바 ORM 표준 JPA 프로그래밍..." + i)
                        .price(38700)
                        .department("ICT융합부")
                        .isNew(false)
                        .status("하")
                        .book_img(book_imgDto)
                        .build();

                System.out.println("bookDTO : " + bookDto);

                service.register(bookDto);
            }

           else {
                if (i <= 130) {
                    Book_ImgDto book_imgDto = Book_ImgDto.builder()
                            .iname("JPA 프로그래밍..." + i)
                            .uuid(UUID.randomUUID().toString())
                            .build();

                    System.out.println("book_imgDto : " + book_imgDto);

                    BookDto bookDto = BookDto.builder()
                            .name("자바 ORM 표준 JPA 프로그래밍..." + i)
                            .price(38700)
                            .department("전기공학과")
                            .isNew(true)
                            .status("신간")
                            .book_img(book_imgDto)
                            .build();

                    System.out.println("bookDTO : " + bookDto);

                    service.register(bookDto);
                } else if (i <= 160) {
                    Book_ImgDto book_imgDto = Book_ImgDto.builder()
                            .iname("JPA 프로그래밍..." + i)
                            .uuid(UUID.randomUUID().toString())
                            .build();

                    System.out.println("book_imgDto : " + book_imgDto);

                    BookDto bookDto = BookDto.builder()
                            .name("자바 ORM 표준 JPA 프로그래밍..." + i)
                            .price(38700)
                            .department("전자공학과")
                            .isNew(true)
                            .status("신간")
                            .book_img(book_imgDto)
                            .build();

                    System.out.println("bookDTO : " + bookDto);

                    service.register(bookDto);
                } else {
                    Book_ImgDto book_imgDto = Book_ImgDto.builder()
                            .iname("JPA 프로그래밍..." + i)
                            .uuid(UUID.randomUUID().toString())
                            .build();

                    System.out.println("book_imgDto : " + book_imgDto);

                    BookDto bookDto = BookDto.builder()
                            .name("자바 ORM 표준 JPA 프로그래밍..." + i)
                            .price(38700)
                            .department("ICT융합부")
                            .isNew(true)
                            .status("신간")
                            .book_img(book_imgDto)
                            .build();

                    System.out.println("bookDTO : " + bookDto);

                    service.register(bookDto);
                }
            }
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

    @Test
    public void search1(){

        List<BookDto> listSearchByName = service.getListSearchByName("1");

        for (BookDto dto : listSearchByName){

            System.out.println("dto : " + dto);
        }
    }

    @Test
    public void search2(){

        List<BookDto> list = service.getListSearchByDepartmentAndName("1", "전기공학과");

        for(BookDto dto : list){

            System.out.println("dto : " + dto);
        }
    }

    @Test
    public void search3(){

        List<BookDto> list = service.getListSearchByDepartment("컴퓨터공학과");

        for (BookDto dto : list){

            System.out.println("dto : " + dto);
        }
    }

    @Test
    public void search4(){

        List<BookDto> list = service.getNewOrOldList(false);

        for (BookDto dto : list){

            System.out.println("dto : " + dto);
        }
    }

    @Test
    public void search5(){

        List<BookDto> list = service.getOldOrNewListSearchByNameAndDepartment("1", "전자공학과", true);

        for (BookDto dto : list){

            System.out.println("dto : " + dto);
        }
    }

    @Test
    public void search6(){

        List<BookDto> list = service.getOldOrNewListSearchByName("1", true);

        for (BookDto dto : list){

            System.out.println("dto : " + dto);
        }
    }
}
