package com.with.first.service;

import com.with.first.dto.BoardDTO;
import com.with.first.dto.PageRequestDTO;
import com.with.first.dto.PageResultDTO;
import com.with.first.entity.Board;

import java.util.List;

public interface BoardService {

    void register(BoardDTO dto);

//    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    List<BoardDTO> getList();

//    PageResultDTO<BoardDTO, Object[]> getListBySearch(String keyword, PageRequestDTO pageRequestDTO);

    List<BoardDTO> getListBySearch(String keyword);

//    PageResultDTO<BoardDTO, Object[]> getListBySearch_writer(String writer, PageRequestDTO pageRequestDTO);

    List<BoardDTO> getListBySearch_writer(String writer);

    BoardDTO get(Long bno);

    void remove(Long bno);

    void modify(BoardDTO boardDTO);

    default Board dtoToEntity(BoardDTO dto){

        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .password(dto.getPassword())
                .build();

        return board;
    }

    default BoardDTO entityToDto(Board board){

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .modDate(board.getModDate())
                .writer(board.getWriter())
                .password(board.getPassword())
                .build();

        return boardDTO;
    }
}
