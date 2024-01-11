package com.security.react.app.models.dao;

import com.security.react.app.models.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplierDao extends JpaRepository<Supplier, Long> {

    public Supplier findByCedula(Long cedula);
}
