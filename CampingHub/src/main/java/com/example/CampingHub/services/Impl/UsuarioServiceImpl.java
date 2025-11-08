package com.example.CampingHub.services.Impl;

import com.example.CampingHub.entities.Usuario;
import com.example.CampingHub.repositories.UsuarioRepository;
import com.example.CampingHub.services.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        // Verifica existencia antes de guardar
        obtenerUsuarioPorId(usuario.getIdUsuario());
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Long id) {
        // Verifica existencia antes de eliminar
        obtenerUsuarioPorId(id);
        usuarioRepository.deleteById(id);
    }


    @Override
    public Usuario login(String correo, String contraseña) {

        return usuarioRepository.findByCorreoAndContraseña(correo, contraseña)
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas."));
    }

    @Override
    public Usuario iniciarSesion(Long id) {
        Usuario u = obtenerUsuarioPorId(id);
        u.iniciarSccion();
        return u;
    }

    @Override
    public Usuario cerrarSesion(Long id) {
        Usuario u = obtenerUsuarioPorId(id);
        u.cerrarSeccion();
        return u;
    }
}