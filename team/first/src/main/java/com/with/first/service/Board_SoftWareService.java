package com.with.first.service;

import com.with.first.dto.*;
import com.with.first.entity.Board_SoftWare;
import com.with.first.entity.Member;
public interface Board_SoftWareService {

    void register(Board_SoftWareDTO dto);

    PageResultDTO<Board_SoftWareDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    PageResultDTO<Board_SoftWareDTO, Object[]> getListBySearch(String keyword, PageRequestDTO pageRequestDTO);

    PageResultDTO<Board_SoftWareDTO, Object[]> getListBySearch_writer(String writer, PageRequestDTO pageRequestDTO);

    Board_SoftWareDTO get(Long bno);

    void remove(Long bno);

    void modify(Board_SoftWareDTO boardDTO);

    default Board_SoftWare dtoToEntity(Board_SoftWareDTO dto){

        Member member = Member.builder().id(dto.getWriter()).build();

        Board_SoftWare board = Board_SoftWare.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return board;
    }

    default Board_SoftWareDTO entityToDto(Board_SoftWare board, Member member){

        Board_SoftWareDTO boardDTO = Board_SoftWareDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .modDate(board.getModDate())
                .writer(member.getId())
                .build();

        return boardDTO;
    }
}
