package com.karakaya.bank.SimpleBankAPI.RepositoryTest;

import com.karakaya.bank.SimpleBankAPI.Auxiliary.HibernateFilter;
import com.karakaya.bank.SimpleBankAPI.Repository.AccountRepository;
import com.karakaya.bank.SimpleBankAPI.Repository.CustomerRepository;
import com.karakaya.bank.SimpleBankAPI.model.Account;
import com.karakaya.bank.SimpleBankAPI.model.Customer;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("Create Customer With No Account TEST")
    public void createCustomerWithNoAccount() {
        Customer customer = new Customer(10L,"Eric","Cartman");
        customerRepository.save(customer);
        Optional<Customer> persistent = customerRepository.findById(10L);
        if(persistent.isPresent()) {
            assertThat(persistent.get()).isEqualTo(customer);
        }
        else
            assertThat(false);
    }

    @Test
    @DisplayName("Create A Customer With Accounts TEST")
    public void createACustomerWithAccounts() {
        Customer customer = new Customer(10L,"Eric","Cartman");
        Account acc1 = new Account(customer, new BigDecimal("0.00"));
        Account acc2 = new Account(customer, new BigDecimal("1.00"));
        Account acc3 = new Account(customer, new BigDecimal("10.00"));
        List<Account> accList = Arrays.asList(acc1,acc2,acc3);

        customer.setAccounts(accList);
        customerRepository.save(customer);

        acc1.setAccountID(1);
        acc2.setAccountID(2);
        acc3.setAccountID(3);
        List<Account> nonPersistentList = Arrays.asList(acc1,acc2,acc3);

        Optional<Customer> persistent = customerRepository.findById(10L);
        if(persistent.isPresent()) {
            assertThat(HibernateFilter.filter(persistent.get().getAccounts())).isEqualTo(nonPersistentList);
        }
        else
            assertThat(false);
    }


    @Test
    @DisplayName("Search For A Known Customer TEST")
    public void searchForAKnownCustomer() {
        Customer customer = new Customer(10L,"Eric","Cartman");
        customerRepository.save(customer);

        Optional<Customer> persistent = customerRepository.findById(10L);
        if(persistent.isPresent()) {
            assertThat(true);
        }
        else
            assertThat(false);
    }

    @Test
    @DisplayName("Search For A Non Existent Customer TEST")
    public void searchForANonExistentCustomer() {
        Customer customer = new Customer(10L,"Eric","Cartman");
        customerRepository.save(customer);

        Optional<Customer> persistent = customerRepository.findById(99L);
        if(persistent.isPresent()) {
            assertThat(false);
        }
        else
            assertThat(true);
    }


    @Test
    @DisplayName("Delete A Customer TEST")
    public void deleteACustomer() {
        Customer customer1 = new Customer(10L,"Eric","Cartman");
        Customer customer2 = new Customer(11L,"Stan","Marsh");
        Customer customer3 = new Customer(12L,"Kenny","McCormick");
        List<Customer> custList = new ArrayList<>();
        custList.add(customer1);
        custList.add(customer2);
        custList.add(customer3);
        customerRepository.saveAll(custList);
        customerRepository.delete(customer2);

        custList.remove(customer2);

        List<Customer> persistent = customerRepository.findAll();
        assertThat(persistent).isEqualTo(custList);
    }


    @Test
    @DisplayName("Try To Remove A Non Existent Customer TEST")
    public void tryToRemoveANonExistentCustomer() {
        Customer customer1 = new Customer(10L,"Eric","Cartman");
        Customer customer2 = new Customer(11L,"Stan","Marsh");
        Customer customer3 = new Customer(12L,"Kenny","McCormick");
        List<Customer> custList = new ArrayList<>();
        custList.add(customer1);
        custList.add(customer2);
        custList.add(customer3);
        customerRepository.saveAll(custList);
        Customer customer4 = new Customer(13L,"Kyle","Broflovski");
        customerRepository.delete(customer4);

        List<Customer> persistent = customerRepository.findAll();
        assertThat(persistent).isEqualTo(custList);
    }

    @After
    public void truncateAfterTests() {
        customerRepository.deleteAll();
    }

}
