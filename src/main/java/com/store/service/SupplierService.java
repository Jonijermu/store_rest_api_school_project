package com.store.service;

import com.store.dto.supplier.SupplierDTO;
import com.store.entity.Supplier;
import com.store.entity.SupplierAddress;
import com.store.mapper.SupplierMapper;
import com.store.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {


    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public SupplierService(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    public SupplierDTO findSupplierById(Integer supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow();
        List<SupplierAddress> addresses = supplier.getSupplierAddresses();
        return supplierMapper.toSupplierDto(supplier, addresses);
    }
}
