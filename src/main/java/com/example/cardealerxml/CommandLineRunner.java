package com.example.cardealerxml;

import com.example.cardealerxml.models.dtos.CarAddRootDto;
import com.example.cardealerxml.models.dtos.CustomerAddRootDto;
import com.example.cardealerxml.models.dtos.PartAddRootDto;
import com.example.cardealerxml.models.dtos.SupplierAddRootDto;
import com.example.cardealerxml.services.*;
import com.example.cardealerxml.utils.XmlParser;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.Random;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {
    private final XmlParser xmlParser;
    private final CarService carService;
    private final CustomerService customerService;
    private final PartService partService;
    private final SupplierService supplierService;
    private final SaleService saleService;

    public CommandLineRunner(XmlParser xmlParser, CarService carService, CustomerService customerService, PartService partService, SupplierService supplierService, SaleService saleService) {
        this.xmlParser = xmlParser;
        this.carService = carService;
        this.customerService = customerService;
        this.partService = partService;
        this.supplierService = supplierService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
        //seedData();

        //orderedCustomers();

        //carsFromMakeToyota();
    }

    private void carsFromMakeToyota() throws JAXBException {
        this.xmlParser.marshalFile(this.carService.getCarsByMake("Toyota"), "src/main/resources/files/outputs/toyota-cars.xml");
    }

    // Query 1 – Ordered Customers
    private void orderedCustomers() throws JAXBException {
        this.xmlParser.marshalFile(this.customerService.getCustomerOrdered(), "src/main/resources/files/outputs/ordered-customers.xml");
    }

    private void seedData() throws JAXBException, FileNotFoundException {
        // Import data for Suppliers from suppliers.xml
        SupplierAddRootDto supplierAddDtos = (SupplierAddRootDto) this.xmlParser.unmarshalFile(SupplierAddRootDto.class, "src/main/resources/files/data/suppliers.xml");
        supplierAddDtos.getSuppliers().forEach(this.supplierService::addSupplier);

        // Import data for Suppliers from parts.xml
        PartAddRootDto partAddRootDto = (PartAddRootDto) this.xmlParser.unmarshalFile(PartAddRootDto.class, "src/main/resources/files/data/parts.xml");
        partAddRootDto.getParts().forEach(this.partService::addPart);

        // Import data for Suppliers from cars.xml
        CarAddRootDto carAddRootDto = (CarAddRootDto) this.xmlParser.unmarshalFile(CarAddRootDto.class, "src/main/resources/files/data/cars.xml");
        carAddRootDto.getCars().forEach(this.carService::addCard);

        // Import data for Suppliers from customers.xml
        CustomerAddRootDto customerAddRootDto = (CustomerAddRootDto) this.xmlParser.unmarshalFile(CustomerAddRootDto.class, "src/main/resources/files/data/customers.xml");
        customerAddRootDto.getCustomers().forEach(this.customerService::addCustomer);

        // Create random sale records
        for (int i = 0; i < new Random().nextInt(30); i++) {
            this.saleService.addSale();
        }
    }
}
