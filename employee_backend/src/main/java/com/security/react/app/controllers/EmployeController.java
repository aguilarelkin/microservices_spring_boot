package com.security.react.app.controllers;

import com.security.react.app.models.entity.Employe;
import com.security.react.app.models.interfaces.IEmployeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = {/*"http://localhost:3000", "http://127.0.0.1:3000",*/ "http://127.0.0.1"})
@RestController
@RequestMapping("/api/v1/emp")
public class EmployeController {

    @Autowired
    private IEmployeService employeService;

    @GetMapping("/employes")
    public ResponseEntity<?> findEmployes() {
        Map<String, Object> response = new HashMap<>();
        List<Employe> data = null;
        try {
            data = employeService.findEmployes();
        } catch (Exception e) {

            response.put("mensaje", "Error al realizar la consulta en la base de datos j");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (data.isEmpty()) {
            response.put("mensaje", "No existe Empleado en la base de datos!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Employe>>(data, HttpStatus.OK);
    }

    @GetMapping("/employe/{id}")
    public ResponseEntity<?> findEmployeId(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Employe> data;
        try {
            data = employeService.findEmployeId(Long.parseLong(id));
            if (data.isPresent()) response.put("data", data);

        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (data.isEmpty()) {
            response.put("mensaje", "No existe Empleado en la base de datos!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PostMapping("/employe")
    public ResponseEntity<?> createEmploye(@Valid @RequestBody Employe employe, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        Employe data;
        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors().stream().map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage()).collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.PRECONDITION_REQUIRED);
        }

        if (employeService.findEmployeCorreo(employe.getCorreo()) != null) {
            response.put("mensaje", "Empleado existe");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            data = employeService.createEmploye(employe);
            if (data != null) {
                response.put("mensaje", "Empleado creado con éxito");
                response.put("employe", data);
            }
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PutMapping("/employe/{id}")
    public ResponseEntity<?> updateEmploye(@Valid @RequestBody Employe employe, BindingResult result, @PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        Employe dataFinal = null;
        Optional<Employe> dataUpdate;
        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors().stream().map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage()).collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.PRECONDITION_REQUIRED);
        }

        try {
            if (!employe.getId().equals(Long.parseLong(id))) {
                response.put("mensaje", "Error: no se pudo editar, el empleado ".concat(" no existe en la base de datos!"));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
            dataUpdate = employeService.findEmployeId(employe.getId());
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (dataUpdate.isEmpty()) {
            response.put("mensaje", "Error: no se pudo editar, el empleado ID: ".concat(employe.getId().toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            dataUpdate.get().setNombre(employe.getNombre());
            dataUpdate.get().setApellido(employe.getApellido());
            dataUpdate.get().setCorreo(employe.getCorreo());
            dataUpdate.get().setTelefono(employe.getTelefono());
            dataUpdate.get().setSalario(employe.getSalario());
            dataFinal = employeService.updateEmploye(dataUpdate.get());
            if (dataUpdate.isPresent()) {
                response.put("mensaje", "El cliente ha sido actualizado con éxito!");
                response.put("data", dataUpdate);
            }
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping("/employe/{id}")
    public ResponseEntity<?> deleteEmploye(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (id == null) {
                response.put("mensaje", "Error al realizar la consulta en la base de datos");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }
            employeService.deleteEmploye(Long.parseLong(id));
            response.put("mensaje", "Empleado eliminado con éxito");

        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
