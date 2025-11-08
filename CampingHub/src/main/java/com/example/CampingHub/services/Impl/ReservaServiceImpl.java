package com.example.CampingHub.services.Impl;

import com.example.CampingHub.entities.Finca;
import com.example.CampingHub.entities.Reserva;
import com.example.CampingHub.entities.Usuario;
import com.example.CampingHub.repositories.FincaRepository;
import com.example.CampingHub.repositories.ReservaRepository;
import com.example.CampingHub.repositories.UsuarioRepository;
import com.example.CampingHub.services.ReservaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final FincaRepository fincaRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepository, UsuarioRepository usuarioRepository, FincaRepository fincaRepository) {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.fincaRepository = fincaRepository;
    }

    @Override
    public Reserva crearReserva(Reserva reserva) {
        // Lógica de Negocio: Validar que el usuario y la finca existen
        if (reserva.getUsuario() == null || reserva.getUsuario().getIdUsuario() == null) {
            throw new RuntimeException("El ID de Usuario es obligatorio para crear una reserva.");
        }
        if (reserva.getFinca() == null || reserva.getFinca().getIdFinca() == null) {
            throw new RuntimeException("El ID de Finca es obligatorio para crear una reserva.");
        }

        Usuario usuario = usuarioRepository.findById(reserva.getUsuario().getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + reserva.getUsuario().getIdUsuario()));
        Finca finca = fincaRepository.findById(reserva.getFinca().getIdFinca())
                .orElseThrow(() -> new RuntimeException("Finca no encontrada con ID: " + reserva.getFinca().getIdFinca()));

        reserva.setUsuario(usuario);
        reserva.setFinca(finca);
        reserva.setEstado("PENDIENTE");

        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva obtenerReservaPorId(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));
    }

    @Override
    public List<Reserva> obtenerTodasLasReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public Reserva actualizarReserva(Reserva reserva) {
        obtenerReservaPorId(reserva.getIdReserva());
        return reservaRepository.save(reserva);
    }

    @Override
    public void eliminarReserva(Long id) {
        obtenerReservaPorId(id);
        reservaRepository.deleteById(id);
    }

    @Override
    public Reserva confirmarReserva(Long id) {
        Reserva reserva = obtenerReservaPorId(id);
        if ("CANCELADA".equals(reserva.getEstado())) {
            throw new RuntimeException("No se puede confirmar una reserva cancelada.");
        }
        reserva.setEstado("CONFIRMADA");
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva cancelarReserva(Long id) {
        Reserva reserva = obtenerReservaPorId(id);
        reserva.setEstado("CANCELADA");
        // Lógica: Si estaba confirmada, se podría revertir la disponibilidad de la finca
        return reservaRepository.save(reserva);
    }
}