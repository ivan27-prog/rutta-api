package com.example.store.controllers;

import com.example.store.dtos.OrderDetailsRequest;
import com.example.store.models.OrderDetails;
import com.example.store.services.OrderDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/order-details") // http://localhost:8080/api/order-details
@CrossOrigin(origins = "*")
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    @Autowired
    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDetails>> getAllOrderDetails() {
        List<OrderDetails> orderDetailsList = orderDetailsService.getAllOrderDetails();
        return ResponseEntity.ok(orderDetailsList);
    }

    @GetMapping(path = "{orderDetailsId}")
    public ResponseEntity<OrderDetails> getOrderDetailsById(@PathVariable("orderDetailsId") Long id) {
        OrderDetails orderDetails = orderDetailsService.getOrderDetailsById(id);
        return ResponseEntity.ok(orderDetails);
    }

    @PostMapping
    public ResponseEntity<OrderDetails> createOrderDetails(@Valid @RequestBody OrderDetailsRequest orderDetailsRequest) {
        OrderDetails orderDetails = orderDetailsService.createOrderDetails(orderDetailsRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(orderDetails.getId())
                .toUri();
        return ResponseEntity.created(location).body(orderDetails);
    }

    @PutMapping(path = "{orderDetailsId}")
    public ResponseEntity<OrderDetails> updateOrderDetailsById(
            @PathVariable("orderDetailsId") Long id,
            @RequestBody OrderDetailsRequest orderDetailsRequest) {
        OrderDetails orderDetails = orderDetailsService.updateOrderDetails(id, orderDetailsRequest);
        return ResponseEntity.ok(orderDetails);
    }

    @DeleteMapping(path = "{orderDetailsId}")
    public ResponseEntity<OrderDetails> deleteOrderDetailsById(@PathVariable("orderDetailsId") Long id) {
        OrderDetails orderDetails = orderDetailsService.deleteOrderDetailsById(id);
        return ResponseEntity.ok(orderDetails);
    }
}