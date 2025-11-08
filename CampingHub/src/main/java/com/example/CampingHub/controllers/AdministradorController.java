package com.example.CampingHub.controllers;

import com.example.CampingHub.entities.Administrador;
import com.example.CampingHub.services.AdministradorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    private final AdministradorService administradorService;

    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Administrador administrador) {
        try {
            Administrador nuevoAdmin = administradorService.crearAdministrador(administrador);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAdmin);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al crear el administrador.");
        }
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        try {
            List<Administrador> administradores = administradorService.obtenerTodosLosAdministradores();
            return ResponseEntity.ok(administradores);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al obtener todos los administradores.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            Administrador administrador = administradorService.obtenerAdministradorPorId(id);
            return ResponseEntity.ok(administrador);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al obtener el administrador con ID: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Administrador administrador, @PathVariable Long id) {
        try {
            administrador.setIdUsuario(id); // Administrador hereda de Usuario
            Administrador adminActualizado = administradorService.actualizarAdministrador(administrador);
            return ResponseEntity.ok(adminActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al actualizar el administrador con ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            administradorService.eliminarAdministrador(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Métodos específicos de Administrador
    @PutMapping("/gestionar-finca/{id}")
    public ResponseEntity<?> gestionarFinca(@PathVariable Long id) {
        try {
            Administrador administrador = administradorService.gestionarFinca(id);
            return ResponseEntity.ok(administrador);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al gestionar la finca para el administrador con ID: " + id);
        }
    }

    @PutMapping("/gestionar-reserva/{id}")
    public ResponseEntity<?> gestionarReserva(@PathVariable Long id) {
        try {
            Administrador administrador = administradorService.gestionarReserva(id);
            return ResponseEntity.ok(administrador);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al gestionar la reserva para el administrador con ID: " + id);
        }
    }
}