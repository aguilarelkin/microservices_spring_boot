package com.security.react.app.models.services;

import com.security.react.app.models.dao.ISupplierDao;
import com.security.react.app.models.entity.Supplier;
import com.security.react.app.models.service.SupplierServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SupplierServiceImplTest {

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Mock
    private ISupplierDao supplierDao;

    @Test
    public void findSuppliers() {
        Supplier supplier = new Supplier(1L, 123456L, "Dana", "Paz", "3184127812", "seguridad", (byte) 1);
        Supplier supplier2 = new Supplier(2L, 123457L, "Diana", "Trum", "3184127812", "transporte", (byte) 2);

        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(supplier);
        suppliers.add(supplier2);
        when(supplierDao.findAll()).thenReturn(suppliers);
        List<Supplier> data = supplierService.findSuppliers();
        verify(supplierDao, times(1)).findAll();
        assertTrue(data.size() > 0);
    }

    @Test
    public void findSupplierId() {
        Long id = 1L;
        Optional<Supplier> optionalSupplier = Optional.of(new Supplier(1L, 123456L, "Dana", "Paz", "3184127812", "seguridad", (byte) 1));
        when(supplierDao.findById(id)).thenReturn(optionalSupplier);
        supplierService.findSupplierId(id);
        verify(supplierDao, times(1)).findById(id);
    }

    @Test
    public void when_findSupplierId_and_idIsNull() {
        Optional<Supplier> data = supplierService.findSupplierId(null);
        verify(supplierDao, times(1)).findById(null);
        assertTrue(data.toString().contains("Optional.empty"));
    }

    @Test
    public void findSupplierCedula() {
        Long cedula = 123456L;
        Supplier supplier = new Supplier(1L, 123456L, "Dana", "Paz", "3184127812", "seguridad", (byte) 1);

        when(supplierDao.findByCedula(cedula)).thenReturn(supplier);
        supplierService.findSupplierCedula(cedula);
        verify(supplierDao, times(1)).findByCedula(cedula);
    }

    @Test
    public void when_findSupplierCedula_and_cedulaIsNull() {
        Supplier data = supplierService.findSupplierCedula(null);
        verify(supplierDao, times(1)).findByCedula(null);
        assertEquals(null, data);
    }

    @Test
    public void createSupplier() {
        Supplier supplier = new Supplier(1L, 123456L, "Dana", "Paz", "3184127812", "seguridad", (byte) 1);
        when(supplierDao.save(supplier)).thenReturn(supplier);
        supplierService.createSupplier(supplier);
        verify(supplierDao, times(1)).save(supplier);
    }

    @Test
    public void when_createSupplier_and_supplierIsNull() {
        Supplier data = supplierService.createSupplier(null);
        verify(supplierDao, times(1)).save(null);
        assertEquals(null, data);
    }

    @Test
    public void updateSupplier() {
        Supplier supplier = new Supplier(1L, 123456L, "Dana", "Paz", "3184127812", "seguridad", (byte) 1);
        when(supplierDao.save(supplier)).thenReturn(supplier);
        supplierService.updateSupplier(supplier);
        verify(supplierDao, times(1)).save(supplier);
    }

    @Test
    public void when_updateSupplier_and_supplierIsNull() {
        Supplier data = supplierService.updateSupplier(null);
        verify(supplierDao, times(1)).save(null);
        assertEquals(null, data);
    }

    @Test
    public void deleteSupplier() {
        supplierService.deleteSupplier(123456L);
        verify(supplierDao, times(1)).deleteById(123456L);
    }


}
