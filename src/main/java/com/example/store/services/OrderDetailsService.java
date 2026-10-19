package com.example.store.services;

import com.example.store.dtos.OrderDetailsRequest;
import com.example.store.exceptions.ResourceNotFoundException;
import com.example.store.models.Order;
import com.example.store.models.OrderDetails;
import com.example.store.models.Product;
import com.example.store.repository.OrderDetailsRepository;
import com.example.store.repository.OrderRepository;
import com.example.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository,
                               OrderRepository orderRepository,
                               ProductRepository productRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailsRepository.findAll();
    }

    public OrderDetails getOrderDetailsById(Long id) {
        return orderDetailsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Detalle de pedido con el id " + id + " no encontrado")
        );
    }

    public OrderDetails createOrderDetails(OrderDetailsRequest newOrderDetails) {
        Order order = orderRepository.findById(newOrderDetails.orderId()).orElseThrow(
                () -> new ResourceNotFoundException("Pedido con el id " + newOrderDetails.orderId() + " no encontrado")
        );
        Product product = productRepository.findById(newOrderDetails.productId()).orElseThrow(
                () -> new ResourceNotFoundException("Producto con el id " + newOrderDetails.productId() + " no encontrado")
        );

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setQuantity(newOrderDetails.quantity());
        orderDetails.setUnitPrice(newOrderDetails.unitPrice());
        orderDetails.setOrder(order);
        orderDetails.setProduct(product);
        return orderDetailsRepository.save(orderDetails);
    }

    public OrderDetails updateOrderDetails(Long id, OrderDetailsRequest updateOrderDetails) {
        OrderDetails oldOrderDetails = orderDetailsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Detalle de pedido con el id " + id + " no encontrado")
        );
        if (updateOrderDetails.quantity() != null) {
            oldOrderDetails.setQuantity(updateOrderDetails.quantity());
        }
        if (updateOrderDetails.unitPrice() != null) {
            oldOrderDetails.setUnitPrice(updateOrderDetails.unitPrice());
        }
        if (updateOrderDetails.orderId() != null) {
            Order order = orderRepository.findById(updateOrderDetails.orderId()).orElseThrow(
                    () -> new ResourceNotFoundException("Pedido con el id " + updateOrderDetails.orderId() + " no encontrado")
            );
            oldOrderDetails.setOrder(order);
        }
        if (updateOrderDetails.productId() != null) {
            Product product = productRepository.findById(updateOrderDetails.productId()).orElseThrow(
                    () -> new ResourceNotFoundException("Producto con el id " + updateOrderDetails.productId() + " no encontrado")
            );
            oldOrderDetails.setProduct(product);
        }
        return orderDetailsRepository.save(oldOrderDetails);
    }

    public OrderDetails deleteOrderDetailsById(Long id) {
        OrderDetails orderDetails = orderDetailsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Detalle de pedido con el id " + id + " no encontrado")
        );
        orderDetailsRepository.delete(orderDetails);
        return orderDetails;
    }
}