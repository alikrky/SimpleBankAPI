package com.karakaya.bank.SimpleBankAPI.model;

import lombok.*;
import com.karakaya.bank.SimpleBankAPI.type.AccountType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "TBL_Account")
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="accountID", nullable = false)
    private long accountID;

    @ManyToOne
    @JoinColumn(name="customerID", nullable=false)
    private Customer customer;

    @Column(name="balance")
    private BigDecimal balance;

    @Column(name="type")
    private AccountType type = AccountType.CURRENT;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public Account(Customer customer, BigDecimal balance) {
        this.customer = customer;
        this.balance = balance;
    }

    public Account(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(accountID);
        return hcb.toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Account)) {
            return false;
        }
        Account that = (Account) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(accountID, that.accountID);
        eb.append(customer, that.customer);
        eb.append(balance, that.balance);
        eb.append(type, that.type);
        return eb.isEquals();
    }
}
