package com.with.first.service;

import com.with.first.dto.BoardDTO;
import com.with.first.dto.PageRequestDTO;
import com.with.first.dto.PageResultDTO;
import com.with.first.entity.Board;
import com.with.first.entity.Member;
import com.with.first.repository.BoardRepository;
import com.with.first.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public void register(BoardDTO dto) {

        log.info("dto : " + dto);

        Board board = dtoToEntity(dto);

        boardRepository.save(board);
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        log.info("pageRequestDTO : " + pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDto((Board)en[0],(Member)en[1]));

        Page<Object[]> result = boardRepository.getBoard(pageRequestDTO.getPageable(Sort.by("bno").descending()));

        log.info("result : " + result);

        return new PageResultDTO<>(result,fn);
    }

//    @Override
//    public List<BoardDTO> getList() {
//
//        log.info("getLIst");
//
//        List<Board> results = boardRepository.findAll(Sort.by("bno").descending());
//
//        List<BoardDTO> boardDTOS = new ArrayList<>();
//
//        for(Board result : results){
//
//            BoardDTO boardDTO = BoardDTO.builder()
//                    .bno(result.getBno())
//                    .title(result.getTitle())
//                    .content(result.getContent())
//                    .writer(result.getWriter().getId())
//                    .modDate(result.getModDate())
//                    .build();
//
//            boardDTOS.add(boardDTO);
//        }
//
//        return boardDTOS;
//    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getListBySearch(String keyword, PageRequestDTO pageRequestDTO) {

        log.info("PageRequestDTO : " + pageRequestDTO);

        log.info("keyword : " + keyword);

        Function<Object[], BoardDTO> fn = (en -> entityToDto((Board)en[0],(Member)en[1]));

        Page<Object[]> result = boardRepository.getBoardBySearch
                (keyword, pageRequestDTO.getPageable(Sort.by("bno").descending()));

        log.info("result : " + result);

        return new PageResultDTO<>(result,fn);
    }

//    @Override
//    public List<BoardDTO> getListBySearch(String keyword) {
//
//        log.info("getListBySearch");
//
//        log.info("keyword : " + keyword);
//
//        List<Board> results = boardRepository.getBoardBySearch(keyword, Sort.by("bno").descending());
//
//        List<BoardDTO> boardDTOS = new ArrayList<>();
//
//        for(Board result : results){
//
//            BoardDTO boardDTO = BoardDTO.builder()
//                    .bno(result.getBno())
//                    .title(result.getTitle())
//                    .content(result.getContent())
//                    .writer(result.getWriter().getId())
//                    .modDate(result.getModDate())
//                    .build();
//
//            boardDTOS.add(boardDTO);
//        }
//
//        return boardDTOS;
//    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getListBySearch_writer(String writer, PageRequestDTO pageRequestDTO) {

        log.info("PageRequestDTO : " + pageRequestDTO);

        log.info("writer : " + writer);

        Function<Object[], BoardDTO> fn = (en -> entityToDto((Board)en[0],(Member)en[1]));

        Page<Object[]> result = boardRepository.getBoardBySearch_writer
                (writer, pageRequestDTO.getPageable(Sort.by("bno").descending()));

        log.info("result : " + result);

        return new PageResultDTO<>(result,fn);
    }

//    @Override
//    public List<BoardDTO> getListBySearch_writer(String writer) {
//
//        log.info("getList");
//
//        log.info("writer : " + writer);
//
//        List<Board> results = boardRepository.getBoardBySearch_writer(writer, Sort.by("bno").descending());
//
//        List<BoardDTO> boardDTOS = new ArrayList<>();
//
//        for(Board result : results){
//
//            BoardDTO boardDTO = BoardDTO.builder()
//                    .bno(result.getBno())
//                    .title(result.getTitle())
//                    .content(result.getContent())
//                    .writer(result.getWriter().getId())
//                    .modDate(result.getModDate())
//                    .build();
//
//            boardDTOS.add(boardDTO);
//        }
//
//        return boardDTOS;
//    }

    @Override
    public BoardDTO get(Long bno) {

        log.info("bno : " + bno);

        Object result = boardRepository.getBoardWithWriter(bno);

        Object [] arr = (Object[])result;

        return entityToDto((Board)arr[0], (Member)arr[1]);
    }

    @Override
    public void remove(Long bno) {

        log.info("bno : " + bno);

        boardRepository.deleteById(bno);
    }

    @Override
    public void modify(BoardDTO boardDTO) {

        log.info(boardDTO);

        Optional<Board> result = boardRepository.findById(boardDTO.getBno());

        Board board = result.get();

        log.info("board : " + board);

        board.changeTitle(boardDTO.getTitle());
        board.changeContent(boardDTO.getContent());

        log.info("board : " + board);

        boardRepository.save(board);
    }
}
