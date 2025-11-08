package com.example.CampingHub.services;

import com.example.CampingHub.entities.Finca;
import java.util.List;

public interface FincaService {
    Finca crearFinca(Finca finca);
    Finca obtenerFincaPorId(Long id);
    List<Finca> obtenerTodasLasFincas();
    Finca actualizarFinca(Finca finca);
    void eliminarFinca(Long id);


    Finca mostrarDisponibilidad(Long id);
}