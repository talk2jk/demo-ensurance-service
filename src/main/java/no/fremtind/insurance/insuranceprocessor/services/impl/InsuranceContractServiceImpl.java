package no.fremtind.insurance.insuranceprocessor.services.impl;

import no.fremtind.insurance.insuranceprocessor.domain.InsuranceContract;
import no.fremtind.insurance.insuranceprocessor.repositories.InsuranceContractRepository;
import no.fremtind.insurance.insuranceprocessor.services.InsuranceContractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class InsuranceContractServiceImpl implements InsuranceContractService {

    private final InsuranceContractRepository repository;

    public InsuranceContractServiceImpl(InsuranceContractRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<InsuranceContract> findAll() {
        Set<InsuranceContract> result = new HashSet<>();
        repository.findAll().forEach(result::add);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InsuranceContract> findById(Long aLong) {
        return repository.findById(aLong);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InsuranceContract> findByContractNumber(String contractNumber) {
        return repository.findByContractNumber(contractNumber);
    }

    @Override
    public InsuranceContract save(InsuranceContract entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }
}