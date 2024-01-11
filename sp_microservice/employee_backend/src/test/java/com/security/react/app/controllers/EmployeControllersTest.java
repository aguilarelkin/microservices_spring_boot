package com.security.react.app.controllers;

import com.security.react.app.models.entity.Employe;
import com.security.react.app.models.interfaces.IEmployeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeControllersTest {

    @InjectMocks
    private EmployeController employeController;

    @Mock
    private BindingResult result;

    @Mock
    private IEmployeService employeService;

    //connection fail bbdd with mockito
    @Test
    public void when_findEmployes_return_200() {
        Employe employe1 = new Employe(1L, "Andres", "Again", "andres@gmail.com", "123456", 4500.1);
        Employe employe2 = new Employe(2L, "Dayana", "Maven", "dayana@gmail.com", "123456", 50000.1);
        Employe employe3 = new Employe(3L, "Natalia", "Type", "natalia@gmail.com", "123456", 800000.1);

        List<Employe> data = new ArrayList<>();
        data.add(employe1);
        data.add(employe2);
        data.add(employe3);

        when(employeService.findEmployes()).thenReturn(data);

        ResponseEntity<?> dat = employeController.findEmployes();
        //Assertions.
        verify(employeService, times(1)).findEmployes();
        assertTrue(dat.toString().contains("200"));
    }

    @Test
    public void when_findEmployes_return_404() {
        when(employeService.findEmployes()).thenReturn(new ArrayList<>());
        ResponseEntity<?> dat = employeController.findEmployes();
        // Assertions.assertThrows();
        assertTrue(dat.toString().contains("No existe Empleado en la base de datos!"));
    }

    @Test
    public void when_findEmployeId_return_200() {
        Long id = 1L;
        Optional<Employe> employe1 = Optional.of(new Employe(1L, "Andres", "Again", "andres@gmail.com", "123456", 4500.1));
        Optional<Employe> employe;
        employe = employe1;
        when(employeService.findEmployeId(id)).thenReturn(employe);
        ResponseEntity<?> dat = employeController.findEmployeId(String.valueOf(id));
        verify(employeService, times(1)).findEmployeId(id);
        assertTrue(dat.toString().contains("200"));
    }

    @Test
    public void when_findEmployeId_return_404() {
        when(employeService.findEmployeId(45L)).thenReturn(Optional.empty());
        ResponseEntity<?> dat = employeController.findEmployeId("45");
        // Assertions.assertThrows();
        verify(employeService, times(1)).findEmployeId(45L);
        assertTrue(dat.toString().contains("No existe Empleado en la base de datos!"));
    }

    @Test
    public void when_findEmployeId_and_idIsNull_return_500() {
        ResponseEntity<?> dat = employeController.findEmployeId(null);
        assertTrue(dat.toString().contains("500"));
    }

    @Test
    public void when_findEmployeId_and_idIsString_return_500() {
        ResponseEntity<?> dat = employeController.findEmployeId("a7");
        assertTrue(dat.toString().contains("500"));
    }

    @Test
    public void when_createEmploye_return_200() {
        Employe employe = new Employe("Natalia", "Type", "natalia@gmail.com", "123456", 800000.1);

        when(employeService.createEmploye(employe)).thenReturn(employe);
        ResponseEntity<?> dat = employeController.createEmploye(employe, result);
        verify(employeService, times(1)).createEmploye(employe);
        assertTrue(dat.toString().contains("200"));
    }

    @Test
    public void when_createEmploye_and_exist_employe_return_400() {
        Employe employe = new Employe("Natalia", "Type", "natalia@gmail.com", "123456", 800000.1);

        when(employeService.findEmployeCorreo("natalia@gmail.com")).thenReturn(employe);

        ResponseEntity<?> dat = employeController.createEmploye(employe, result);
        verify(employeService, times(1)).findEmployeCorreo("natalia@gmail.com");
        assertTrue(dat.toString().contains("Empleado existe"));
    }

    @Test
    public void when_createEmploye_return_428() {
        Employe employe = new Employe();
        Errors errors = new BeanPropertyBindingResult(employe, "datos vacíos");
//result.reject("email", "vacío");
        //result.addAllErrors(errors);

        when(result.hasErrors()).thenReturn(true);
        ResponseEntity<?> dat = employeController.createEmploye(employe, result);

        assertTrue(dat.toString().contains("428"));
    }

    @Test
    public void when_updateEmploye_return_200() {
        Employe employe = new Employe(1L, "Natalia", "Type", "natalia@gmail.com", "123456", 800000.1);
        Employe employe2 = new Employe(1L, "NataliaPrueba", "Type", "natalia@gmail.com", "123456", 800000.1);

        Long id = 1L;
        when(employeService.updateEmploye(employe)).thenReturn(employe);
        when(employeService.findEmployeId(id)).thenReturn(Optional.of(employe2));
        ResponseEntity<?> dat = employeController.updateEmploye(employe, result, String.valueOf(id));
        verify(employeService, times(1)).updateEmploye(employe);
        verify(employeService, times(1)).findEmployeId(id);

        assertTrue(dat.toString().contains("200"));
    }

    @Test
    public void when_updateEmploye_return_404() {
        Employe employe = new Employe(1L, "Natalia", "Type", "natalia@gmail.com", "123456", 800000.1);
        Long id = 1L;
        when(employeService.findEmployeId(id)).thenReturn(Optional.empty());
        ResponseEntity<?> dat = employeController.updateEmploye(employe, result, String.valueOf(id));
        verify(employeService, times(1)).findEmployeId(id);
        assertTrue(dat.toString().contains("404"));
    }

    @Test
    public void when_updateEmploye_return_428() {
        Employe employe = new Employe(1L, "Natalia", "Type", "natalia@gmail.com", "123456", 800000.1);
        Long id = 1L;
        when(result.hasErrors()).thenReturn(true);
        ResponseEntity<?> dat = employeController.updateEmploye(employe, result, String.valueOf(id));
        verify(result, times(1)).hasErrors();
        assertTrue(dat.toString().contains("428"));
    }

    @Test
    public void when_updateEmploye_and_idIncorrect_return_404() {
        Employe employe = new Employe(2L, "Natalia", "Type", "natalia@gmail.com", "123456", 800000.1);
        Long id = 1L;
        ResponseEntity<?> dat = employeController.updateEmploye(employe, result, String.valueOf(id));
        assertTrue(dat.toString().contains("404 NOT_FOUND"));
    }

    @Test
    public void when_updateEmploye_and_idIsNull_return_500() {
        Employe employe = new Employe(2L, "Natalia", "Type", "natalia@gmail.com", "123456", 800000.1);
        ResponseEntity<?> dat = employeController.updateEmploye(employe, result, null);
        assertTrue(dat.toString().contains("500 INTERNAL_SERVER_ERROR"));
    }

    @Test
    public void when_updateEmploye_and_idIsString_return_500() {
        Employe employe = new Employe(2L, "Natalia", "Type", "natalia@gmail.com", "123456", 800000.1);
        ResponseEntity<?> dat = employeController.updateEmploye(employe, result, "a78");
        assertTrue(dat.toString().contains("500 INTERNAL_SERVER_ERROR"));
    }

    @Test
    public void when_deleteEmploye_200() {
        Long id = 1L;
        ResponseEntity<?> dat = employeController.deleteEmploye(String.valueOf(id));
        verify(employeService, times(1)).deleteEmploye(id);
    }

    @Test
    public void when_deleteEmploye_400() {
        ResponseEntity<?> dat = employeController.deleteEmploye(null);
        assertTrue(dat.toString().contains("400"));
    }

    @Test
    public void when_deleteEmploye_and_idIsString_500() {
        ResponseEntity<?> dat = employeController.deleteEmploye("a45");
        assertTrue(dat.toString().contains("500 INTERNAL_SERVER_ERROR"));
    }
}
