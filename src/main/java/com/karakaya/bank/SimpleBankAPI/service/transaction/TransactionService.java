package com.karakaya.bank.SimpleBankAPI.service.transaction;

import com.karakaya.bank.SimpleBankAPI.model.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService implements ITransactionService {
    @Override
    public Transaction createTransaction(BigDecimal initialCredit) {
        Transaction trx = new Transaction(initialCredit);
        return trx;
    }
}
