package com.security.react.app.controllers;

import com.security.react.app.models.entity.Supplier;
import com.security.react.app.models.interfaces.ISupplierService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
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
    public void when_findSupplier_return_404() {
        when(supplierService.findSuppliers()).thenReturn(new ArrayList<>());
        ResponseEntity<?> data = supplierController.findSupplier();
        assertTrue(data.toString().contains("No existe proveedor en la base de datos!"));
    }

    @Test
    public void findSupplierId() {
        Long id = 1L;
        Optional<Supplier> optionalSupplier = Optional.of(new Supplier(1L, 123456L, "Dana", "Paz", "3184127812", "seguridad", (byte) 1));
        when(supplierService.findSupplierId(id)).thenReturn(optionalSupplier);
        ResponseEntity<?> data = supplierController.findSupplierId(String.valueOf(id));
        verify(supplierService, times(1)).findSupplierId(id);
        assertTrue(data.toString().contains("200"));
    }

    @Test
    public void when_findSupplierId_404() {
        Long id = 1L;
        when(supplierService.findSupplierId(id)).thenReturn(Optional.empty());
        ResponseEntity<?> data = supplierController.findSupplierId(String.valueOf(id));
        assertTrue(data.toString().contains("No existe proveedor en la base de datos!"));
    }

    @Test
    public void when_findSupplierId_And_IdIsNull_500() {
        ResponseEntity<?> data = supplierController.findSupplierId(null);
        assertTrue(data.toString().contains("500 INTERNAL_SERVER_ERROR"));
    }

    @Test
    public void when_findSupplierId_and_IdIsString_500() {
        ResponseEntity<?> data = supplierController.findSupplierId("data4");
        assertTrue(data.toString().contains("500 INTERNAL_SERVER_ERROR"));
    }

    @Test
    public void createSupplier() {

        Supplier supplier = new Supplier(123456L, "Dana", "Paz", "3184127812", "seguridad", (byte) 1);
        // when(supplierService.findSupplierCedula(123456l)).thenReturn(new Supplier(2L,123456L, "Dana", "Paz", "3184127812", "seguridad", (byte) 1));
        when(supplierService.createSupplier(supplier)).thenReturn(supplier);
        ResponseEntity<?> data = supplierController.createSupplier(supplier, result);
        verify(supplierService, times(1)).findSupplierCedula(123456L);
        verify(supplierService, times(1)).createSupplier(supplier);
        assertTrue(data.toString().contains("200"));
    }

    @Test
    public void when_createSupplier_400() {
        Supplier supplier = new Supplier(123456L, "Dana", "Paz", "3184127812", "seguridad", (byte) 1);
        when(supplierService.findSupplierCedula(123456L)).thenReturn(supplier);
        ResponseEntity<?> data = supplierController.createSupplier(supplier, result);
        assertTrue(data.toString().contains("Proveedor existe"));
    }

    @Test
    public void when_createSupplier_And_IdIsNull_500() {
        ResponseEntity<?> data = supplierController.createSupplier(null, result);
        assertTrue(data.toString().contains("500 INTERNAL_SERVER_ERROR"));
    }

    @Test
    public void when_createSupplier_and_ResultIsEmpty_428() {
        Supplier supplier = new Supplier(123456L, "Dana", "Paz", "3184127812", "seguridad", (byte) 1);
        when(result.hasErrors()).thenReturn(true);
        ResponseEntity<?> data = supplierController.createSupplier(supplier, result);
        assertTrue(data.toString().contains("428"));
    }

    @Test
    public void updateSupplier() {
        Supplier supplier = new Supplier(2L, 123456L, "Dana", "Paz", "3184127812", "seguridad", (byte) 1);
        Supplier supplier2 = new Supplier(2L, 123457L, "Diana", "Trum", "3184127812", "transporte", (byte) 2);
        Long id = 2L;
        when(supplierService.findSupplierId(id)).thenReturn(Optional.of(supplier2));
        when(supplierService.updateSupplier(supplier2)).thenReturn(supplier2);
        ResponseEntity<?> data = supplierController.updateSupplier(supplier, result, String.valueOf(id));
        verify(supplierService, times(1)).updateSupplier(supplier2);
        verify(supplierService, times(1)).findSupplierId(id);
        assertTrue(data.toString().contains("200"));
    }

    @Test
    public void when_updateSupplier_404() {
        Supplier supplier = new Supplier(2L, 123456L, "Dana", "Paz", "3184127812", "seguridad", (byte) 1);
        when(supplierService.findSupplierId(supplier.getId())).thenReturn(Optional.empty());
        ResponseEntity<?> data = supplierController.updateSupplier(supplier, result, String.valueOf(supplier.getId()));
        assertTrue(data.toString().contains("404"));
    }

    @Test
    public void when_updateSupplier_and_IdIsIncorrect_404() {
        Supplier supplier = new Supplier(2L, 123456L, "Dana", "Paz", "3184127812", "seguridad", (byte) 1);
        ResponseEntity<?> data = supplierController.updateSupplier(supplier, result, "4");
        assertTrue(data.toString().contains("404"));
    }

    @Test
    public void when_updateSupplier_And_IdIsNull_500() {
        Supplier supplier = new Supplier(2L, 123456L, "Dana", "Paz", "3184127812", "seguridad", (byte) 1);
        ResponseEntity<?> data = supplierController.updateSupplier(supplier, result, null);
        assertTrue(data.toString().contains("500 INTERNAL_SERVER_ERROR"));
    }

    @Test
    public void when_updateSupplier_and_IdIsString_500() {
        Supplier supplier = new Supplier(2L, 123456L, "Dana", "Paz", "3184127812", "seguridad", (byte) 1);
        ResponseEntity<?> data = supplierController.updateSupplier(supplier, result, "null");
        assertTrue(data.toString().contains("500 INTERNAL_SERVER_ERROR"));
    }

    @Test
    public void deleteSupplier() {
        Long id = 2L;
        ResponseEntity<?> data = supplierController.deleteSupplier(String.valueOf(id));
        verify(supplierService, times(1)).deleteSupplier(id);
        assertTrue(data.toString().contains("200"));
    }

    @Test
    public void when_deleteSupplier_And_IdIsNull_500() {
        ResponseEntity<?> data = supplierController.deleteSupplier(null);
        assertTrue(data.toString().contains("500 INTERNAL_SERVER_ERROR"));
    }

    @Test
    public void when_deleteSupplier_and_IdIsString_500() {
        ResponseEntity<?> data = supplierController.deleteSupplier("null8");
        assertTrue(data.toString().contains("500 INTERNAL_SERVER_ERROR"));
    }
}
