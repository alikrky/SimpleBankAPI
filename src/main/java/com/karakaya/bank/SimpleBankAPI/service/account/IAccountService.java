package com.karakaya.bank.SimpleBankAPI.service.account;

import com.karakaya.bank.SimpleBankAPI.model.Account;

import java.math.BigDecimal;

public interface IAccountService {
    public Account createAccount(BigDecimal initialCredit);
}
