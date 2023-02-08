package com.example.cardealerxml.services.impl;

import com.example.cardealerxml.models.dtos.CarSimpleExportDto;
import com.example.cardealerxml.models.dtos.SaleExportDto;
import com.example.cardealerxml.models.dtos.SalesExportDto;
import com.example.cardealerxml.models.entities.Sale;
import com.example.cardealerxml.repositories.SaleRepository;
import com.example.cardealerxml.services.CarService;
import com.example.cardealerxml.services.CustomerService;
import com.example.cardealerxml.services.SaleService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
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

    @Override
    public SalesExportDto salesWithDiscount() {
        List<SaleExportDto> saleExportDtos = new ArrayList<>();
        List<String> it = this.saleRepository.getSalesWithAppliedDiscount();
        it.forEach(ti -> {
            String[] split = ti.split(",");
            double discount = Double.parseDouble(split[4]) / 100;
            BigDecimal price= new BigDecimal(split[5]);
            double priceAfterDiscount  = price.doubleValue() * (1 - discount);
            saleExportDtos.add(new SaleExportDto(new CarSimpleExportDto(split[0], split[1], Long.parseLong(split[2])), split[3], discount, price, new BigDecimal(priceAfterDiscount)));
        });
        return new SalesExportDto(saleExportDtos);
    }
}
