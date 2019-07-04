package no.fremtind.insurance.insuranceprocessor.web.rest;

import lombok.extern.slf4j.Slf4j;
import no.fremtind.insurance.insuranceprocessor.domain.Customer;
import no.fremtind.insurance.insuranceprocessor.domain.InsuranceContract;
import no.fremtind.insurance.insuranceprocessor.domain.enums.InsuranceStatus;
import no.fremtind.insurance.insuranceprocessor.services.CustomerService;
import no.fremtind.insurance.insuranceprocessor.services.InsuranceContractService;
import no.fremtind.insurance.insuranceprocessor.web.dto.ContractStatusUpdateDto;
import no.fremtind.insurance.insuranceprocessor.web.dto.InsuranceContractRequest;
import no.fremtind.insurance.insuranceprocessor.web.dto.ContractDto;
import no.fremtind.insurance.insuranceprocessor.web.rest.error.ContractNotFoundException;
import no.fremtind.insurance.insuranceprocessor.web.rest.error.CustomerNotFoundException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;

@RestController
@Slf4j
@RequestMapping("/api")
public class InsuranceContractResource {

    private final InsuranceContractService insuranceContractService;
    private final CustomerService customerService;

    public InsuranceContractResource(InsuranceContractService insuranceContractService, CustomerService customerService) {
        this.insuranceContractService = insuranceContractService;
        this.customerService = customerService;
    }

    @PostMapping("/insurances")
    public ResponseEntity<ContractDto> createContract(@RequestBody @Valid InsuranceContractRequest request) {
        log.info("REST request to create a new insurance contract: {}", request);

        Customer customer = customerService.findBySocialSecurityNumber(request.getCustomerInfo().getSocialSecurityNumber())
                .orElseThrow(() -> new CustomerNotFoundException(request.getCustomerInfo().getSocialSecurityNumber()));

        InsuranceContract contract = InsuranceContract.builder()
                .contractNumber(RandomStringUtils.randomAlphanumeric(8))
                .creationDate(LocalDateTime.now())
                .expiryDate(LocalDateTime.now().plusYears(1L))
                .status(InsuranceStatus.CREATED)
                .type(request.getInsuranceType())
                .customer(customer)
                .build();

        // save new contract
        contract = insuranceContractService.save(contract);

        // contract response
        ContractDto result = ContractDto.from(contract);

        return ResponseEntity.created(URI.create("/api/insurances/"+ contract.getContractNumber()))
                .body(result);
    }

    @PutMapping("/insurances/{contractNumber}/status")
    public ResponseEntity<ContractDto> update(@RequestBody ContractStatusUpdateDto dto, @PathVariable String contractNumber) {
        log.debug("REST request to update contract status: {}", dto);

        InsuranceContract result = insuranceContractService.findByContractNumber(contractNumber)
                .map(contract -> {
                    contract.setStatus(dto.getStatus());
                    return insuranceContractService.save(contract);
                })
                .orElseThrow(() -> new ContractNotFoundException(contractNumber));

        return ResponseEntity.ok(ContractDto.from(result));
    }
}