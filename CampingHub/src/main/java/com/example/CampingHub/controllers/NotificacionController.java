package com.example.CampingHub.controllers;

import com.example.CampingHub.entities.Notificacion;
import com.example.CampingHub.services.NotificacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    private final NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Notificacion notificacion) {
        try {
            Notificacion nuevaNotificacion = notificacionService.crearNotificacion(notificacion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaNotificacion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al crear la notificación.");
        }
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodas() {
        try {
            List<Notificacion> notificaciones = notificacionService.obtenerTodasLasNotificaciones();
            return ResponseEntity.ok(notificaciones);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al obtener todas las notificaciones.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            Notificacion notificacion = notificacionService.obtenerNotificacionPorId(id);
            return ResponseEntity.ok(notificacion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al obtener la notificación con ID: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Notificacion notificacion, @PathVariable Long id) {
        try {
            notificacion.setIdNotificacion(id);
            Notificacion notificacionActualizada = notificacionService.actualizarNotificacion(notificacion);
            return ResponseEntity.ok(notificacionActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al actualizar la notificación con ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            notificacionService.eliminarNotificacion(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Método específico de Notificacion
    @PutMapping("/enviar/{id}")
    public ResponseEntity<?> enviar(@PathVariable Long id) {
        try {
            Notificacion notificacion = notificacionService.enviarNotificacion(id);
            return ResponseEntity.ok(notificacion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al enviar la notificación con ID: " + id);
        }
    }
}