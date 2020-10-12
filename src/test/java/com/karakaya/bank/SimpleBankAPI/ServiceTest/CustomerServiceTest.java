package com.karakaya.bank.SimpleBankAPI.ServiceTest;

import com.karakaya.bank.SimpleBankAPI.Repository.CustomerRepository;
import com.karakaya.bank.SimpleBankAPI.model.Customer;
import com.karakaya.bank.SimpleBankAPI.service.customer.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Before
    public void setup() {
        customerService.populateRandomData();
    }

    @Test
    @DisplayName("Get All Customer TEST")
    public void getAllCustomers() {
        List<Customer> persistent = customerService.getAllCustomers();
        assertThat(persistent.size()).isEqualTo(20);
    }

    @Test
    @DisplayName("Create Customer TEST")
    @Transactional
    public void createCustomer() {
        Customer customer = new Customer(10L,"Eric","Cartman");
        customerService.createOrUpdateCustomer(customer,BigDecimal.ZERO);
        Customer persistent = customerService.getCustomer(10L);
        assertThat(persistent).isEqualTo(customer);
    }

    @Test
    @DisplayName("Update Customer TEST")
    @Transactional
    public void updateExistentCustomer() {
        Customer customer = new Customer(10L,"Eric","Cartman");
        customerService.createOrUpdateCustomer(customer,BigDecimal.ZERO);
        customer.setName("Ali");
        customerService.createOrUpdateCustomer(customer,BigDecimal.ZERO);
        Customer persistent = customerService.getCustomer(10L);
        assertThat(persistent.getName()).isEqualTo("Ali");
    }

    @Test
    @DisplayName("Get Non Existent Customer TEST")
    @Transactional
    public void getNonExistentCustomer() {

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            Customer cust = customerService.getCustomer(99999L);
            System.out.println(cust.getName());
        });

        String expectedMessage = "Unable to find";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Remove All Customers TEST")
    @Transactional
    public void removeAllCustomers() {
        customerService.removeAll();
        List<Customer> persistent = customerService.getAllCustomers();
        assertThat(persistent.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Calculate Total Balance TEST")
    @Transactional
    public void calculateTotalBalanceOfCustomer() {
        Customer customer = new Customer(10L,"Eric","Cartman");
        customerService.createOrUpdateCustomer(customer,BigDecimal.TEN);
        BigDecimal total = customerService.calculateTotalBalanceOfCustomer(10L);
        assertThat(total).isEqualTo(BigDecimal.TEN);
    }
}
