package com.with.first.service;

import com.with.first.dto.*;
import com.with.first.entity.Board_Vision;
import com.with.first.entity.Member;
public interface Board_VisionService {

    void register(Board_VisionDTO dto);

    PageResultDTO<Board_VisionDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    PageResultDTO<Board_VisionDTO, Object[]> getListBySearch(String keyword, PageRequestDTO pageRequestDTO);

    PageResultDTO<Board_VisionDTO, Object[]> getListBySearch_writer(String writer, PageRequestDTO pageRequestDTO);

    Board_VisionDTO get(Long bno);

    void remove(Long bno);

    void modify(Board_VisionDTO boardDTO);

    default Board_Vision dtoToEntity(Board_VisionDTO dto){

        Member member = Member.builder().id(dto.getWriter()).build();

        Board_Vision board = Board_Vision.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return board;
    }

    default Board_VisionDTO entityToDto(Board_Vision board, Member member){

        Board_VisionDTO boardDTO = Board_VisionDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .modDate(board.getModDate())
                .writer(member.getId())
                .build();

        return boardDTO;
    }
}
