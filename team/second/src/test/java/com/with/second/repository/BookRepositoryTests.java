package com.with.second.repository;

import com.with.second.entity.BookEntity;
import com.with.second.entity.Book_ImgEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void save(){
        IntStream.rangeClosed(1,100).forEach(i -> {

            if(i <= 30){
                BookEntity entity = BookEntity.builder()
                        .name("book..." + i)
                        .price(10000)
                        .department("computerScience")
                        .isNew(true)
                        .status("신간")
                        .build();
                bookRepository.save(entity);
            }
            else if(i <= 60){
                BookEntity entity = BookEntity.builder()
                        .name("book..." + i)
                        .price(5000)
                        .department("computerScience")
                        .isNew(false)
                        .status("상")
                        .build();
                bookRepository.save(entity);
            }
            else if(i <= 90){
                BookEntity entity = BookEntity.builder()
                        .name("book..." + i)
                        .price(3000)
                        .department("computerScience")
                        .isNew(false)
                        .status("중")
                        .build();
                bookRepository.save(entity);
            }
            else{
                BookEntity entity = BookEntity.builder()
                        .name("book..." + i)
                        .price(2000)
                        .department("computerScience")
                        .isNew(false)
                        .status("하")
                        .build();
                bookRepository.save(entity);
            }
        });

    }

    @Test
    public void read(){
        List<BookEntity> results = bookRepository.findAll(Sort.by("bno").descending());

        for(BookEntity result : results){

            System.out.println("result : " + result);
        }
    }

    @Test
    public void read2(){

        Optional<BookEntity> result = bookRepository.findById(30L);

        BookEntity entity = result.get();

        System.out.println("entity : " + entity);
    }

    @Test
    public void newQuery(){

        List<Object[]> bno = bookRepository.getList(Sort.by("bno").descending());

        for(Object[] result : bno){

            BookEntity bookEntity = (BookEntity) result[0];
            Book_ImgEntity book_imgEntity = (Book_ImgEntity) result[1];

            System.out.println("bookEntity : " + bookEntity);
            System.out.println("book_imgEntity : " + book_imgEntity);
        }
    }
}
