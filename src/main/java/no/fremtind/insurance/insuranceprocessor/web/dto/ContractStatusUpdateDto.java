package no.fremtind.insurance.insuranceprocessor.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import no.fremtind.insurance.insuranceprocessor.domain.enums.InsuranceStatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class ContractStatusUpdateDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @NotEmpty
    private InsuranceStatus status;
}
