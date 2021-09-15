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
    public ResponseEntity<List<BookDto>> getList(String keyword, String department){

        if(keyword != null && department != null){

            log.info("List Page Search By Name And Department........");

            List<BookDto> listSearchByDepartmentAndName = service.getListSearchByDepartmentAndName(keyword, department);

            return new ResponseEntity<>(listSearchByDepartmentAndName,HttpStatus.OK);
        }

        if(keyword != null){
            log.info("List Page Search By Name........");

            List<BookDto> listSearchByName = service.getListSearchByName(keyword);
            return new ResponseEntity<>(listSearchByName, HttpStatus.OK);
        }

        if(department != null) {
            log.info("List Page Search By Department........");

            List<BookDto> listBySearch = service.getListSearchByDepartment(department);

            return new ResponseEntity<>(listBySearch, HttpStatus.OK);
        }

        log.info("List Page.........");

        List<BookDto> list = service.getList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getOldList")
    public ResponseEntity<List<BookDto>> getOldList(String keyword, String department, boolean isNew){

        if(keyword != null && department != null){

            log.info("List Old Or New Page Search By Name And Department........");

            List<BookDto> oldOrNewListSearchByNameAndDepartment = service.getOldOrNewListSearchByNameAndDepartment(keyword, department, isNew);

            return new ResponseEntity<>(oldOrNewListSearchByNameAndDepartment,HttpStatus.OK);
        }

        if(keyword != null){
            log.info("List Old Or New Page Search By Name........");

            List<BookDto> oldOrNewListSearchByName = service.getOldOrNewListSearchByName(keyword, isNew);

            return new ResponseEntity<>(oldOrNewListSearchByName, HttpStatus.OK);
        }

        if(department != null){
            log.info("List Old Or New Page Search By Department........");

            List<BookDto> getOldOrNewListSearchByDepartment = service.getOldOrNewListSearchByDepartment(department, isNew);

            return new ResponseEntity<>(getOldOrNewListSearchByDepartment, HttpStatus.OK);
        }

        log.info("List Old Or New Page");
        List<BookDto> newOrOldList = service.getNewOrOldList(isNew);

        return new ResponseEntity<>(newOrOldList, HttpStatus.OK);
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
    public HttpStatus remove(@PathVariable("bno") Long bno){

        log.info("bno : " + bno);

        service.remove(bno);

        return HttpStatus.OK;
    }


}
