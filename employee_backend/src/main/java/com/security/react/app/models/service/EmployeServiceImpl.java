package com.security.react.app.models.service;

import com.security.react.app.models.dao.IEmployeDao;
import com.security.react.app.models.entity.Employe;
import com.security.react.app.models.interfaces.IEmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeServiceImpl implements IEmployeService {

    @Autowired
    private IEmployeDao employeDao;

    @Override
    public List<Employe> findEmployes() {

        return employeDao.findAll();
    }

    @Override
    public Optional<Employe> findEmployeId(Long id) {
        return employeDao.findById(id);
    }

    @Override
    public Employe findEmployeCorreo(String correo) {
        return employeDao.findByCorreo(correo);
    }

    @Override
    public Employe createEmploye(Employe employe) {
        return employeDao.save(employe);
    }

    @Override
    public Employe updateEmploye(Employe employe) {
        return employeDao.save(employe);
    }

    @Override
    public void deleteEmploye(Long id) {
        employeDao.deleteById(id);
    }
}
