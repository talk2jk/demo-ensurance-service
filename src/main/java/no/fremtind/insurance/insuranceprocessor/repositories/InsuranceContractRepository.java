package no.fremtind.insurance.insuranceprocessor.repositories;

import no.fremtind.insurance.insuranceprocessor.domain.InsuranceContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InsuranceContractRepository extends JpaRepository<InsuranceContract, Long> {
    Optional<InsuranceContract> findByContractNumber(String number);
}
