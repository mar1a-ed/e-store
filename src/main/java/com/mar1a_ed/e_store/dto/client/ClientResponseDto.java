package com.mar1a_ed.e_store.dto.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientResponseDto {

    private String code;

    private String name;

    private String cpf;

    private String address;
}
