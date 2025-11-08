package com.example.CampingHub.services;

import com.example.CampingHub.entities.Administrador;
import java.util.List;

public interface AdministradorService {
    Administrador crearAdministrador(Administrador administrador);
    Administrador obtenerAdministradorPorId(Long id);
    List<Administrador> obtenerTodosLosAdministradores();
    Administrador actualizarAdministrador(Administrador administrador);
    void eliminarAdministrador(Long id);


    Administrador gestionarFinca(Long id);
    Administrador gestionarReserva(Long id);
}