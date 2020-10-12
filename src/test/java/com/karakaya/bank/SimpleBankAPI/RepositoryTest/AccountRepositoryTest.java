package com.karakaya.bank.SimpleBankAPI.RepositoryTest;

import com.karakaya.bank.SimpleBankAPI.Auxiliary.HibernateFilter;
import com.karakaya.bank.SimpleBankAPI.Repository.AccountRepository;
import com.karakaya.bank.SimpleBankAPI.Repository.CustomerRepository;
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
public class AccountRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("Try To Create An Account Without Customer TEST")
    public void tryToCreateAnAccountWithoutCustomer() {
        Account acc = new Account(null, BigDecimal.ZERO);
        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            accountRepository.save(acc);
        });

        String expectedMessage = "not-null property references a null or transient value";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Create Account With Transaction TEST")
    public void createAccountWithTransaction() {
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

        Optional<Account> persistent = accountRepository.findById(1L);
        acc.setAccountID(1L);
        acc.getTransactions().get(0).setTransactionID(1L);
        if(persistent.isPresent()) {
            assertThat(HibernateFilter.filter(persistent.get().getTransactions())).isEqualTo(acc.getTransactions());
        }
        else
            assertThat(false);
    }


    @Test
    @DisplayName("Find Accounts of the Customer TEST")
    public void findAccountsOfTheCustomer() {
        Customer customer = new Customer(10L,"Eric","Cartman");
        Account acc1 = new Account(customer, new BigDecimal("0.00"));
        Account acc2 = new Account(customer, new BigDecimal("1.00"));
        Account acc3 = new Account(customer, new BigDecimal("10.00"));
        List<Account> accList = new ArrayList<>();
        customer.setAccounts(accList);

        customerRepository.save(customer);

        List<Account> persistent = accountRepository.findAll();

        if(persistent != null && persistent.size()==3) {
            assertThat(persistent).isEqualTo(accList);
        }
        else
            assertThat(false);

    }

    @Test
    @DisplayName("Delete An Account of the Customer TEST")
    public void deleteAnAccountOfTheCustomer() {
        Customer customer = new Customer(10L,"Eric","Cartman");
        Account acc1 = new Account(customer, new BigDecimal("0.00"));
        Account acc2 = new Account(customer, new BigDecimal("1.00"));
        Account acc3 = new Account(customer, new BigDecimal("10.00"));
        List<Account> accList = new ArrayList<>();
        customer.setAccounts(accList);

        customerRepository.save(customer);
        accountRepository.delete(acc2);
        accList.remove(acc2);
        Optional<Customer> persistent = customerRepository.findById(10L);
        if(persistent.isPresent()) {
            assertThat(HibernateFilter.filter(persistent.get().getAccounts())).isEqualTo(accList);
        }
        else
            assertThat(false);
    }

    @After
    public void truncateAfterTests() {
        customerRepository.deleteAll();
    }

}
