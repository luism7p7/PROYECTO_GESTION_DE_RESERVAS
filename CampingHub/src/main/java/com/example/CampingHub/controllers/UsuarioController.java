package com.example.CampingHub.controllers;

import com.example.CampingHub.entities.Usuario;
import com.example.CampingHub.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Usuario usuario) {
        try {
            // Lógica de negocio: El registro de un nuevo usuario se realiza aquí
            Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
            // Simular inicio de sesión después del registro para obtener el ID
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al crear el usuario.");
        }
    }

    // NUEVO: Endpoint para el inicio de sesión
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginUsuario) {
        try {
            // Lógica de negocio: Buscar al usuario por correo y contraseña en MySQL
            Usuario usuarioEncontrado = usuarioService.login(loginUsuario.getCorreo(), loginUsuario.getContraseña());

            // Si las credenciales son correctas, marcar como sesión activa (simulado)
            usuarioService.iniciarSesion(usuarioEncontrado.getIdUsuario());

            return ResponseEntity.ok(usuarioEncontrado);
        } catch (RuntimeException e) {
            // Credenciales inválidas (manejado por el service)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al intentar iniciar sesión.");
        }
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        try {
            List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
            return ResponseEntity.ok(usuarios);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al obtener todos los usuarios.");
        }
    }

    // [ ... resto de métodos del Controller se mantiene igual ... ]
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al obtener el usuario con ID: " + id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Usuario usuario, @PathVariable Long id) {
        try {
            usuario.setIdUsuario(id);
            Usuario usuarioActualizado = usuarioService.actualizarUsuario(usuario);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al actualizar el usuario con ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Métodos específicos de Usuario
    @PutMapping("/iniciar-sesion/{id}")
    public ResponseEntity<?> iniciarSesion(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.iniciarSesion(id);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al iniciar sesión para el usuario con ID: " + id);
        }
    }

    @PutMapping("/cerrar-sesion/{id}")
    public ResponseEntity<?> cerrarSesion(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.cerrarSesion(id);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al cerrar sesión para el usuario con ID: " + id);
        }
    }
}