package com.example.cardealerxml.services;

import com.example.cardealerxml.models.dtos.SupplierAddDto;
import com.example.cardealerxml.models.entities.Supplier;

public interface SupplierService {
    void addSupplier(SupplierAddDto supplierAddDto);

    Supplier getRandomSupplier();
}
