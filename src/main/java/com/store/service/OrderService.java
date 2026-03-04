package com.store.service;

import com.store.dto.customer.CompanyCustomerDTO;
import com.store.dto.order.CreateOrderRequest;
import com.store.dto.order.CustomerOrdersDTO;
import com.store.dto.order.OrderDTO;
import com.store.dto.order.OrderProductRequest;
import com.store.entity.*;
import com.store.mapper.OrderMapper;
import com.store.repository.CustomerAddressRepository;
import com.store.repository.CustomerRepository;
import com.store.repository.OrderRepository;
import com.store.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderMapper ordersMapper;
    private final ProductRepository productRepository;
    private final CustomerAddressRepository customerAddressRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, OrderMapper ordersMapper, ProductRepository productRepository, CustomerAddressRepository customerAddressRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.ordersMapper = ordersMapper;
        this.productRepository = productRepository;
        this.customerAddressRepository = customerAddressRepository;
    }

    public CustomerOrdersDTO getAllCustomerOrdersByCustomerId(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow();

        if (customer instanceof PrivateCustomer privateCustomer) {
            List<Order> orders = orderRepository.findOrderByCustomerId(customerId);
            return ordersMapper.toPrivateCustomerOrderDto(privateCustomer, orders);
        }

        if (customer instanceof CompanyCustomer companyCustomer) {
            List<Order> orders = orderRepository.findOrderByCustomerId(customerId);
            return ordersMapper.toCompanyCustomerOrderDto(companyCustomer, orders);
        }

        throw new RuntimeException("Failed to load customer orders");
    }

    //todo
    public void getOrderProductsByOrderId() {
    }

    //todo fix teh stock when order is successfull
    // todo default shipping date to change later
    public OrderDTO createOrder(CreateOrderRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow();

        CustomerAddress address = customerAddressRepository.findByCustomerId(customer.getId());

        List<Integer> productIds = request.getProducts().stream()
                .map(OrderProductRequest::getProductId)
                .toList();

        List<Product> products = productRepository.findAllById(productIds);

        Map<Integer, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, p -> p));

        Order order = new Order();
        order.setCustomer(customer);
        order.setShippingAddress(address);
        order.setOrderDate(new Date());
        List<OrderItem> orderItems = request.getProducts().stream()
                        .map(req -> createOrderItems(
                                productMap.get(req.getProductId()),
                                req.getQuantity(), order

                        )).toList();

        order.setOrderItems(orderItems);
        orderRepository.save(order);
        return ordersMapper.toOrderDto(order);
    }

    private OrderItem createOrderItems(
            Product product,
            Integer quantity,
            Order order
    ) {
        OrderItemId id = new OrderItemId();
        id.setOrderId(order.getId());
        id.setProductId(product.getId());
        return OrderItem.builder()
                .id(id)
                .order(order)
                .product(product)
                .quantity(quantity)
                .unitPrice(product.getPrice())
                .build();

    }
}
