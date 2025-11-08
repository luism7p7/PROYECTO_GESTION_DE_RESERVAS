package com.example.CampingHub.services.Impl;

import com.example.CampingHub.entities.Administrador;
import com.example.CampingHub.repositories.AdministradorRepository;
import com.example.CampingHub.services.AdministradorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorServiceImpl implements AdministradorService {

    private final AdministradorRepository administradorRepository;

    public AdministradorServiceImpl(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    @Override
    public Administrador crearAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    @Override
    public Administrador obtenerAdministradorPorId(Long id) {
        return administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado con id: " + id));
    }

    @Override
    public List<Administrador> obtenerTodosLosAdministradores() {
        return administradorRepository.findAll();
    }

    @Override
    public Administrador actualizarAdministrador(Administrador administrador) {
        // Verifica existencia antes de guardar
        obtenerAdministradorPorId(administrador.getIdUsuario());
        return administradorRepository.save(administrador);
    }

    @Override
    public void eliminarAdministrador(Long id) {
        // Verifica existencia antes de eliminar
        obtenerAdministradorPorId(id);
        administradorRepository.deleteById(id);
    }

    @Override
    public Administrador gestionarFinca(Long id) {
        Administrador a = obtenerAdministradorPorId(id);
        a.gestionarFinca();
        return a;
    }

    @Override
    public Administrador gestionarReserva(Long id) {
        Administrador a = obtenerAdministradorPorId(id);
        a.gestionarReserva();
        return a;
    }
}