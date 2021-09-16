package com.with.second.service;

import com.with.second.dto.OrderDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class OrderServiceTests {

    @Autowired
    private OrderService service;

    @Test
    public void register(){

        IntStream.rangeClosed(1,10).forEach(i -> {
            OrderDto dto = OrderDto.builder()
                    .bno(1L)
                    .bookName("자바 ORM 표준 JPA 프로그래밍")
                    .userId("User2")
                    .status("배송중")
                    .build();

            service.register(dto);
        });

    }

    @Test
    public void getList(){

        List<OrderDto> list = service.getList("User2");

        for(OrderDto dto : list){

            System.out.println("dto : " + dto);
        }
    }

}
