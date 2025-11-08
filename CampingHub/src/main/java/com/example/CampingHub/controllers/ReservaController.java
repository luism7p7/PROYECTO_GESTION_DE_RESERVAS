package com.example.CampingHub.controllers;

import com.example.CampingHub.entities.Reserva;
import com.example.CampingHub.services.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {


    private final ReservaService reservaService;


    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    // Endpoint: POST /reservas/crear
    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Reserva reserva) {
        try {
            Reserva nuevaReserva = reservaService.crearReserva(reserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva);
        } catch (RuntimeException e) {
            // Se usa BAD_REQUEST porque la RuntimeException en el servicio indica que falta el Cliente o la Finca (error de datos de entrada).
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al crear la reserva.");
        }
    }

    // Endpoint: PUT /reservas/confirmar/{id}
    @PutMapping("/confirmar/{id}")
    public ResponseEntity<?> confirmar(@PathVariable Long id) {
        try {
            Reserva reservaConfirmada = reservaService.confirmarReserva(id);
            return ResponseEntity.ok(reservaConfirmada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al confirmar la reserva con ID: " + id);
        }
    }

    // Endpoint: PUT /reservas/cancelar/{id}
    @PutMapping("/cancelar/{id}")
    public ResponseEntity<?> cancelar(@PathVariable Long id) {
        try {
            Reserva reservaCancelada = reservaService.cancelarReserva(id);
            return ResponseEntity.ok(reservaCancelada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al cancelar la reserva con ID: " + id);
        }
    }

    // Endpoint: GET /reservas
    @GetMapping
    public ResponseEntity<?> obtenerTodas() {
        try {
            List<Reserva> reservas = reservaService.obtenerTodasLasReservas();
            return ResponseEntity.ok(reservas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al obtener todas las reservas.");
        }
    }

    // Endpoint: GET /reservas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            Reserva reserva = reservaService.obtenerReservaPorId(id);
            return ResponseEntity.ok(reserva);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al obtener la reserva con ID: " + id);
        }
    }
}