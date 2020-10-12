package com.karakaya.bank.SimpleBankAPI.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TBL_Customer")
@NoArgsConstructor
public class Customer {

    @Id
    @Column(name="customerID", nullable = false, unique = true)
    private long customerID;

    @Column(name="name", nullable = false)
    @NonNull
    private String name;

    @Column(name="surname", nullable = false)
    @NonNull
    private String surname;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Account> accounts;

    @Column(name="balance", columnDefinition = "NUMERIC(12,2) default 0.0")
    private BigDecimal balance;

    public Customer(long customerID, String name, String surname) {
        this.customerID = customerID;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(customerID);
        return hcb.toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Customer)) {
            return false;
        }
        Customer that = (Customer) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(customerID, that.customerID);
        eb.append(name, that.name);
        eb.append(surname, that.surname);
        return eb.isEquals();
    }
}
