
package com.example.store.services;

import com.example.store.dtos.OrderRequest;
import com.example.store.exceptions.ResourceNotFoundException;
import com.example.store.models.Order;
import com.example.store.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Obtener todos los pedidos
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Obtener un pedido por ID
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Pedido con id " + id + " no encontrado"
                        ));
    }

    // Crear un pedido a partir del DTO
    public Order createOrder(OrderRequest request) {
        Order order = new Order();

        order.setOrderDate(request.orderDate());
        order.setOrderStatus(request.orderStatus());
        order.setOrderAmount(request.orderAmount());

        return orderRepository.save(order);
    }

    // Actualizar un pedido
    public Order updateOrder(Long id, OrderRequest request) {
        Order existingOrder = getOrderById(id);

        if (request.orderDate() != null) {
            existingOrder.setOrderDate(request.orderDate());
        }

        if (request.orderStatus() != null) {
            existingOrder.setOrderStatus(request.orderStatus());
        }

        if (request.orderAmount() != null) {
            existingOrder.setOrderAmount(request.orderAmount());
        }

        return orderRepository.save(existingOrder);
    }

    // Eliminar un pedido
    public Order deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
        return order;
    }
}
