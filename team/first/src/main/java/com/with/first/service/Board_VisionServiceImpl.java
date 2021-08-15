package com.with.first.service;

import com.with.first.dto.Board_VisionDTO;
import com.with.first.dto.PageRequestDTO;
import com.with.first.dto.PageResultDTO;
import com.with.first.entity.Board_Vision;
import com.with.first.entity.Member;
import com.with.first.repository.Board_VisionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class Board_VisionServiceImpl implements Board_VisionService{

    private final Board_VisionRepository boardRepository;

    @Override
    public void register(Board_VisionDTO dto) {

        log.info("dto : " + dto);

        Board_Vision board = dtoToEntity(dto);

        boardRepository.save(board);
    }

    @Override
    public PageResultDTO<Board_VisionDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        log.info("pageRequestDTO : " + pageRequestDTO);

        Function<Object[], Board_VisionDTO> fn = (en -> entityToDto((Board_Vision) en[0],(Member)en[1]));

        Page<Object[]> result = boardRepository.getBoard(pageRequestDTO.getPageable(Sort.by("bno").descending()));

        log.info("result : " + result);

        return new PageResultDTO<>(result,fn);
    }

    @Override
    public PageResultDTO<Board_VisionDTO, Object[]> getListBySearch(String keyword, PageRequestDTO pageRequestDTO) {

        log.info("PageRequestDTO : " + pageRequestDTO);

        log.info("keyword : " + keyword);

        Function<Object[], Board_VisionDTO> fn = (en -> entityToDto((Board_Vision) en[0],(Member)en[1]));

        Page<Object[]> result = boardRepository.getBoardBySearch
                (keyword, pageRequestDTO.getPageable(Sort.by("bno").descending()));

        log.info("result : " + result);

        return new PageResultDTO<>(result,fn);
    }

    @Override
    public PageResultDTO<Board_VisionDTO, Object[]> getListBySearch_writer(String writer, PageRequestDTO pageRequestDTO) {

        log.info("PageRequestDTO : " + pageRequestDTO);

        log.info("writer : " + writer);

        Function<Object[], Board_VisionDTO> fn = (en -> entityToDto((Board_Vision) en[0],(Member)en[1]));

        Page<Object[]> result = boardRepository.getBoardBySearch_writer
                (writer, pageRequestDTO.getPageable(Sort.by("bno").descending()));

        log.info("result : " + result);

        return new PageResultDTO<>(result,fn);
    }

    @Override
    public Board_VisionDTO get(Long bno) {

        log.info("bno : " + bno);

        Object result = boardRepository.getBoardWithWriter(bno);

        Object [] arr = (Object[])result;

        return entityToDto((Board_Vision) arr[0], (Member)arr[1]);
    }

    @Override
    public void remove(Long bno) {

        log.info("bno : " + bno);

        boardRepository.deleteById(bno);
    }

    @Override
    public void modify(Board_VisionDTO boardDTO) {

        log.info(boardDTO);

        Optional<Board_Vision> result = boardRepository.findById(boardDTO.getBno());

        Board_Vision board = result.get();

        log.info("board : " + board);

        board.changeTitle(boardDTO.getTitle());
        board.changeContent(boardDTO.getContent());

        log.info("board : " + board);

        boardRepository.save(board);
    }
}
