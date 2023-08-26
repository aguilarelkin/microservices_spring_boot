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
    public void findEmployeCorreo() {
        String correo = "natalia@gamil.com";
        Employe employe3 = new Employe(3L, "Natalia", "Type", "natalia@gmail.com", "123456", 800000.1);

        when(employeDao.findByCorreo(correo)).thenReturn(employe3);
        Employe employe = employeService.findEmployeCorreo(correo);
        verify(employeDao, times(1)).findByCorreo(correo);

    }

    @Test
    public void createEmploye() {
        Employe employe3 = new Employe(3L, "Natalia", "Type", "natalia@gmail.com", "123456", 800000.1);

        when(employeDao.save(employe3)).thenReturn(employe3);
        employeService.createEmploye(employe3);
        verify(employeDao, times(1)).save(employe3);

    }

    @Test
    public void updateEmploye() {
        Employe employe3 = new Employe("Natalia", "Type", "natalia@gmail.com", "123456", 800000.1);

        when(employeDao.save(employe3)).thenReturn(employe3);
        employeService.updateEmploye(employe3);
        verify(employeDao, times(1)).save(employe3);
    }

    @Test
    public void deleteEmploye() {
        Long id  = 1L;
        employeService.deleteEmploye(id);
        verify(employeDao, times(1)).deleteById(id);
    }

}
