package com.karakaya.bank.SimpleBankAPI.ControllerTest;

import com.karakaya.bank.SimpleBankAPI.model.Customer;
import com.karakaya.bank.SimpleBankAPI.service.customer.CustomerService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private List<Customer> customerList;

    @BeforeEach
    void setUp() {
        Customer customer1 = new Customer(10L,"Eric","Cartman");
        Customer customer2 = new Customer(11L,"Stan","Marsh");
        Customer customer3 = new Customer(12L,"Kenny","McCormick");
        customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);
    }


    @Test
    public void test() throws Exception{
        customerService.getAllCustomers();
        given(customerService.getAllCustomers()).willReturn(customerList);
        this.mockMvc.perform(get("/")).andExpect(status().isOk());

    }



}
