package no.fremtind.insurance.insuranceprocessor.services.impl;

import no.fremtind.insurance.insuranceprocessor.domain.Customer;
import no.fremtind.insurance.insuranceprocessor.repositories.CustomerRepository;
import no.fremtind.insurance.insuranceprocessor.services.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmailAddress(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> findBySocialSecurityNumber(String ssn) {
        return customerRepository.findBySocialSecurityNumber(ssn);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> findById(Long aLong) {
        return customerRepository.findById(aLong);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Customer> findAll() {
        return new HashSet<>(customerRepository.findAll());
    }

    @Override
    public Customer save(Customer entity) {
        return customerRepository.save(entity);
    }

    @Override
    public void deleteById(Long aLong) {
        customerRepository.deleteById(aLong);
    }
}
