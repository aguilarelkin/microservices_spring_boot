package com.security.react.app.models.dao;

import com.security.react.app.models.entity.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeDao extends JpaRepository<Employe, Long> {

    public Employe findByCorreo(String correo);
}
