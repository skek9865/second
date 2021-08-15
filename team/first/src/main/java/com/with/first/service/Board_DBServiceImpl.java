package com.with.first.service;

import com.with.first.dto.Board_DBDTO;
import com.with.first.dto.PageRequestDTO;
import com.with.first.dto.PageResultDTO;
import com.with.first.entity.Board_DB;
import com.with.first.entity.Member;
import com.with.first.repository.Board_DBRepository;
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
public class Board_DBServiceImpl implements Board_DBService{

    private final Board_DBRepository boardRepository;

    @Override
    public void register(Board_DBDTO dto) {

        log.info("dto : " + dto);

        Board_DB board = dtoToEntity(dto);

        boardRepository.save(board);

    }

    @Override
    public PageResultDTO<Board_DBDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        log.info("pageRequestDTO : " + pageRequestDTO);

        Function<Object[], Board_DBDTO> fn = (en -> entityToDto((Board_DB) en[0],(Member)en[1]));

        Page<Object[]> result = boardRepository.getBoard(pageRequestDTO.getPageable(Sort.by("bno").descending()));

        log.info("result : " + result);

        return new PageResultDTO<>(result,fn);
    }

    @Override
    public PageResultDTO<Board_DBDTO, Object[]> getListBySearch(String keyword, PageRequestDTO pageRequestDTO) {

        log.info("PageRequestDTO : " + pageRequestDTO);

        log.info("keyword : " + keyword);

        Function<Object[], Board_DBDTO> fn = (en -> entityToDto((Board_DB) en[0],(Member)en[1]));

        Page<Object[]> result = boardRepository.getBoardBySearch
                (keyword, pageRequestDTO.getPageable(Sort.by("bno").descending()));

        log.info("result : " + result);

        return new PageResultDTO<>(result,fn);
    }

    @Override
    public PageResultDTO<Board_DBDTO, Object[]> getListBySearch_writer(String writer, PageRequestDTO pageRequestDTO) {

        log.info("PageRequestDTO : " + pageRequestDTO);

        log.info("writer : " + writer);

        Function<Object[], Board_DBDTO> fn = (en -> entityToDto((Board_DB) en[0],(Member)en[1]));

        Page<Object[]> result = boardRepository.getBoardBySearch_writer
                (writer, pageRequestDTO.getPageable(Sort.by("bno").descending()));

        log.info("result : " + result);

        return new PageResultDTO<>(result,fn);
    }

    @Override
    public Board_DBDTO get(Long bno) {

        log.info("bno : " + bno);

        Object result = boardRepository.getBoardWithWriter(bno);

        Object [] arr = (Object[])result;

        return entityToDto((Board_DB) arr[0], (Member)arr[1]);
    }

    @Override
    public void remove(Long bno) {

        log.info("bno : " + bno);

        boardRepository.deleteById(bno);
    }

    @Override
    public void modify(Board_DBDTO boardDTO) {

        log.info(boardDTO);

        Optional<Board_DB> result = boardRepository.findById(boardDTO.getBno());

        Board_DB board = result.get();

        log.info("board : " + board);

        board.changeTitle(boardDTO.getTitle());
        board.changeContent(boardDTO.getContent());

        log.info("board : " + board);

        boardRepository.save(board);
    }
}
