package com.store.controller;

import com.store.dto.supplier.SupplierDTO;
import com.store.service.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/suppliers")
public class SupplierController {


    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/{supplierId}")
    public ResponseEntity<SupplierDTO> getSupplierById(
            @PathVariable Integer supplierId
    ) {
        SupplierDTO supplierDTO = supplierService.findSupplierById(supplierId);
        return ResponseEntity.ok(supplierDTO);

    }
}
