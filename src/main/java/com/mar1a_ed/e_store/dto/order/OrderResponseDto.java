package com.mar1a_ed.e_store.dto.order;

import com.mar1a_ed.e_store.model.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderResponseDto {

    private Long id;

    private String nameClient;

    private List<OrderItemResponseDto> itemsOrder;

    private String status;

    private BigDecimal totalPrice;
}
