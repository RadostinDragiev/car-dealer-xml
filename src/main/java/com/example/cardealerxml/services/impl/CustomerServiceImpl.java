package com.example.cardealerxml.services.impl;

import com.example.cardealerxml.models.dtos.*;
import com.example.cardealerxml.models.entities.Customer;
import com.example.cardealerxml.repositories.CustomerRepository;
import com.example.cardealerxml.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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

    @Override
    public CustomerRootExportDto getCustomerOrdered() {
        CustomerExportDto[] customerExportDtos = this.modelMapper.map(this.customerRepository.getAllOrderedByBirthDate(), CustomerExportDto[].class);
        return new CustomerRootExportDto(Arrays.stream(customerExportDtos).collect(Collectors.toList()));
    }

    @Override
    public CustomersSalesExportDto getTotalSalesByCustomer() {
        List<CustomerSalesExportDto> customersWIthTotalSales = new ArrayList<>();
        this.customerRepository.getCustomersWIthTotalSales().forEach(record -> {
            String[] splitRecord = record.split(",");
            customersWIthTotalSales.add(new CustomerSalesExportDto(splitRecord[0], Integer.parseInt(splitRecord[1]), Double.parseDouble(splitRecord[2])));
        });

        return new CustomersSalesExportDto(customersWIthTotalSales);
    }

}
