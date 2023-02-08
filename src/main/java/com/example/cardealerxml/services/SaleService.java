package com.example.cardealerxml.services;

import com.example.cardealerxml.models.dtos.SalesExportDto;

public interface SaleService {
    void addSale();

    SalesExportDto salesWithDiscount();
}
