package com.with.first.controller;

import com.with.first.dto.Board_AIDTO;
import com.with.first.dto.PageRequestDTO;
import com.with.first.service.Board_AIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Log4j2
public class Api_AIController {

    private final Board_AIService boardService;

    @GetMapping(value = "/get_list_AI")
    public ResponseEntity getList(PageRequestDTO pageRequestDTO, String keyword, String writer){

        if (keyword != null) {
            return ResponseEntity.ok(boardService.getListBySearch(keyword, pageRequestDTO));
        }
        if (writer != null){
            return ResponseEntity.ok(boardService.getListBySearch_writer(writer,pageRequestDTO));
        }
        return ResponseEntity.ok(boardService.getList(pageRequestDTO));
    }

    @PostMapping(value = "/register_AI")
    public HttpStatus register(@RequestBody Board_AIDTO dto){

        boardService.register(dto);

        return HttpStatus.OK;
    }

    @GetMapping(value = "/get_board_AI/{bno}")
    public ResponseEntity getBoard(@PathVariable("bno") Long bno){

        return ResponseEntity.ok(boardService.get(bno));
    }

    @PutMapping(value = "/modify_AI")
    public HttpStatus modify(@RequestBody Board_AIDTO dto){

        boardService.modify(dto);

        return HttpStatus.OK;
    }

    @DeleteMapping(value = "/delete_AI/{bno}")
    public HttpStatus delete(@PathVariable("bno") Long bno){

        boardService.remove(bno);

        return HttpStatus.OK;
    }
}
