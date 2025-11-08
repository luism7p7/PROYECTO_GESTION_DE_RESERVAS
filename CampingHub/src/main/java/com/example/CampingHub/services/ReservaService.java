package com.example.CampingHub.services;

import com.example.CampingHub.entities.Reserva;
import java.util.List;

public interface ReservaService {
    Reserva crearReserva(Reserva reserva);
    Reserva obtenerReservaPorId(Long id);
    List<Reserva> obtenerTodasLasReservas();
    Reserva actualizarReserva(Reserva reserva);
    void eliminarReserva(Long id);

    Reserva confirmarReserva(Long id);
    Reserva cancelarReserva(Long id);
}