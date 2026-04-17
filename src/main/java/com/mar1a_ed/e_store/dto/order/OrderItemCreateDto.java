package com.mar1a_ed.e_store.dto.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemCreateDto {

    @NotBlank(message = "The field cannot be blank")
    private String productCode;

    @NotNull(message = "The field cannot be null")
    @Positive
    private Integer quantity;
}
