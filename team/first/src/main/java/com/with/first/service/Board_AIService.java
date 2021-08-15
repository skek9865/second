package com.with.first.service;

import com.with.first.dto.Board_AIDTO;
import com.with.first.dto.PageRequestDTO;
import com.with.first.dto.PageResultDTO;
import com.with.first.entity.Board_AI;
import com.with.first.entity.Member;
public interface Board_AIService {

    void register(Board_AIDTO dto);

    PageResultDTO<Board_AIDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    PageResultDTO<Board_AIDTO, Object[]> getListBySearch(String keyword, PageRequestDTO pageRequestDTO);

    PageResultDTO<Board_AIDTO, Object[]> getListBySearch_writer(String writer, PageRequestDTO pageRequestDTO);

    Board_AIDTO get(Long bno);

    void remove(Long bno);

    void modify(Board_AIDTO boardDTO);

    default Board_AI dtoToEntity(Board_AIDTO dto){

        Member member = Member.builder().id(dto.getWriter()).build();

        Board_AI board = Board_AI.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return board;
    }

    default Board_AIDTO entityToDto(Board_AI board, Member member){

        Board_AIDTO boardDTO = Board_AIDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .modDate(board.getModDate())
                .writer(member.getId())
                .build();

        return boardDTO;
    }
}
