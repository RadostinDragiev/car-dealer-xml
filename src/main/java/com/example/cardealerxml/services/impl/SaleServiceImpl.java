package com.example.cardealerxml.services.impl;

import com.example.cardealerxml.models.entities.Sale;
import com.example.cardealerxml.repositories.SaleRepository;
import com.example.cardealerxml.services.CarService;
import com.example.cardealerxml.services.CustomerService;
import com.example.cardealerxml.services.SaleService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class SaleServiceImpl implements SaleService {
    private static final List<Integer> DISCOUNT_LIST = Arrays.asList(0, 5, 10, 15, 20, 30, 40, 50);

    private final SaleRepository saleRepository;
    private final CarService carService;
    private final CustomerService customerService;

    public SaleServiceImpl(SaleRepository saleRepository, CarService carService, CustomerService customerService) {
        this.saleRepository = saleRepository;
        this.carService = carService;
        this.customerService = customerService;
    }

    @Override
    @Transactional
    public void addSale() {
        Sale sale = new Sale();
        sale.setCar(this.carService.getRandomCar());
        sale.setCustomer(this.customerService.getRandomCustomer());
        sale.setDiscount(DISCOUNT_LIST.get(new Random().nextInt(DISCOUNT_LIST.size())));
       this.saleRepository.saveAndFlush(sale);
    }
}
