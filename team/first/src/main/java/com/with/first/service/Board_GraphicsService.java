package com.with.first.service;

import com.with.first.dto.*;
import com.with.first.entity.Board_Graphics;
import com.with.first.entity.Member;
public interface Board_GraphicsService {

    void register(Board_GraphicsDTO dto);

    PageResultDTO<Board_GraphicsDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    PageResultDTO<Board_GraphicsDTO, Object[]> getListBySearch(String keyword, PageRequestDTO pageRequestDTO);

    PageResultDTO<Board_GraphicsDTO, Object[]> getListBySearch_writer(String writer, PageRequestDTO pageRequestDTO);

    Board_GraphicsDTO get(Long bno);

    void remove(Long bno);

    void modify(Board_GraphicsDTO boardDTO);

    default Board_Graphics dtoToEntity(Board_GraphicsDTO dto){

        Member member = Member.builder().id(dto.getWriter()).build();

        Board_Graphics board = Board_Graphics.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return board;
    }

    default Board_GraphicsDTO entityToDto(Board_Graphics board, Member member){

        Board_GraphicsDTO boardDTO = Board_GraphicsDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .modDate(board.getModDate())
                .writer(member.getId())
                .build();

        return boardDTO;
    }
}
