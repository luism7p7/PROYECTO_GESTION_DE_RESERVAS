package com.example.CampingHub.services;

import com.example.CampingHub.entities.Notificacion;
import java.util.List;

public interface NotificacionService {
    Notificacion crearNotificacion(Notificacion notificacion);
    Notificacion obtenerNotificacionPorId(Long id);
    List<Notificacion> obtenerTodasLasNotificaciones();
    Notificacion actualizarNotificacion(Notificacion notificacion);
    void eliminarNotificacion(Long id);

    Notificacion enviarNotificacion(Long id);
}