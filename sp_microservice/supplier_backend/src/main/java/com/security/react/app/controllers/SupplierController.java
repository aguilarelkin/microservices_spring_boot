package com.security.react.app.controllers;

import com.security.react.app.models.entity.Supplier;
import com.security.react.app.models.interfaces.ISupplierService;
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

@CrossOrigin(origins = {/*"http://localhost:3000", "http://127.0.0.1:3000","http://127.0.0.1:8091*/"http://localhost:8080","http://localhost"})
@RestController
@RequestMapping("/api/v1/sup")
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;

    @GetMapping("/suppliers")
    public ResponseEntity<?> findSupplier() {
        Map<String, Object> response = new HashMap<>();
        List<Supplier> data = null;
        try {
            data = supplierService.findSuppliers();
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (data.isEmpty()) {
            response.put("mensaje", "No existe proveedor en la base de datos!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Supplier>>(data, HttpStatus.OK);
    }

    @GetMapping("/supplier/{id}")
    public ResponseEntity<?> findSupplierId(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        Optional<Supplier> data;
        try {
            data = supplierService.findSupplierId(Long.parseLong(id));
            if (data.isPresent()) response.put("data", data);

        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (data.isEmpty()) {
            response.put("mensaje", "No existe proveedor en la base de datos!");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PostMapping("/supplier")
    public ResponseEntity<?> createSupplier(@Valid @RequestBody Supplier supplier, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        Supplier data;
        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors().stream().map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage()).collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.PRECONDITION_REQUIRED);
        }

        try {
            if (supplierService.findSupplierCedula(supplier.getCedula()) != null) {
                response.put("mensaje", "Proveedor existe");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }

            data = supplierService.createSupplier(supplier);
            if (data != null) {
                response.put("mensaje", "Proveedor creado con éxito");
                response.put("employe", data);
            }
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PutMapping("/supplier/{id}")
    public ResponseEntity<?> updateSupplier(@Valid @RequestBody Supplier supplier, BindingResult result, @PathVariable String id) {
        Map<String, Object> response = new HashMap<>();

        Optional<Supplier> dataUpdate = null;

        Supplier dataFinal = null;
        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors().stream().map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage()).collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.PRECONDITION_REQUIRED);
        }

        try {
            if (!supplier.getId().equals(Long.parseLong(id))) {
                response.put("mensaje", "Error: no se pudo editar, el proveedor :(".concat(" no existe en la base de datos!"));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
            dataUpdate = supplierService.findSupplierId(supplier.getId());
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (dataUpdate.isEmpty()) {
            response.put("mensaje", "Error: no se pudo editar, el empleado ID: ".concat(supplier.getId().toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            dataUpdate.get().setNombre(supplier.getNombre());
            dataUpdate.get().setApellido(supplier.getApellido());
            dataUpdate.get().setCedula(supplier.getCedula());
            dataUpdate.get().setTelefono(supplier.getTelefono());
            dataUpdate.get().setContrato(supplier.getContrato());
            dataUpdate.get().setService(supplier.getService());

            dataFinal = supplierService.updateSupplier(dataUpdate.get());
            if (dataUpdate.isPresent()) response.put("mensaje", "El proveedor ha sido actualizado con éxito!");
        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping("/supplier/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        try {
            supplierService.deleteSupplier(Long.parseLong(id));
            response.put("mensaje", "Proveedor eliminado con éxito");

        } catch (Exception e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
