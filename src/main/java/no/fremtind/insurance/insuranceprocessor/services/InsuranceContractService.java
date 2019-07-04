package no.fremtind.insurance.insuranceprocessor.services;

import no.fremtind.insurance.insuranceprocessor.domain.InsuranceContract;

import java.util.Optional;

public interface InsuranceContractService extends CrudService<InsuranceContract, Long> {
    Optional<InsuranceContract> findByContractNumber(String contractNumber);
}
