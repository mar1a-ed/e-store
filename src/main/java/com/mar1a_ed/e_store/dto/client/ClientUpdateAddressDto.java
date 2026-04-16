package com.mar1a_ed.e_store.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientUpdateAddressDto {

    @NotBlank(message = "The 'currentAddress' cannot be blank.")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ0-9.\\s]+,\\s*\\d+\\s*,\\s*[a-zA-ZÀ-ÿ0-9.\\s]+,\\s*[a-zA-ZÀ-ÿ0-9.\\s]+\\s*-\\s*[a-zA-ZÀ-ÿ\\s]{2,},\\s*[a-zA-ZÀ-ÿ\\s]+$",
            message = "Use the format: Street, Number, Neighborhood, City - State, Country")
    private String currentAddress;

    @NotBlank(message = "The 'newAddress' cannot be blank.")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ0-9.\\s]+,\\s*\\d+\\s*,\\s*[a-zA-ZÀ-ÿ0-9.\\s]+,\\s*[a-zA-ZÀ-ÿ0-9.\\s]+\\s*-\\s*[a-zA-ZÀ-ÿ\\s]{2,},\\s*[a-zA-ZÀ-ÿ\\s]+$",
            message = "Use the format: Street, Number, Neighborhood, City - State, Country")
    private String newAddress;
}
