package com.karakaya.bank.SimpleBankAPI.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "TBL_Transaction")
@NoArgsConstructor
@EqualsAndHashCode
public class Transaction {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="transactionID")
    private long transactionID;

    @ManyToOne
    @JoinColumn(name="accountID", nullable=false)
    @NonNull
    private Account account;

    @Column(name="amount")
    private BigDecimal amount;

    public Transaction(Account account, BigDecimal amount) {
        this.account = account;
        this.amount = amount;
    }

    public Transaction(BigDecimal amount) {
        this.amount = amount;
    }
}
