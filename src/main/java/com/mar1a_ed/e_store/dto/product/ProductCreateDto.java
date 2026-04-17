package com.mar1a_ed.e_store.dto.product;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductCreateDto {

    @NotBlank(message = "The field 'name' cannot be blank")
    @Size(min = 1, max = 200)
    private String name;

    @NotNull(message = "The field 'currentPrice' cannot be blank")
    @Digits(integer = 10, fraction = 2)
    @DecimalMin(value = "0.00")
    @DecimalMax(value = "9999999999.99")
    private BigDecimal currentPrice;

    @NotNull(message = "The field 'stockQuantity' cannot be blank")
    private Integer stockQuantity;

    private String category;
}
