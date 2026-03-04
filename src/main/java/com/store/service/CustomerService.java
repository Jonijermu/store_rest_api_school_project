package com.store.service;


import com.store.dto.customer.CreateCustomerRequest;
import com.store.dto.customer.CustomerDTO;
import com.store.entity.CompanyCustomer;
import com.store.entity.Customer;
import com.store.entity.CustomerAddress;
import com.store.entity.PrivateCustomer;
import com.store.mapper.CustomerMapper;
import com.store.repository.CustomerAddressRepository;
import com.store.repository.CustomerRepository;
import com.store.utils.enums.CustomerType;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    public CustomerService(
            CustomerRepository customerRepository,
            CustomerMapper customerMapper
    ) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerDTO findCustomerById(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow();

        if (customer instanceof PrivateCustomer privateCustomer) {
            return customerMapper.toPrivateCustomerDto(privateCustomer);
        }

        if (customer instanceof CompanyCustomer companyCustomer) {
            return customerMapper.toCompanyCustomerDto(companyCustomer);
        }
        throw new IllegalStateException("Could not find customer");
    }


    public CustomerDTO saveCustomer(CreateCustomerRequest request) {

        if (request.getType().equals(CustomerType.PRIVATE)) {
            PrivateCustomer customer = new PrivateCustomer();
            setCommonFields(customer, request);
            saveCustomerAddress(customer, request);
            return customerMapper.toPrivateCustomerDto(
                    customerRepository.save(customer)
            );
        }

        if (request.getType().equals(CustomerType.COMPANY)) {
            CompanyCustomer customer = new CompanyCustomer();
            saveCustomerAddress(customer, request);
            setCommonFields(customer, request);
            customer.setCompanyName(request.getCompanyName());
            customer.setBillingEmail(request.getBillingEmail()
            );
            return customerMapper.toCompanyCustomerDto(
                    customerRepository.save(customer)
            );
        }
        throw new IllegalStateException("Creating customer failed");

    }

    public void deleteCustomerById(int customerId) {
        customerRepository.deleteById(customerId);
    }

    public CustomerDTO updateCustomerInfo(
            int customerId,
            CreateCustomerRequest request
    ) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));

        setCommonFields(customer, request);
        saveCustomerAddress(customer, request);

        if (customer instanceof PrivateCustomer privateCustomer) {
            return customerMapper.toPrivateCustomerDto(
                    customerRepository.save(privateCustomer)
            );
        }

        if (customer instanceof CompanyCustomer companyCustomer) {
            companyCustomer.setCompanyName(request.getCompanyName());
            companyCustomer.setBillingEmail(request.getBillingEmail());
            return  customerMapper.toCompanyCustomerDto(
                    customerRepository.save(companyCustomer)
            );
        }

        throw new RuntimeException("Changing customer info failed");

    }


    private void setCommonFields(Customer customer, CreateCustomerRequest request) {
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
    }

    private void saveCustomerAddress(Customer customer, CreateCustomerRequest request) {
        CustomerAddress address = new CustomerAddress();
        address.setCustomer(customer);
        address.setCity(request.getCity());
        address.setStreetAddress(request.getStreetAddress());
        address.setCountry(request.getCountry());
        address.setPostalCode(request.getPostalCode());
        customer.setCustomerAddresses(address);
    }
}
