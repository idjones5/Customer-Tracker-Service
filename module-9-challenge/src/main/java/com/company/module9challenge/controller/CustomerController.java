package com.company.module9challenge.controller;

import com.company.module9challenge.model.Customer;
import com.company.module9challenge.repository.CustomerRepository;
import com.sun.tools.javac.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
    @PostMapping(value = "/customer/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    /* Delete an existing customer record */
    @DeleteMapping(value = "/customer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id) {
        customerRepository.deleteById(id);
    }

    /* Find a customer record by id */
    @GetMapping(value = "/customer/{id")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Customer findCustomerById(@PathVariable int id) {
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        return foundCustomer.orElse(null);
    }

    /* Find customer records by state */
    @GetMapping(value = "/customer/state")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Customer> findCustomersByState(@PathVariable String state) {
        return customerRepository.findByState(state);
    }
}
