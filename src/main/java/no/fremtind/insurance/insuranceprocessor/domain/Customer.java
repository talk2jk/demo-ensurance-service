package no.fremtind.insurance.insuranceprocessor.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "customer")
public class Customer extends BaseEntity {

    @Column(name = "customer_number", nullable = false, unique = true)
    @NotNull
    @Size(min = 8, max = 8)
    private String customerNumber;

    @Column(name = "social_security_number", unique = true, nullable = false)
    @NotNull
    @Size(min = 11, max = 11)
    private String socialSecurityNumber;

    @Column(name = "first_name", nullable = false)
    @NotNull
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotNull
    private String lastName;

    @Column(name = "email_address", nullable = false, unique = true)
    @NotNull
    private String emailAddress;

    @OneToMany
    private Set<InsuranceContract> insuranceContracts = new HashSet<>();
}