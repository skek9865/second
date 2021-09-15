package com.with.second.service;

import com.with.second.dto.OrderDto;
import com.with.second.entity.OrderEntity;
import com.with.second.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    public void register(OrderDto dto) {

        log.info("dto : " + dto);

        OrderEntity orderEntity = dtoToEntity(dto);

        log.info("orderEntity : " + orderEntity);

        orderRepository.save(orderEntity);
    }

    @Override
    public List<OrderDto> getList(String id) {

        List<Object[]> results = orderRepository.getList(id, Sort.by("ono").descending());

        List<OrderDto> orderDtos = new ArrayList<>();

        for(Object[] result : results){

            OrderDto orderDto = entityToDTO((OrderEntity) result[0], (String)result[1]);

            orderDtos.add(orderDto);
        }

        return orderDtos;
    }

    @Override
    public OrderDto read(Long ono) {

        log.info("ono : " + ono);

        List<Object[]> list = orderRepository.read(ono, Sort.by("ono").descending());

        OrderEntity entity = (OrderEntity) list.get(0)[0];

        String name = (String) list.get(0)[1];

        OrderDto orderDto = entityToDTO(entity, name);

        return orderDto;
    }

    @Override
    public void remove(Long ono) {

        log.info("ono : " + ono);

        orderRepository.deleteById(ono);
    }
}