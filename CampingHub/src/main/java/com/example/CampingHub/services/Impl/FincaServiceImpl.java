package com.example.CampingHub.services.Impl;

import com.example.CampingHub.entities.Finca;
import com.example.CampingHub.repositories.FincaRepository;
import com.example.CampingHub.services.FincaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FincaServiceImpl implements FincaService {

    private final FincaRepository fincaRepository;

    public FincaServiceImpl(FincaRepository fincaRepository) {
        this.fincaRepository = fincaRepository;
    }

    @Override
    public Finca crearFinca(Finca finca) {
        return fincaRepository.save(finca);
    }

    @Override
    public Finca obtenerFincaPorId(Long id) {
        return fincaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Finca no encontrada con id: " + id));
    }

    @Override
    public List<Finca> obtenerTodasLasFincas() {
        return fincaRepository.findAll();
    }

    @Override
    public Finca actualizarFinca(Finca finca) {
        // Verifica existencia antes de guardar
        obtenerFincaPorId(finca.getIdFinca());
        return fincaRepository.save(finca);
    }

    @Override
    public void eliminarFinca(Long id) {
        // Verifica existencia antes de eliminar
        obtenerFincaPorId(id);
        fincaRepository.deleteById(id);
    }

    @Override
    public Finca mostrarDisponibilidad(Long id) {
        Finca f = obtenerFincaPorId(id);
        f.mostrarDisponibilidad();
        return f;
    }
}