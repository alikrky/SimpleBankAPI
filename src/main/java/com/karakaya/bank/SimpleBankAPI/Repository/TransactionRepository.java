package com.karakaya.bank.SimpleBankAPI.Repository;

import com.karakaya.bank.SimpleBankAPI.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
