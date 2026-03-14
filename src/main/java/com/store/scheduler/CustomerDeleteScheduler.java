package com.store.scheduler;

import com.store.entity.Customer;
import com.store.repository.CustomerRepository.CustomerRepository;
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
        LocalDateTime twoYearsAgo = LocalDateTime.now().minusYears(2);
        List<Customer> customers = customerRepository.deleteInactiveCustomers(twoYearsAgo);
        customerRepository.deleteAll(customers);
    }
}
