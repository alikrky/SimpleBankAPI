package com.karakaya.bank.SimpleBankAPI.RepositoryTest;

import com.karakaya.bank.SimpleBankAPI.Repository.CustomerRepository;
import com.karakaya.bank.SimpleBankAPI.Repository.TransactionRepository;
import com.karakaya.bank.SimpleBankAPI.model.Account;
import com.karakaya.bank.SimpleBankAPI.model.Customer;
import com.karakaya.bank.SimpleBankAPI.model.Transaction;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Try To Create A Transaction Without Account")
    public void TryToCreateATransactionWithoutAccount() {
        Transaction trx = new Transaction(null,BigDecimal.ZERO);
        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            transactionRepository.save(trx);
        });

        String expectedMessage = "not-null property references a null or transient value";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Update Amount Of The Transaction")
    public void UpdateAmountOfTheTransaction() {
        Customer customer = new Customer(10L,"Eric","Cartman");
        Account acc = new Account(customer, new BigDecimal("0.00"));
        Transaction trx = new Transaction(acc,new BigDecimal("0.00"));
        List<Transaction> trxList = new ArrayList<>();
        List<Account> accList = new ArrayList<>();
        accList.add(acc);
        trxList.add(trx);
        acc.setTransactions(trxList);
        customer.setAccounts(accList);

        customerRepository.save(customer);

        Optional<Transaction> persistent = transactionRepository.findById(1L);

        if(persistent.isPresent()) {
            persistent.get().setAmount(new BigDecimal("99.00"));
            transactionRepository.save(persistent.get());
            Optional<Transaction> updatedTrx = transactionRepository.findById(1L);
            assertThat(updatedTrx.get().getAmount()).isEqualTo(new BigDecimal("99.00"));
        }
        else
            assertThat(false);
    }

    @Test
    @DisplayName("Find A Known Transaction")
    public void FindAKnownTransaction() {
        Customer customer = new Customer(10L,"Eric","Cartman");
        Account acc = new Account(customer, new BigDecimal("15.00"));
        Transaction trx = new Transaction(acc,new BigDecimal("15.00"));
        List<Transaction> trxList = new ArrayList<>();
        List<Account> accList = new ArrayList<>();
        accList.add(acc);
        trxList.add(trx);
        acc.setTransactions(trxList);
        customer.setAccounts(accList);

        customerRepository.save(customer);

        Optional<Transaction> persistent = transactionRepository.findById(1L);
        trx.setTransactionID(1L);
        trx.getAccount().setAccountID(1L);
        assertThat(persistent.get()).isEqualTo(trx);

    }

    @After
    public void truncateAfterTests() {
        customerRepository.deleteAll();
    }
}
