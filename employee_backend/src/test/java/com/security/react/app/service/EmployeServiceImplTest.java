package com.security.react.app.service;

import com.security.react.app.models.dao.IEmployeDao;
import com.security.react.app.models.entity.Employe;
import com.security.react.app.models.service.EmployeServiceImpl;
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
public class EmployeServiceImplTest {

    @InjectMocks
    private EmployeServiceImpl employeService;

    @Mock
    private IEmployeDao employeDao;

    @Test
    public void findEmployes() {
        Employe employe1 = new Employe(1L, "Andres", "Again", "andres@gmail.com", "123456", 4500.1);
        Employe employe2 = new Employe(2L, "Dayana", "Maven", "dayana@gmail.com", "123456", 50000.1);
        Employe employe3 = new Employe(3L, "Natalia", "Type", "natalia@gmail.com", "123456", 800000.1);

        List<Employe> data = new ArrayList<>();
        data.add(employe1);
        data.add(employe2);
        data.add(employe3);
        when(employeDao.findAll()).thenReturn(data);
        List<Employe> a = employeService.findEmployes();

        verify(employeDao, times(1)).findAll();
        assertTrue(a.size() > 0);

    }

    @Test
    public void findEmployeId() {
        Long a = 1L;
        Employe employe3 = new Employe(3L, "Natalia", "Type", "natalia@gmail.com", "123456", 800000.1);

        when(employeDao.findById(a)).thenReturn(Optional.of(employe3));

        Optional<Employe> aa = employeService.findEmployeId(a);
        verify(employeDao, times(1)).findById(a);
    }

    @Test
    public void when_findEmployeId_return_employe() {
        Long a = 1L;
        Employe employe3 = new Employe(3L, "Natalia", "Type", "natalia@gmail.com", "123456", 800000.1);
        when(employeDao.findById(a)).thenReturn(Optional.of(employe3));
        Optional<Employe> aa = employeService.findEmployeId(a);
        assertEquals(employe3, aa.get());
    }

    @Test
    public void when_findEmployeId_return_null() {
        Optional<Employe> aa = employeService.findEmployeId(null);
        assertEquals(Optional.empty(), aa);
    }

    @Test
    public void findEmployeCorreo() {
        String correo = "natalia@gamil.com";
        Employe employe3 = new Employe(3L, "Natalia", "Type", "natalia@gmail.com", "123456", 800000.1);

        when(employeDao.findByCorreo(correo)).thenReturn(employe3);
        Employe employe = employeService.findEmployeCorreo(correo);
        verify(employeDao, times(1)).findByCorreo(correo);
        assertTrue(employe != null);

    }

    @Test
    public void when_findEmployeCorreo_return_employe() {
        String correo = "natalia@gamil.com";
        Employe employe3 = new Employe(3L, "Natalia", "Type", "natalia@gmail.com", "123456", 800000.1);

        when(employeDao.findByCorreo(correo)).thenReturn(employe3);
        Employe employe = employeService.findEmployeCorreo(correo);
        assertEquals(employe3, employe);
    }

    @Test
    public void when_findEmployeCorreo_return_null() {
        Employe employe = employeService.findEmployeCorreo(null);
        assertEquals(null, employe);
    }

    @Test
    public void createEmploye() {
        Employe employe3 = new Employe(3L, "Natalia", "Type", "natalia@gmail.com", "123456", 800000.1);

        when(employeDao.save(employe3)).thenReturn(employe3);
        Employe data = employeService.createEmploye(employe3);
        verify(employeDao, times(1)).save(employe3);
        assertTrue(data != null);
    }

    @Test
    public void when_createEmploye_return_employe() {
        Employe employe3 = new Employe(3L, "Natalia", "Type", "natalia@gmail.com", "123456", 800000.1);

        when(employeDao.save(employe3)).thenReturn(employe3);
        Employe data = employeService.createEmploye(employe3);
        assertEquals(employe3, data);
    }

    @Test
    public void when_createEmploye_return_null() {
        Employe employe3 = new Employe();
        Employe data = employeService.createEmploye(employe3);
        assertEquals(null, data);
    }

    @Test
    public void updateEmploye() {
        Employe employe3 = new Employe("Natalia", "Type", "natalia@gmail.com", "123456", 800000.1);

        when(employeDao.save(employe3)).thenReturn(employe3);
        Employe data = employeService.updateEmploye(employe3);
        verify(employeDao, times(1)).save(employe3);
        assertTrue(data != null);
    }

    @Test
    public void when_updateEmploye_return_employe() {
        Employe employe3 = new Employe("Natalia", "Type", "natalia@gmail.com", "123456", 800000.1);

        when(employeDao.save(employe3)).thenReturn(employe3);
        Employe data = employeService.updateEmploye(employe3);
        assertEquals(employe3, data);
    }

    @Test
    public void when_updateEmploye_return_null() {
        Employe data = employeService.createEmploye(null);
        assertEquals(null, data);
    }

    @Test
    public void deleteEmploye() {
        Long id = 1L;
        employeService.deleteEmploye(id);
        verify(employeDao, times(1)).deleteById(id);

    }
}
