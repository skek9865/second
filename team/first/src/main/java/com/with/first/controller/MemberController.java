//package com.with.first.controller;
//
//import com.with.first.dto.BoardDTO;
//import com.with.first.service.BoardService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@Log4j2
//@RequiredArgsConstructor
//@RequestMapping("/notes")
//
//public class MemberController {
//
//    private final BoardService boardService; //final
//
//    @PostMapping(value = "")
//    public HttpStatus register(@RequestBody BoardDTO boardDTO){
//
//        log.info("-----------register-------------------------------");
//        log.info(boardDTO);
//
//        boardService.register(boardDTO);
//
//        return HttpStatus.OK;
//    }
//
//    @GetMapping(value = "/{num}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<BoardDTO> read(@PathVariable("num") Long num){
//
//        log.info("-----------read-------------------------------");
//        log.info(num);
//        return new ResponseEntity<>(boardService.get(num), HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<BoardDTO>> getList(String email){
//
//        log.info("-----------getList-------------------------------");
//        log.info(email);
//
//        return new ResponseEntity<>(boardService.getAllWithWriter(email), HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/{num}", produces = MediaType.TEXT_PLAIN_VALUE)
//    public ResponseEntity<String> remove(@PathVariable("num") Long num){
//
//        log.info("-----------remove-------------------------------");
//        log.info(num);
//
//        boardService.remove(num);
//
//        return new ResponseEntity<>("removed", HttpStatus.OK);
//
//    }
//
//    @PutMapping(value = "/{num}", produces = MediaType.TEXT_PLAIN_VALUE)
//    public ResponseEntity<String> modify(@RequestBody BoardDTO boardDTO){
//
//        log.info("-----------modify-------------------------------");
//        log.info(boardDTO);
//
//        boardService.modify(boardDTO);
//
//        return new ResponseEntity<>("modified", HttpStatus.OK);
//
//    }
//
//}
