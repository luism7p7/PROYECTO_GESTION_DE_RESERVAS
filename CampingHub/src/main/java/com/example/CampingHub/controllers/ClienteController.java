package com.example.CampingHub.controllers;

import com.example.CampingHub.entities.Cliente;
import com.example.CampingHub.services.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Cliente cliente) {
        try {
            Cliente nuevoCliente = clienteService.crearCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al crear el cliente.");
        }
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        try {
            List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
            return ResponseEntity.ok(clientes);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al obtener todos los clientes.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            Cliente cliente = clienteService.obtenerClientePorId(id);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al obtener el cliente con ID: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Cliente cliente, @PathVariable Long id) {
        try {
            cliente.setIdUsuario(id); // Cliente hereda de Usuario
            Cliente clienteActualizado = clienteService.actualizarCliente(cliente);
            return ResponseEntity.ok(clienteActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al actualizar el cliente con ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            clienteService.eliminarCliente(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/realizar-reserva/{id}")
    public ResponseEntity<?> realizarReserva(@PathVariable Long id) {
        try {
            Cliente cliente = clienteService.realizarReserva(id);
            return ResponseEntity.ok(cliente);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al realizar la reserva para el cliente con ID: " + id);
        }
    }
}