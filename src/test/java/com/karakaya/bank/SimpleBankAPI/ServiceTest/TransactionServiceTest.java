package com.karakaya.bank.SimpleBankAPI.ServiceTest;

import com.karakaya.bank.SimpleBankAPI.model.Transaction;
import com.karakaya.bank.SimpleBankAPI.service.transaction.TransactionService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Test
    @DisplayName("Create Transaction TEST")
    public void createTransaction() {
        Transaction trx = transactionService.createTransaction(new BigDecimal("14.72"));
        assertThat(trx.getAmount()).isEqualTo(new BigDecimal("14.72"));
    }

}
