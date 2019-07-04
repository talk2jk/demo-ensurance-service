package no.fremtind.insurance.insuranceprocessor.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.fremtind.insurance.insuranceprocessor.domain.InsuranceContract;
import no.fremtind.insurance.insuranceprocessor.domain.enums.InsuranceStatus;
import no.fremtind.insurance.insuranceprocessor.domain.enums.InsuranceType;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDateTime creationDate;
    private LocalDateTime expiryDate;
    private String contractNumber;
    private InsuranceType type;
    private InsuranceStatus status;

    private String customerNumber;

    public static ContractDto from(InsuranceContract contract) {
        return ContractDto.builder()
                .contractNumber(contract.getContractNumber())
                .creationDate(contract.getCreationDate())
                .expiryDate(contract.getExpiryDate())
                .type(contract.getType())
                .status(contract.getStatus())
                .customerNumber(contract.getCustomer().getCustomerNumber())
                .build();
    }
}