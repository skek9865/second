package com.with.first.repository;

import com.with.first.dto.BoardDTO;
import com.with.first.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select b, w from Board b left join b.writer w where b.bno =:bno")
    Object getBoardWithWriter(@Param("bno") Long bno);

    @Query("select b,w from Board b left join b.writer w")
    Page<Object[]> getBoard(Pageable pageable);

    @Query("select b,w from Board b left join b.writer w where b.title Like %:keyword% or b.content Like %:keyword%")
    Page<Object[]> getBoardBySearch(@Param("keyword") String keyword, Pageable pageable);

    @Query("select b,w from Board_Vision b left join b.writer w where w.id Like %:writer%")
    Page<Object[]> getBoardBySearch_writer(@Param("writer") String writer, Pageable pageable);

//    @Query("select b from Board b where b.title Like %:keyword% or b.content Like %:keyword%")
//    List<Board> getBoardBySearch(@Param("keyword")String keyword, Sort sort);

}
