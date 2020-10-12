package com.karakaya.bank.SimpleBankAPI.service.customer;

import com.github.javafaker.Faker;
import com.karakaya.bank.SimpleBankAPI.Repository.AccountRepository;
import com.karakaya.bank.SimpleBankAPI.Repository.CustomerRepository;
import com.karakaya.bank.SimpleBankAPI.model.Account;
import com.karakaya.bank.SimpleBankAPI.model.Customer;
import com.karakaya.bank.SimpleBankAPI.model.Transaction;
import com.karakaya.bank.SimpleBankAPI.service.account.AccountService;
import com.karakaya.bank.SimpleBankAPI.service.transaction.TransactionService;
import com.karakaya.bank.SimpleBankAPI.type.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    private Customer createCustomer(Customer customer, BigDecimal initialCredit) {

        Account acc = accountService.createAccount(initialCredit);
        List<Account> listAcc = Arrays.asList(acc);

        customer.setAccounts(listAcc);
        acc.setCustomer(customer);
        customerRepository.save(customer);
        return customer;
    }

    private void updateAccountType(List<Account> accList) {
        for(Account acc:accList) {
            acc.setType(AccountType.DEPRECATED);
        }
    }

    private Customer updateCustomer(Customer persistent, Customer newOne, BigDecimal initialCredit) {

        Account acc = accountService.createAccount(initialCredit);
        List<Account> listAcc = Arrays.asList(acc);
        updateAccountType(persistent.getAccounts());
        persistent.getAccounts().addAll(listAcc);
        acc.setCustomer(persistent);
        persistent.setName(newOne.getName());
        persistent.setSurname(newOne.getSurname());
        customerRepository.save(persistent);
        return persistent;
    }

    @Override
    public Customer createOrUpdateCustomer(Customer customer, BigDecimal initialCredit) {

        Optional<Customer> persistent = customerRepository.findById(customer.getCustomerID());

        if(persistent.isPresent())
        {
            return updateCustomer(persistent.get(),customer, initialCredit);
        } else {
            return createCustomer(customer, initialCredit);
        }
    }

    @Override
    public List<Customer> getAllCustomers() {

        List<Customer> customerList = customerRepository.findAll();

        if(customerList.size() > 0) {
            return customerList;
        } else {
            return new ArrayList<Customer>();
        }
    }

    @Override
    public Customer getCustomer(long customerID) {
        return customerRepository.getOne(customerID);
    }

    @Override
    public Map<Long, List<Transaction>> getUserTransactions(long customerID) {
        Map<Long, List<Transaction>> transactionMap = new HashMap<>();
        Customer cust = getCustomer(customerID);
        List<Account> accountList = cust.getAccounts();
        for (Account acc : accountList) {
            List<Transaction> transactionList = acc.getTransactions();
            if (transactionList != null && !transactionList.isEmpty()) {
                transactionMap.put(acc.getAccountID(), transactionList);
            }
        }
        return transactionMap;
    }

    @Override
    public BigDecimal calculateTotalBalanceOfCustomer(long customerID) {
        Customer customer = getCustomer(customerID);
        List<Account> customerAccounts = customer.getAccounts();
        BigDecimal totalBalance = BigDecimal.ZERO;

        for (Account acc : customerAccounts) {
            totalBalance = totalBalance.add(acc.getBalance());
        }
        return totalBalance;
    }

    @Override
    public void removeAll() {
        customerRepository.deleteAll();
    }

    public void populateRandomData() {
        Faker faker = new Faker();
        Random rand = new Random();
        Set<Integer> custSet = new HashSet<>();
        for(int i=0;i<20;i++)
        {
            int randomID = rand.nextInt(100)+100;
            while(custSet.contains(randomID)) {
                randomID = rand.nextInt(100)+100;
            }
            custSet.add(randomID);
            String name = faker.name().firstName();
            String surname = faker.name().lastName();
            Customer temp = new Customer(randomID, name, surname);
            createCustomer(temp,BigDecimal.ZERO);
        }
    }



}
