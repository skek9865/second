package com.with.second.controller;

import com.with.second.dto.BookDto;
import com.with.second.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService service;

    @GetMapping("/getList")
    public ResponseEntity<List<BookDto>> getList(){

        log.info("List Page.........");

        List<BookDto> list = service.getList();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/read/{bno}")
    public ResponseEntity<BookDto> read(@PathVariable("bno") Long bno){

        log.info("read page.........");

        BookDto read = service.read(bno);

        return new ResponseEntity<>(read, HttpStatus.OK);
    }

    @PostMapping("/register")
    public HttpStatus register(@RequestBody BookDto dto){

        log.info("dto : " + dto);

        service.register(dto);

        return HttpStatus.OK;
    }

    @DeleteMapping("/remove/{bno}")
    public HttpStatus remove(@PathVariable Long bno){

        log.info("bno : " + bno);

        service.remove(bno);

        return HttpStatus.OK;
    }


}
