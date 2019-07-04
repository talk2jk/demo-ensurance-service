package no.fremtind.insurance.insuranceprocessor.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.fremtind.insurance.insuranceprocessor.domain.enums.InsuranceStatus;
import no.fremtind.insurance.insuranceprocessor.domain.enums.InsuranceType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "insurance")
public class InsuranceContract extends BaseEntity {

    @Column(name = "contract_number", unique = true)
    private String contractNumber;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    @Column(name = "insurance_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private InsuranceType type;

    @Column(name = "insurance_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private InsuranceStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}