package com.with.first.service;

import com.with.first.dto.*;
import com.with.first.entity.Board_DB;
import com.with.first.entity.Member;
public interface Board_DBService {

    void register(Board_DBDTO dto);

    PageResultDTO<Board_DBDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    PageResultDTO<Board_DBDTO, Object[]> getListBySearch(String keyword, PageRequestDTO pageRequestDTO);

    PageResultDTO<Board_DBDTO, Object[]> getListBySearch_writer(String writer, PageRequestDTO pageRequestDTO);

    Board_DBDTO get(Long bno);

    void remove(Long bno);

    void modify(Board_DBDTO boardDTO);

    default Board_DB dtoToEntity(Board_DBDTO dto){

        Member member = Member.builder().id(dto.getWriter()).build();

        Board_DB board = Board_DB.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return board;
    }

    default Board_DBDTO entityToDto(Board_DB board, Member member){

        Board_DBDTO boardDTO = Board_DBDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .modDate(board.getModDate())
                .writer(member.getId())
                .build();

        return boardDTO;
    }
}
