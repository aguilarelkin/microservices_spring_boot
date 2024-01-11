package com.security.react.app.models.interfaces;

import com.security.react.app.models.entity.Employe;

import java.util.List;
import java.util.Optional;

public interface IEmployeService {

    public List<Employe> findEmployes();

    public Optional<Employe> findEmployeId(Long id);

    public Employe findEmployeCorreo(String correo);
    public Employe createEmploye(Employe employe);

    public Employe updateEmploye(Employe employe);

    public void deleteEmploye(Long id);
}
