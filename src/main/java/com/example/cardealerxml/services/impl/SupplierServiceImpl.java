package com.example.cardealerxml.services.impl;

import com.example.cardealerxml.models.dtos.SupplierAddDto;
import com.example.cardealerxml.models.entities.Supplier;
import com.example.cardealerxml.repositories.SupplierRepository;
import com.example.cardealerxml.services.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addSupplier(SupplierAddDto supplierAddDto) {
        if (this.supplierRepository.findSupplierByNameAndImporterEquals(supplierAddDto.getName(), supplierAddDto.isImporter()) != null) {
            return;
        }
        Supplier supplier = this.modelMapper.map(supplierAddDto, Supplier.class);
        this.supplierRepository.saveAndFlush(supplier);
    }

    @Override
    public Supplier getRandomSupplier() {
        Random random = new Random();
        long id = random.nextInt((int) this.supplierRepository.count()) + 1;
        return this.supplierRepository.findById(id).get();
    }

}
