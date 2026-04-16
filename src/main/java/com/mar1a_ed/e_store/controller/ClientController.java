package com.mar1a_ed.e_store.controller;

import com.mar1a_ed.e_store.dto.client.ClientCreateDto;
import com.mar1a_ed.e_store.dto.client.ClientMapper;
import com.mar1a_ed.e_store.dto.client.ClientResponseDto;
import com.mar1a_ed.e_store.model.entity.Client;
import com.mar1a_ed.e_store.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/user_id={userId}")
    public ResponseEntity<ClientResponseDto> create(@PathVariable Long userId, @Valid @RequestBody ClientCreateDto clientCreateDto){
        Client client = ClientMapper.toClient(clientCreateDto);
        clientService.create(userId, client);
        return ResponseEntity.status(HttpStatus.CREATED).body(ClientMapper.toDto(client));
    }

    @GetMapping("/code={code}")
    public ResponseEntity<ClientResponseDto> findByCode(@PathVariable String code){
        Client client = clientService.findByCode(code);
        return ResponseEntity.status(HttpStatus.FOUND).body(ClientMapper.toDto(client));
    }
}
