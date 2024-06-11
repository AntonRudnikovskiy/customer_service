package sentinelguardcustomer_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sentinelguardcustomer_service.config.amqp.RabbitMQueue;
import sentinelguardcustomer_service.config.client.FraudClient;
import sentinelguardcustomer_service.dto.CustomerNotificationRequest;
import sentinelguardcustomer_service.dto.CustomerRegistrationRequest;
import sentinelguardcustomer_service.dto.FraudCheckResponse;
import sentinelguardcustomer_service.entity.Customer;
import sentinelguardcustomer_service.message.producer.NotificationProducer;
import sentinelguardcustomer_service.repository.CustomerRepository;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationProducer rabbitMQMessageProducer;
    private final RabbitMQueue rabbitMQueue;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudster = fraudClient.isFraudster(customer.getId());

        if (fraudster.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        CustomerNotificationRequest payload = new CustomerNotificationRequest(
                "",
                customer.getLastName(),
                LocalDateTime.now(),
                customer.getEmail(),
                customer.getId());

        rabbitMQMessageProducer.publish(payload, rabbitMQueue.getInternalExchange(),
                rabbitMQueue.getInternalNotificationRoutingKey());
    }
}
