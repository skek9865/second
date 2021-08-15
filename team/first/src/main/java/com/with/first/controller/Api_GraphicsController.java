package com.with.first.controller;

import com.with.first.dto.Board_GraphicsDTO;
import com.with.first.dto.PageRequestDTO;
import com.with.first.service.Board_GraphicsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Log4j2
public class Api_GraphicsController {

    private final Board_GraphicsService boardService;

    @GetMapping(value = "/get_list_graphics")
    public ResponseEntity getList(PageRequestDTO pageRequestDTO, String keyword, String writer){

        if (keyword != null) {
            return ResponseEntity.ok(boardService.getListBySearch(keyword, pageRequestDTO));
        }
        if (writer != null){
            return ResponseEntity.ok(boardService.getListBySearch_writer(writer,pageRequestDTO));
        }
        return ResponseEntity.ok(boardService.getList(pageRequestDTO));
    }

    @PostMapping(value = "/register_graphics")
    public HttpStatus register(@RequestBody Board_GraphicsDTO dto){

        boardService.register(dto);

        return HttpStatus.OK;
    }

    @GetMapping(value = "/get_board_graphics/{bno}")
    public ResponseEntity getBoard(@PathVariable("bno") Long bno){

        return ResponseEntity.ok(boardService.get(bno));
    }

    @PutMapping(value = "/modify_graphics")
    public HttpStatus modify(@RequestBody Board_GraphicsDTO dto){

        boardService.modify(dto);

        return HttpStatus.OK;
    }

    @DeleteMapping(value = "/delete_graphics/{bno}")
    public HttpStatus delete(@PathVariable("bno") Long bno){

        boardService.remove(bno);

        return HttpStatus.OK;
    }
}
