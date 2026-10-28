package com.giuli.tienda.service;

import com.giuli.tienda.dto.OrderDetailRequest;
import com.giuli.tienda.dto.OrderRequest;
import com.giuli.tienda.dto.OrderResponse;
import com.giuli.tienda.entity.OrderDetail;
import com.giuli.tienda.entity.Order;
import com.giuli.tienda.entity.Product;
import com.giuli.tienda.exception.InvalidQuantityException;
import com.giuli.tienda.exception.ProductNotFoundException;
import com.giuli.tienda.repository.OrderRepository;
import com.giuli.tienda.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    // Dependency injection
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    // Create and save an order with the response we prepared in the class
    public OrderResponse createOrder(OrderRequest orderRequest) {
        // Create the order to assign its details first and then save it
        Order order = new Order();

        // Iterate over orderRequest to build OrderDetail objects
        for (OrderDetailRequest orderDetailRequest : orderRequest.details()) {
            // Check that no negative product quantity was entered
            if (orderDetailRequest.quantity() <= 0) {
                throw new InvalidQuantityException("Negative product quantity");
            }

            // Find the product associated with each detail request
            Product product = productRepository
                    .findById(orderDetailRequest.productId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));

            // Create the detail
            OrderDetail orderDetail = new OrderDetail(product, orderDetailRequest.quantity(), product.getPrice());

            // Add the created details to the order
            order.addDetail(orderDetail);

        }

        // The order is ready, now save it -> the id is generated here because it was sent to the database
        Order savedOrder = orderRepository.save(order); // save returns the entity
        // Now use it to create the OrderResponse
        return new OrderResponse(savedOrder.getId(), savedOrder.getDate(), order.calculateTotal());
    }
}
