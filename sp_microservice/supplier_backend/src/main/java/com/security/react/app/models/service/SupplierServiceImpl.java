package com.security.react.app.models.service;

import com.security.react.app.models.dao.ISupplierDao;
import com.security.react.app.models.entity.Supplier;
import com.security.react.app.models.interfaces.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements ISupplierService {

    @Autowired
    private ISupplierDao supplierDao;

    @Override
    public List<Supplier> findSuppliers() {
        return supplierDao.findAll();
    }

    @Override
    public Optional<Supplier> findSupplierId(Long id) {
        return supplierDao.findById(id);
    }

    @Override
    public Supplier findSupplierCedula(Long cedula) {
        return supplierDao.findByCedula(cedula);
    }

    @Override
    public Supplier createSupplier(Supplier supplier) {
        return supplierDao.save(supplier);
    }

    @Override
    public Supplier updateSupplier(Supplier supplier) {
        return supplierDao.save(supplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        supplierDao.deleteById(id);
    }
}
