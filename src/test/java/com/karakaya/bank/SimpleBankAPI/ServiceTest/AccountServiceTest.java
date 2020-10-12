package com.karakaya.bank.SimpleBankAPI.ServiceTest;

import com.karakaya.bank.SimpleBankAPI.model.Account;
import com.karakaya.bank.SimpleBankAPI.service.account.AccountService;
import com.karakaya.bank.SimpleBankAPI.type.AccountType;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    @DisplayName("Create Account With Balance TEST")
    public void createAccountWithBalance() {
        Account acc = accountService.createAccount(new BigDecimal("15.32"));
        assertThat(acc.getBalance()).isEqualTo(new BigDecimal("15.32"));
    }

    @Test
    @DisplayName("Create Account With One Transaction TEST")
    @Transactional
    public void createAccountWithOneTransaction() {
        Account acc = accountService.createAccount(new BigDecimal("15.32"));
        assertThat(acc.getTransactions().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Create Current Account TEST")
    @Transactional
    public void createCurrentAccount() {
        Account acc = accountService.createAccount(new BigDecimal("15.32"));
        assertThat(acc.getType()).isEqualTo(AccountType.CURRENT);
    }
}
