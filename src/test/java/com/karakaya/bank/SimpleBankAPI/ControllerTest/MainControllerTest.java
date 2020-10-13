package com.karakaya.bank.SimpleBankAPI.ControllerTest;

import com.karakaya.bank.SimpleBankAPI.Controller.MainController;
import com.karakaya.bank.SimpleBankAPI.service.customer.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@WebMvcTest(value = MainController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class MainControllerTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setup() { customerService.populateRandomData(); }

    @Test
    @DisplayName("Index Page TEST")
    public void indexPage() throws Exception {

        mvc.perform(get("/")
            .contentType(MediaType.APPLICATION_XHTML_XML))
            .andExpect(status().isOk());
    }

}
