package com.with.first.service;

import com.with.first.dto.*;
import com.with.first.entity.Board_Network;
import com.with.first.entity.Member;
public interface Board_NetworkService {

    void register(Board_NetworkDTO dto);

    PageResultDTO<Board_NetworkDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    PageResultDTO<Board_NetworkDTO, Object[]> getListBySearch(String keyword, PageRequestDTO pageRequestDTO);

    PageResultDTO<Board_NetworkDTO, Object[]> getListBySearch_writer(String writer, PageRequestDTO pageRequestDTO);

    Board_NetworkDTO get(Long bno);

    void remove(Long bno);

    void modify(Board_NetworkDTO boardDTO);

    default Board_Network dtoToEntity(Board_NetworkDTO dto){

        Member member = Member.builder().id(dto.getWriter()).build();

        Board_Network board = Board_Network.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return board;
    }

    default Board_NetworkDTO entityToDto(Board_Network board, Member member){

        Board_NetworkDTO boardDTO = Board_NetworkDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .modDate(board.getModDate())
                .writer(member.getId())
                .build();

        return boardDTO;
    }
}
