package com.security.react.app.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//Proveedores
@Entity
@Table(name = "suppliers")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Supplier {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id    private Long id;
    @NotNull
    @Column(unique = true)
    private Long cedula;
    @NotEmpty
    private String nombre;
    @NotEmpty
    private String apellido;
    @NotEmpty
    private String telefono;
    @NotEmpty
    private String service;
    @NotNull
    private Byte contrato;

}
