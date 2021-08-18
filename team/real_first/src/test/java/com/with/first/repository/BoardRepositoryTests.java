package com.with.first.repository;

import com.with.first.entity.Board;
import com.with.first.entity.Board_AI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository repository;

    @Autowired
    private Board_AIRepository repository_AI;

    @Test
    public void register(){

        IntStream.rangeClosed(1,100).forEach(i -> {

            Board board = Board.builder()
                    .title("Test...." + i)
                    .content("Test Content..." + i)
                    .writer("User" + i)
                    .password("1234")
                    .build();

            System.out.println("board : " + board);

            repository.save(board);
        });
    }

    @Test
    public void register_AI(){

        IntStream.rangeClosed(1,100).forEach(i -> {

            Board_AI board_AI = Board_AI.builder()
                    .title("Test...." + i)
                    .content("Test Content..." + i)
                    .writer("User" + i)
                    .password("1234")
                    .build();

            System.out.println("board : " + board_AI);

            repository_AI.save(board_AI);
        });
    }
}
