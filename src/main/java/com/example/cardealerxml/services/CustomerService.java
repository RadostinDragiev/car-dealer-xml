package com.example.cardealerxml.services;

import com.example.cardealerxml.models.dtos.CustomerRootExportDto;
import com.example.cardealerxml.models.dtos.CustomersSalesExportDto;
import com.example.cardealerxml.models.entities.Customer;
import com.example.cardealerxml.models.dtos.CustomerAddDto;

public interface CustomerService {
    void addCustomer(CustomerAddDto customerAddDto);

    Customer getRandomCustomer();

    CustomerRootExportDto getCustomerOrdered();

    CustomersSalesExportDto getTotalSalesByCustomer();
}
