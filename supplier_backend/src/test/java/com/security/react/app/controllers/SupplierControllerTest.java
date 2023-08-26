package com.security.react.app.controllers;

import com.security.react.app.models.entity.Supplier;
import com.security.react.app.models.interfaces.ISupplierService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SupplierControllerTest {

    @InjectMocks
    private SupplierController supplierController;

    @Mock
    private ISupplierService supplierService;

    @Mock
    private BindingResult result;

    @Test
    public void findSupplier() {
        Supplier supplier = new Supplier(1L, 123456L, "Dana", "Paz", "3184127812", "seguridad", (byte) 1);
        Supplier supplier2 = new Supplier(2L, 123457L, "Diana", "Trum", "3184127812", "transporte", (byte) 2);

        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(supplier);
        suppliers.add(supplier2);

        when(supplierService.findSuppliers()).thenReturn(suppliers);
        supplierController.findSupplier();
        verify(supplierService, times(1)).findSuppliers();
    }

    @Test
    public void findSupplierId() {
        Long id = 1L;


        Optional<Supplier> optionalSupplier = Optional.of(new Supplier(1L, 123456L, "Dana", "Paz", "3184127812", "seguridad", (byte) 1));
        when(supplierService.findSupplierId(id)).thenReturn(optionalSupplier);
        supplierController.findSupplierId(id);
        verify(supplierService, times(1)).findSupplierId(id);
    }

    @Test
    public void createSupplier() {

        Supplier supplier = new Supplier(123456L, "Dana", "Paz", "3184127812", "seguridad", (byte) 1);
       // when(supplierService.findSupplierCedula(123456l)).thenReturn(new Supplier(2L,123456L, "Dana", "Paz", "3184127812", "seguridad", (byte) 1));
        when(supplierService.createSupplier(supplier)).thenReturn(supplier);
        supplierController.createSupplier(supplier, result);
        verify(supplierService, times(1)).findSupplierCedula(123456L);
        verify(supplierService, times(1)).createSupplier(supplier);

    }

    @Test
    public void updateSupplier() {
        Supplier supplier = new Supplier(2L, 123456L, "Dana", "Paz", "3184127812", "seguridad", (byte) 1);
        Supplier supplier2 = new Supplier(2L, 123457L, "Diana", "Trum", "3184127812", "transporte", (byte) 2);
        Long id = 2L;
        when(supplierService.findSupplierId(id)).thenReturn(Optional.of(supplier2));
        when(supplierService.updateSupplier(supplier2)).thenReturn(supplier2);
        supplierController.updateSupplier(supplier, result, id);
        verify(supplierService, times(1)).updateSupplier(supplier2);
        verify(supplierService, times(1)).findSupplierId(id);
    }

    @Test
    public void deleteSupplier() {
        Long id = 2L;
        supplierController.deleteSupplier(id);
        verify(supplierService, times(1)).deleteSupplier(id);
    }
}
