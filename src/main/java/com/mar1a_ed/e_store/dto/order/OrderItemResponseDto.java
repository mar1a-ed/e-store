package com.mar1a_ed.e_store.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemResponseDto {

    private String productName;

    private Integer quantity;

    private BigDecimal subTotal;
}
