
package com.example.store.controllers;

import com.example.store.dtos.OrderRequest;
import com.example.store.models.Order;
import com.example.store.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Obtener todos los pedidos
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(
                orderService.getAllOrders()
        );
    }

    // Obtener un pedido por ID
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(
            @PathVariable Long orderId) {

        return ResponseEntity.ok(
                orderService.getOrderById(orderId)
        );
    }

    // Crear un pedido
    @PostMapping
    public ResponseEntity<Order> createOrder(
            @RequestBody OrderRequest orderRequest) {

        Order createdOrder =
                orderService.createOrder(orderRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdOrder.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(createdOrder);
    }

    // Actualizar un pedido
    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(
            @PathVariable Long orderId,
            @RequestBody OrderRequest orderRequest) {

        return ResponseEntity.ok(
                orderService.updateOrder(orderId, orderRequest)
        );
    }

    // Eliminar un pedido
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Order> deleteOrder(
            @PathVariable Long orderId) {

        return ResponseEntity.ok(
                orderService.deleteOrder(orderId)
        );
    }
}
