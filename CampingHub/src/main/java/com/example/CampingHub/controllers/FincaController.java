package com.example.CampingHub.controllers;

import com.example.CampingHub.entities.Finca;
import com.example.CampingHub.services.FincaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fincas")
public class FincaController {

    private final FincaService fincaService;

    public FincaController(FincaService fincaService) {
        this.fincaService = fincaService;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Finca finca) {
        try {
            Finca nuevaFinca = fincaService.crearFinca(finca);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaFinca);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al crear la finca.");
        }
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodas() {
        try {
            List<Finca> fincas = fincaService.obtenerTodasLasFincas();
            return ResponseEntity.ok(fincas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al obtener todas las fincas.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            Finca finca = fincaService.obtenerFincaPorId(id);
            return ResponseEntity.ok(finca);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al obtener la finca con ID: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Finca finca, @PathVariable Long id) {
        try {
            finca.setIdFinca(id);
            Finca fincaActualizada = fincaService.actualizarFinca(finca);
            return ResponseEntity.ok(fincaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al actualizar la finca con ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            fincaService.eliminarFinca(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Método específico de Finca
    @GetMapping("/disponibilidad/{id}")
    public ResponseEntity<?> mostrarDisponibilidad(@PathVariable Long id) {
        try {
            Finca finca = fincaService.mostrarDisponibilidad(id);
            return ResponseEntity.ok(finca);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al mostrar la disponibilidad de la finca con ID: " + id);
        }
    }
}