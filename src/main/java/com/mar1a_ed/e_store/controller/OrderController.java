package com.mar1a_ed.e_store.controller;

import com.mar1a_ed.e_store.dto.order.OrderCreateDto;
import com.mar1a_ed.e_store.dto.order.OrderMapper;
import com.mar1a_ed.e_store.dto.order.OrderResponseDto;
import com.mar1a_ed.e_store.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/client_code={clientCode}")
    public ResponseEntity<OrderResponseDto> create(@PathVariable String clientCode, @Valid @RequestBody OrderCreateDto orderCreateDto){
        OrderResponseDto orderResponseDto = orderService.create(clientCode, orderCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDto);
    }

}
