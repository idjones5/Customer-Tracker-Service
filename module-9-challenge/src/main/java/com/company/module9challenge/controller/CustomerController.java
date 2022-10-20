package com.company.module9challenge.controller;

import com.company.module9challenge.model.Customer;
import com.company.module9challenge.repository.CustomerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    /* Create a new customer record */
    @PostMapping(value = "/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    /* Update an existing customer record */
    @PutMapping(value = "/customer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Integer id) {

        Customer updatedCustomer = customerRepository.findById(id)
                .map(c -> {
                    c.setFirstName(customer.getFirstName());
                    c.setLastName(customer.getLastName());
                    c.setEmail(customer.getEmail());
                    c.setCompany(customer.getCompany());
                    c.setPhone(customer.getPhone());
                    c.setAddress1(customer.getAddress1());
                    c.setAddress2(customer.getAddress2());
                    c.setCity(customer.getCity());
                    c.setState(customer.getState());
                    c.setPostalCode(customer.getPostalCode());
                    c.setCountry(customer.getCountry());
                    return customerRepository.save(customer);
                })
                .orElseGet(() -> {
                    customer.setCustomer_id(id);
                    return customerRepository.save(customer);
                });
        return customerRepository.save(customer);
    }

    /* Delete an existing customer record */
    @DeleteMapping(value = "/customer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Integer id) {
        customerRepository.deleteById(id);
    }

    /* Find a customer record by id */
    @GetMapping(value = "/customer/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Customer findCustomerById(@PathVariable Integer id) {
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        return foundCustomer.orElse(null);
    }

    /* Find customer records by state */
    @GetMapping(value = "/customer/state/{state}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Customer> findCustomersByState(@PathVariable String state) {
        return customerRepository.findByState(state);
    }
}
