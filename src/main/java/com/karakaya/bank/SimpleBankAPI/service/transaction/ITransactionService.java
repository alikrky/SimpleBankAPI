package com.karakaya.bank.SimpleBankAPI.service.transaction;

import com.karakaya.bank.SimpleBankAPI.model.Transaction;

import java.math.BigDecimal;

public interface ITransactionService {
    public Transaction createTransaction(BigDecimal initialCredit);
}
