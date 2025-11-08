package com.example.CampingHub.repositories;

import com.example.CampingHub.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método necesario para el login: busca un usuario que coincida con ambas credenciales
    Optional<Usuario> findByCorreoAndContraseña(String correo, String contraseña);
}