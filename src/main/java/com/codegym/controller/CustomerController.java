package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("api/students")
    @ResponseBody()
    public List<Customer> getListCustomers(){
        List<Customer> customers = customerService.findAll();
        return customers;
    }

    @PostMapping("api/create-customer")
    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer){
        customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/api/update-customer/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        Customer customerUpdate = customerService.findById(id);

        if (customerUpdate ==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        customerUpdate.setFirstName(customer.getFirstName());
        customerUpdate.setLastName(customer.getLastName());
        customerUpdate.setId(customer.getId());

        customerService.save(customerUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/delete/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        customerService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
