package no.fremtind.insurance.insuranceprocessor.web.rest.error;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String socialSecurityNumber) {
        super(String.format("Customer with with SSN %s does not exist", socialSecurityNumber));
    }
}
