package com.karakaya.bank.SimpleBankAPI.service.customer;

import com.karakaya.bank.SimpleBankAPI.model.Customer;
import com.karakaya.bank.SimpleBankAPI.model.Transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ICustomerService {
    public Customer createOrUpdateCustomer(Customer customer, BigDecimal initialCredit);
    public List<Customer> getAllCustomers();
    public Customer getCustomer(long customerID);
    public Map<Long, List<Transaction>> getUserTransactions(long customerID);
    public BigDecimal calculateTotalBalanceOfCustomer(long customerID);
    public void removeAll();
}
