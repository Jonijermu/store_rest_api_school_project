package com.store.service;

import com.store.dto.order.CreateOrderRequest;
import com.store.dto.order.CustomerOrdersDTO;
import com.store.dto.order.OrderDTO;
import com.store.dto.order.OrderProductRequest;
import com.store.dto.orderitem.OrderItemDTO;
import com.store.dto.product.ProductDTO;
import com.store.entity.*;
import com.store.mapper.OrderItemMapper;
import com.store.mapper.OrderMapper;
import com.store.mapper.ProductMapper;
import com.store.repository.CustomerAddressRepository;
import com.store.repository.CustomerRepository.CustomerRepository;
import com.store.repository.orderRepository.OrderRepository;
import com.store.repository.productRepository.ProductRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
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
    private final ProductMapper productMapper;
    private final OrderItemMapper orderItemMapper;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, OrderMapper ordersMapper, ProductRepository productRepository, CustomerAddressRepository customerAddressRepository, ProductMapper productMapper, OrderItemMapper orderItemMapper) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.ordersMapper = ordersMapper;
        this.productRepository = productRepository;
        this.customerAddressRepository = customerAddressRepository;
        this.productMapper = productMapper;
        this.orderItemMapper = orderItemMapper;
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
    public List<OrderItemDTO> getOrderItemsByOrderId(Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return orderItemMapper.toOrderItemDtoList(order.getOrderItems());
    }



    // todo default shipping date to change later
    @Transactional
    public OrderDTO createOrder(CreateOrderRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow();

        CustomerAddress address = customerAddressRepository.findByCustomerId(customer.getId());

        List<Integer> productIds = request.getProducts().stream()
                .map(OrderProductRequest::getProductId)
                .toList();

        List<Product> products = productRepository.findAllById(productIds);

        Map<Integer, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, p -> p));

        Order order = createOrderEntity(customer, address);

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
        int orderQuantity = checkProductAvailability(product, quantity);
        stockReduceFromOrder(product, orderQuantity);
        OrderItemId id = new OrderItemId();
        id.setOrderId(order.getId());
        id.setProductId(product.getId());
        return OrderItem.builder()
                .id(id)
                .order(order)
                .product(product)
                .quantity(orderQuantity)
                .unitPrice(product.getPrice())
                .build();

    }


    private void stockReduceFromOrder(Product product, int orderQuantity) {
        product.setStockQuantity(product.getStockQuantity() - orderQuantity);
    }


    private Order createOrderEntity(Customer customer, CustomerAddress address) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setShippingAddress(address);
        order.setOrderDate(new Date());
        return order;
    }


    private Integer checkProductAvailability(Product product, Integer orderQuantity) {
        productRepository.findById(product.getId())
                .orElseThrow(() -> new RuntimeException("Product with id: " + product.getId() + " not found"));
        if (product.isLocked()) {
            throw new RuntimeException("Product with id: " + product.getId() + " is locked");
        }
        orderQuantity = Math.min(orderQuantity, product.getStockQuantity());

        if (orderQuantity == 0) {
            throw new RuntimeException("Can't order product with id: "+product.getId() +" because stock is 0 ");
        }
        return orderQuantity;
    }
}
