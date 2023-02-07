package com.example.cardealerxml.services.impl;

import com.example.cardealerxml.models.dtos.CustomerAddDto;
import com.example.cardealerxml.models.entities.Customer;
import com.example.cardealerxml.repositories.CustomerRepository;
import com.example.cardealerxml.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addCustomer(CustomerAddDto customerAddDto) {
        Customer customer = this.modelMapper.map(customerAddDto, Customer.class);
        this.customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer getRandomCustomer() {
        long randomId = new Random().nextInt((int) this.customerRepository.count()) + 1;
        return this.customerRepository.findById(randomId).get();
    }

}
