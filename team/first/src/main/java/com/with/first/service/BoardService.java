package com.with.first.service;

import com.with.first.dto.BoardDTO;
import com.with.first.dto.PageRequestDTO;
import com.with.first.dto.PageResultDTO;
import com.with.first.entity.Board;
import com.with.first.entity.Member;

import java.util.List;

public interface BoardService {

    void register(BoardDTO dto);

    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

//    List<BoardDTO> getList();

    PageResultDTO<BoardDTO, Object[]> getListBySearch(String keyword, PageRequestDTO pageRequestDTO);

//    List<BoardDTO> getListBySearch(String keyword);

    PageResultDTO<BoardDTO, Object[]> getListBySearch_writer(String writer, PageRequestDTO pageRequestDTO);

//    List<BoardDTO> getListBySearch_writer(String writer);

    BoardDTO get(Long bno);

    void remove(Long bno);

    void modify(BoardDTO boardDTO);

    default Board dtoToEntity(BoardDTO dto){

        Member member = Member.builder().id(dto.getWriter()).build();

        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return board;
    }

    default BoardDTO entityToDto(Board board, Member member){

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .modDate(board.getModDate())
                .writer(member.getId())
                .build();

        return boardDTO;
    }
}
