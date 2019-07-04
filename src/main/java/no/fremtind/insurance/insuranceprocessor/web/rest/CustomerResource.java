package no.fremtind.insurance.insuranceprocessor.web.rest;

import lombok.extern.slf4j.Slf4j;
import no.fremtind.insurance.insuranceprocessor.domain.Customer;
import no.fremtind.insurance.insuranceprocessor.services.CustomerService;
import no.fremtind.insurance.insuranceprocessor.web.dto.CustomerInfo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api")
public class CustomerResource {

    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody CustomerInfo customerInfo) {
        log.debug("REST request to create a new customer: {}", customerInfo);

        // Try to find a customer with provided info. If not existing, create a new customer
        Customer customer = customerService.findBySocialSecurityNumber(customerInfo.getSocialSecurityNumber())
                .orElseGet(() -> {
                    Customer newCustomer =Customer.builder()
                            .customerNumber(RandomStringUtils.randomAlphanumeric(8).toUpperCase())
                            .emailAddress(customerInfo.getEmailAddress())
                            .firstName(customerInfo.getFirstName())
                            .lastName(customerInfo.getLastName())
                            .socialSecurityNumber(customerInfo.getSocialSecurityNumber())
                            .build();
                    return customerService.save(newCustomer);
                });

        return ResponseEntity
                .created(URI.create("/api/customers/"+ customer.getCustomerNumber())).body(customer);
    }
}