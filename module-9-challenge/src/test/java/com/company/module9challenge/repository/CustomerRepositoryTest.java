package com.company.module9challenge.repository;

import com.company.module9challenge.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        customerRepository.deleteAll(); // cleansing
    }

    @Test
    public void createGetAndDeleteCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Yeonjun");
        customer.setLastName("Choi");
        customer.setEmail("txtYJ@hybe.com");
        customer.setCompany("Hybe");
        customer.setPhone("xxx-xxx-xxx");
        customer.setAddress1("1234 hybe street");
        customer.setCity("Seoul");
        customer.setPostalCode(94843);
        customer.setCountry("South Korea");

        customer = customerRepository.save(customer);

        Optional<Customer> customer1 = customerRepository.findById(customer.getCustomer_id());
        assertEquals(customer1.get(), customer);
        customerRepository.deleteById(customer.getCustomer_id());
        customer1 = customerRepository.findById(customer.getCustomer_id());
        assertFalse(customer1.isPresent());
    }

    @Test
    public void updateCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Yeonjun");
        customer.setLastName("Choi");
        customer.setEmail("txtYJ@hybe.com");
        customer.setCompany("Hybe");
        customer.setPhone("xxx-xxx-xxx");
        customer.setAddress1("1234 hybe street");
        customer.setCity("Seoul");
        customer.setPostalCode(94843);
        customer.setCountry("South Korea");

        customer = customerRepository.save(customer);

        customer.setFirstName("Soobin");
        customer.setEmail("txtSB@hybe.com");

        customerRepository.save(customer);

        Optional<Customer> customer1 = customerRepository.findById(customer.getCustomer_id());
        assertEquals(customer1.get(), customer);
    }

    @Test
    public void getCustomerByState() {
        Customer customer = new Customer();
        customer.setFirstName("Yeonjun");
        customer.setLastName("Choi");
        customer.setEmail("txtYJ@hybe.com");
        customer.setCompany("Hybe");
        customer.setPhone("xxx-xxx-xxx");
        customer.setAddress1("1234 hybe street");
        customer.setCity("LA");
        customer.setState("California");
        customer.setPostalCode(94843);
        customer.setCountry("United States");

        customerRepository.save(customer);

        List<Customer> customerList = customerRepository.findByState("california");

        assertEquals(customerList.size(), 1);
    }
}