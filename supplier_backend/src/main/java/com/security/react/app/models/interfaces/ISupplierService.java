package com.security.react.app.models.interfaces;

import com.security.react.app.models.entity.Supplier;

import java.util.List;
import java.util.Optional;

public interface ISupplierService {

    List<Supplier> findSuppliers();

    Optional<Supplier> findSupplierId(Long id);

    Supplier findSupplierCedula(Long cedula);

    Supplier createSupplier(Supplier supplier);

    Supplier updateSupplier(Supplier supplier);

    void deleteSupplier(Long id);
}
