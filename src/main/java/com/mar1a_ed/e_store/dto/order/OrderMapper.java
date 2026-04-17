package com.mar1a_ed.e_store.dto.order;

import com.mar1a_ed.e_store.model.entity.Order;
import org.modelmapper.ModelMapper;

public class OrderMapper {

    public static Order toOrder(OrderCreateDto dto){
        return new ModelMapper().map(dto, Order.class);
    }

    public static OrderResponseDto toDto(Order order){
        return new ModelMapper().map(order, OrderResponseDto.class);
    }
}
