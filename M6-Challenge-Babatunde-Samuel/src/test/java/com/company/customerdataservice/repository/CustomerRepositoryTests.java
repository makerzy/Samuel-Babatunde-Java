package com.company.customerdataservice.repository;


import com.company.customerdataservice.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class CustomerRepositoryTests {

    @Autowired
    CustomerRepository customerRepository;

    Customer customer;

    @BeforeEach
    public void setUp() throws Exception{
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

    }

    @Test
    public void shouldGetCustomerById(){
        customerRepository.save(customer);
        Optional<Customer> customer1 = customerRepository.findById(customer.getId());
        assertEquals(customer1.get(), customer);
    }

    @Test
    public void shouldGetCustomersByState(){
        customerRepository.save(customer);
        customer.setId(1000);
        customerRepository.save(customer);

        List<Customer> customers = customerRepository.findByState(customer.getState());
        assertEquals(customers.size(), 2);
    }
    @Test
    public void shouldCreateCustomer(){
        customer = customerRepository.save(customer);

        Optional<Customer> customer1 = customerRepository.findById(customer.getId());
        assertEquals(customer1.get(), customer);
    }

    @Test
    public void shouldUpdateCustomer(){
        customer = customerRepository.save(customer);

        customer.setCity("Miami");
        customer.setState("Florida");
        customer.setPostalCode("80808");
// Update in DB
        customerRepository.save(customer);
        Optional<Customer> customer1 = customerRepository.findById(customer.getId());
        assertEquals(customer1.get(), customer);
    }

    @Test
    public void shouldDeleteACustomer(){
        customer = customerRepository.save(customer);
        Optional<Customer> customer1 = customerRepository.findById(customer.getId());
        assertEquals(customer1.get(), customer);

        customerRepository.deleteById(customer.getId());

        customer1 = customerRepository.findById(customer.getId());
        assertFalse(customer1.isPresent());
    }



}
