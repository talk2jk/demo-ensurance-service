package no.fremtind.insurance.insuranceprocessor.repositories;

import no.fremtind.insurance.insuranceprocessor.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmailAddress(String email);
    Optional<Customer> findBySocialSecurityNumber(String ssn);
}