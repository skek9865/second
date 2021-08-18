package com.with.first.service;

import com.with.first.dto.Board_AIDTO;
import com.with.first.dto.PageRequestDTO;
import com.with.first.dto.PageResultDTO;
import com.with.first.entity.Board_AI;
import com.with.first.repository.Board_AIRepository;
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
public class Board_AIServiceImpl implements Board_AIService{

    private final Board_AIRepository boardRepository;

    @Override
    public void register(Board_AIDTO dto) {

        log.info("dto : " + dto);

        Board_AI board = dtoToEntity(dto);

        boardRepository.save(board);
    }

    @Override
    public PageResultDTO<Board_AIDTO, Object> getList(PageRequestDTO pageRequestDTO) {

        log.info("pageRequestDTO : " + pageRequestDTO);

        Function<Object, Board_AIDTO> fn = (en -> entityToDto((Board_AI) en));

        Page<Object> result = boardRepository.getBoardList(pageRequestDTO.getPageable(Sort.by("bno").descending()));

        log.info("result : " + result);

        return new PageResultDTO<>(result,fn);
    }

    @Override
    public PageResultDTO<Board_AIDTO, Object> getListBySearch(String keyword, PageRequestDTO pageRequestDTO) {

        log.info("PageRequestDTO : " + pageRequestDTO);

        log.info("keyword : " + keyword);

        Function<Object, Board_AIDTO> fn = (en -> entityToDto((Board_AI) en));

        Page<Object> result = boardRepository.getBoardBySearch
                (keyword, pageRequestDTO.getPageable(Sort.by("bno").descending()));

        log.info("result : " + result);

        return new PageResultDTO<>(result,fn);
    }

    @Override
    public PageResultDTO<Board_AIDTO, Object> getListBySearch_writer(String writer, PageRequestDTO pageRequestDTO) {

        log.info("PageRequestDTO : " + pageRequestDTO);

        log.info("writer : " + writer);

        Function<Object, Board_AIDTO> fn = (en -> entityToDto((Board_AI) en));

        Page<Object> result = boardRepository.getBoardBySearch_writer
                (writer, pageRequestDTO.getPageable(Sort.by("bno").descending()));

        log.info("result : " + result);

        return new PageResultDTO<>(result,fn);
    }

    @Override
    public Board_AIDTO get(Long bno) {

        log.info("bno : " + bno);

        Object result = boardRepository.getBoard(bno);

        return entityToDto((Board_AI)result);
    }

    @Override
    public void remove(Long bno) {

        log.info("bno : " + bno);

        boardRepository.deleteById(bno);
    }

    @Override
    public void modify(Board_AIDTO boardDTO) {

        log.info(boardDTO);

        Optional<Board_AI> result = boardRepository.findById(boardDTO.getBno());

        Board_AI board = result.get();

        log.info("board : " + board);

        board.changeTitle(boardDTO.getTitle());
        board.changeContent(boardDTO.getContent());

        log.info("board : " + board);

        boardRepository.save(board);
    }
}
