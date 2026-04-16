package com.mar1a_ed.e_store.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientCreateDto {

    @NotBlank(message = "The 'name' cannot be blank.")
    @Size(min = 1, max = 200, message = "Enter full name")
    private String name;

    @NotBlank(message = "The 'CPF' cannot be blank.")
    @CPF
    private String cpf;

    @NotBlank(message = "The 'address' cannot be blank.")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ0-9.\\s]+,\\s*\\d+\\s*,\\s*[a-zA-ZÀ-ÿ0-9.\\s]+,\\s*[a-zA-ZÀ-ÿ0-9.\\s]+\\s*-\\s*[a-zA-ZÀ-ÿ\\s]{2,},\\s*[a-zA-ZÀ-ÿ\\s]+$",
            message = "Use the format: Street, Number, Neighborhood, City - State, Country")
    private String address;
}
