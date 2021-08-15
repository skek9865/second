package com.with.first.repository;

import com.with.first.entity.Board_Vision;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Board_VisionRepository extends JpaRepository<Board_Vision, Long>{

    @Query("select b, w from Board_Vision b left join b.writer w where b.bno =:bno")
    Object getBoardWithWriter(@Param("bno") Long bno);

    @Query("select b,w from Board_Vision b left join b.writer w")
    Page<Object[]> getBoard(Pageable pageable);

    @Query("select b,w from Board_Vision b left join b.writer w where b.title Like %:keyword% or b.content Like %:keyword%")
    Page<Object[]> getBoardBySearch(@Param("keyword")String keyword, Pageable pageable);

    @Query("select b,w from Board_Vision b left join b.writer w where w.id Like %:writer%")
    Page<Object[]> getBoardBySearch_writer(@Param("writer")String writer, Pageable pageable);
}
