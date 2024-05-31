package sentinelguardcustomer_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sentinelguardcustomer_service.dto.CustomerRegistrationRequest;
import sentinelguardcustomer_service.entity.Customer;
import sentinelguardcustomer_service.repository.CustomerRepository;

@Service
@Slf4j
public record CustomerService(CustomerRepository customerRepository) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        customerRepository.save(customer);
    }
}
