package no.fremtind.insurance.insuranceprocessor.services;

import no.fremtind.insurance.insuranceprocessor.domain.Customer;

import java.util.Optional;

public interface CustomerService extends CrudService<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findBySocialSecurityNumber(String ssn);
}
