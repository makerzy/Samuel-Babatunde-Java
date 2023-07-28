package com.company.customerdataservice.controller;


import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    CustomerRepository customerRepository;

    Customer customer;

    @BeforeEach
    public void setUp() throws Exception {
        customerRepository.deleteAll();
        customer = new Customer();
        customer.setFirstName("Firstname");
        customer.setLastName("Lastname");
        customer.setEmail("firstname.lastname@customer.com");
        customer.setCompany("company");
        customer.setPhone("800-800-8000");
        customer.setAddress1("1233 address 1");
        customer.setAddress2("alen street");
        customer.setCity("San Jose");
        customer.setState("California");
        customer.setPostalCode("49400");
        customer.setCountry("United States");
        customerRepository.save(customer);
    }

    ObjectMapper objectMapper = new ObjectMapper();

    //    GET customer
    @Test
    public void shouldGetAllCustomers() throws Exception {
        mockMvc.perform(
                        get("/customers")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
//                .andExpect(JsonPath("$").size)
                .andReturn();
    }

    //    GET customer/id
    @Test
    public void shouldGetCustomerById() throws Exception {
        mockMvc.perform(get("/customers/{id}", customer.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    //    GET customer/state
    @Test
    public void shouldGetCustomersByState() throws Exception {
        mockMvc.perform(get("/customers/state/{state}", customer.getState()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    //    POST
    @Test
    public void shouldAddNewCustomer() throws Exception {
        Customer customer1 = new Customer();
        customer.setFirstName("Firstname1");
        customer.setLastName("Lastname1");
        customer.setEmail("firstname1.lastname1@customer.com");
        customer.setCompany("company1");
        customer.setPhone("800-800-8001");
        customer.setAddress1("33 address 2");
        customer.setAddress2("allen street");
        customer.setCity("San Jose");
        customer.setState("California");
        customer.setPostalCode("49300");
        customer.setCountry("United States");
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(customer1))
                                .characterEncoding("utf-8")
                )

                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();


    }

    //    PUT
    @Test
    public void shouldUpdateCustomerById() throws Exception {
        customer.setFirstName("new_first_name");
        customer.setLastName("new_last_name");
        mockMvc.perform(put("/customers/{id}", customer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer))
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }


    //    DELETE
    @Test
    public void shouldDeleteCustomerById() throws Exception {
        mockMvc.perform(delete("/customers/{id}", customer.getId())
                      )
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }
}
