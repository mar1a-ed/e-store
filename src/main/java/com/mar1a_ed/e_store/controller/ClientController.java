package com.mar1a_ed.e_store.controller;

import com.mar1a_ed.e_store.dto.client.ClientCreateDto;
import com.mar1a_ed.e_store.dto.client.ClientMapper;
import com.mar1a_ed.e_store.dto.client.ClientResponseDto;
import com.mar1a_ed.e_store.dto.client.ClientUpdateAddressDto;
import com.mar1a_ed.e_store.model.entity.Client;
import com.mar1a_ed.e_store.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/full_name={fullName}")
    public ResponseEntity<ClientResponseDto> findByFullName(@PathVariable String fullName){
        Client client = clientService.findByFullName(fullName);
        ClientResponseDto clientResponseDto = ClientMapper.toDto(client);
        return ResponseEntity.status(HttpStatus.FOUND).body(clientResponseDto);
    }

    @GetMapping("/name={name}")
    public ResponseEntity<List<ClientResponseDto>> findByNameContaining(@PathVariable String name){
        List<Client> clients = clientService.findByNameContaining(name);
        List<ClientResponseDto> clientsDto = ClientMapper.toDtoList(clients);
        return ResponseEntity.ok(clientsDto);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDto>> findAll(){
        List<Client> clients = clientService.findAll();
        return ResponseEntity.ok(ClientMapper.toDtoList(clients));
    }

    @PatchMapping("/update-address/name={name}")
    public ResponseEntity<Void> updateAddress(@PathVariable String name,
                                              @Valid @RequestBody ClientUpdateAddressDto clientUpdateAddressDto){
        clientService.updateAddress(name, clientUpdateAddressDto.getCurrentAddress(), clientUpdateAddressDto.getNewAddress());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/name={name}")
    public ResponseEntity<Void> deleteClient(@PathVariable String name){
        clientService.deleteClient(name);
        return ResponseEntity.noContent().build();
    }
}
