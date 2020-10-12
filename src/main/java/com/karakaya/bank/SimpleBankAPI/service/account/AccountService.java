package com.karakaya.bank.SimpleBankAPI.service.account;

import com.karakaya.bank.SimpleBankAPI.model.Account;
import com.karakaya.bank.SimpleBankAPI.model.Transaction;
import com.karakaya.bank.SimpleBankAPI.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private TransactionService transactionService;

    @Override
    public Account createAccount(BigDecimal initialCredit) {

        Account account = new Account(initialCredit);
        Transaction trx = transactionService.createTransaction(initialCredit);
        trx.setAccount(account);
        List<Transaction> listTrx = Arrays.asList(trx);
        account.setTransactions(listTrx);
        return account;
    }
}
