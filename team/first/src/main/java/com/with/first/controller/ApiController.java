package com.with.first.controller;

import com.with.first.dto.BoardDTO;
import com.with.first.dto.PageRequestDTO;
import com.with.first.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

public class ApiController {

    private final BoardService boardService;

    @GetMapping(value = "/get_list")
    public ResponseEntity getList(PageRequestDTO pageRequestDTO, String keyword, String writer){

        if (keyword != null) {
            return ResponseEntity.ok(boardService.getListBySearch(keyword, pageRequestDTO));
        }
        if (writer != null){
            return ResponseEntity.ok(boardService.getListBySearch_writer(writer,pageRequestDTO));
        }
        return ResponseEntity.ok(boardService.getList(pageRequestDTO));
    }

//    @GetMapping(value = "/get_list")
//    public ResponseEntity getList(String keyword, String writer){
//
//        if (keyword != null) {
//            return ResponseEntity.ok(boardService.getListBySearch(keyword));
//        }
//        if (writer != null){
//            return ResponseEntity.ok(boardService.getListBySearch_writer(writer));
//        }
//        return ResponseEntity.ok(boardService.getList());
//    }

    @PostMapping(value = "/register")
    public HttpStatus register(@RequestBody BoardDTO dto){

        boardService.register(dto);

        return HttpStatus.OK;
    }

    @GetMapping(value = "/get_board/{bno}")
    public ResponseEntity getBoard(@PathVariable("bno") Long bno){

        return ResponseEntity.ok(boardService.get(bno));
    }

    @PutMapping(value = "/modify")
    public HttpStatus modify(@RequestBody BoardDTO dto){

        boardService.modify(dto);

        return HttpStatus.OK;
    }

    @DeleteMapping(value = "/delete/{bno}")
    public HttpStatus delete(@PathVariable("bno") Long bno){

        boardService.remove(bno);

        return HttpStatus.OK;
    }

}
