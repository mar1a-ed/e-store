package com.mar1a_ed.e_store.service;

import com.mar1a_ed.e_store.dto.order.*;
import com.mar1a_ed.e_store.exception.ClientNotFoundException;
import com.mar1a_ed.e_store.exception.ProductNotFoundException;
import com.mar1a_ed.e_store.model.entity.Client;
import com.mar1a_ed.e_store.model.entity.Order;
import com.mar1a_ed.e_store.model.entity.OrderItem;
import com.mar1a_ed.e_store.model.entity.Product;
import com.mar1a_ed.e_store.model.enums.OrderStatus;
import com.mar1a_ed.e_store.repository.ClientRepository;
import com.mar1a_ed.e_store.repository.OrderRepository;
import com.mar1a_ed.e_store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;

    public OrderResponseDto create(String clientCode, OrderCreateDto orderCreateDto){
        Order order = new Order();
        if(orderCreateDto.getStatus() == null){
            order.setOrderStatus(OrderStatus.DONE);
        }

        Client client = clientRepository.findByCode(clientCode);
        order.setClient(client);

        for(OrderItemCreateDto items: orderCreateDto.getItemsOrder()){
            Product product = productRepository.findByCode(items.getProductCode());

            if(product == null){
                throw new ProductNotFoundException("Product not found");
            }


            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(items.getQuantity());
            orderItem.setSubTotal(product.getCurrentPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())));

            order.getItems().add(orderItem);
        }

        BigDecimal totalPrice = order.getItems().stream().map(OrderItem::getSubTotal).reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalPrice(totalPrice);

        orderRepository.save(order);

        List<OrderItemResponseDto> items = order.getItems().stream().map(orderItem -> new OrderItemResponseDto(
                orderItem.getProduct().getName(), orderItem.getQuantity(), orderItem.getSubTotal()
        )).toList();

        return new OrderResponseDto(order.getId(), order.getClient().getName(), items, order.getOrderStatus().toString(), order.getTotalPrice());
    }

    public List<OrderResponseDto> findByClientCode(String clientCode){

        List<Order> orders = orderRepository.findByClientCode(clientCode);

        List<OrderResponseDto> orderResponseDto = orders.stream().map(order -> new OrderResponseDto(
                order.getId(), order.getClient().getName(), order.getItems().stream().map(orderItem ->
                        new OrderItemResponseDto(orderItem.getProduct().getName(), orderItem.getQuantity(), orderItem.getSubTotal())).toList(),
                order.getOrderStatus().name(), order.getTotalPrice())).toList();

        return orderResponseDto;
    }
}
