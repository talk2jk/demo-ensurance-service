package no.fremtind.insurance.insuranceprocessor.web.rest.error;

public class ContractNotFoundException extends RuntimeException {

    public ContractNotFoundException(String contractNumber) {
        super(String.format("Contract with number {%s} not found", contractNumber));
    }
}
