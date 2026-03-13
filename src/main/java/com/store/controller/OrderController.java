package com.store.controller;

import com.store.dto.order.CreateOrderRequest;
import com.store.dto.order.CustomerOrdersDTO;
import com.store.dto.order.OrderDTO;
import com.store.dto.orderitem.OrderItemDTO;
import com.store.dto.product.ProductDTO;
import com.store.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerOrdersDTO> getAllCustomerOrders(
            @PathVariable int customerId
    ) {
        CustomerOrdersDTO customerOrdersDTO = orderService.getAllCustomerOrdersByCustomerId(customerId);
        return ResponseEntity.ok(customerOrdersDTO);

    }

    @PostMapping()
    public ResponseEntity<OrderDTO> createOrder(
            @RequestBody CreateOrderRequest request
            ) {
        OrderDTO orderDTO = orderService.createOrder(request);
        return ResponseEntity.ok(orderDTO);
    }

    @GetMapping("/items/{orderId}")
    public ResponseEntity<List<OrderItemDTO>> getOrderItems(
            @PathVariable Integer orderId
    ) {
        List<OrderItemDTO> items = orderService.getOrderItemsByOrderId(orderId);
        return ResponseEntity.ok(items);

    }
}
