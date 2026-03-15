package com.store.scheduler;

import com.store.entity.Customer;
import com.store.entity.CustomerAddress;
import com.store.entity.Order;
import com.store.repository.CustomerAddressRepository;
import com.store.repository.CustomerRepository.CustomerRepository;
import com.store.repository.orderRepository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class CustomerDeleteScheduler {

    private final CustomerRepository customerRepository;
    private final int TIMER = 60000 * 10;

    @Scheduled(fixedRate = TIMER, initialDelay = TIMER)
    @Transactional
    public void deleteInactiveCustomers() {
        LocalDateTime years = LocalDateTime.now().minusYears(3);
        List<Customer> customers = customerRepository.deleteInactiveCustomers(years);
        for (Customer customer : customers) {

            for (Order order : customer.getOrders()) {
                order.setCustomer(null);
            }

            CustomerAddress address = customer.getCustomerAddresses();
            if (address != null) {
                address.setCustomer(null);
            }

            customerRepository.delete(customer);
        }
    }
}
