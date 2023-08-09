package com.security.react.app.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "employes")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "nombre vacío")
    private String nombre;
    @NotEmpty(message = "apellido vacío")
    private String apellido;

    @NotEmpty(message = "correo vacío")
    @Email
    @Column(unique = true)
    private String correo;
    @NotEmpty(message = "teléfono vacío")
    private String telefono;
    @NotNull(message = "salario vacío")
    private Double salario;


}
