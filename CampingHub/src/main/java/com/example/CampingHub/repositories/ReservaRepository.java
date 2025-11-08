package com.example.CampingHub.repositories;

import com.example.CampingHub.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    // Puedes añadir métodos de búsqueda personalizados si son necesarios, por ejemplo:
    // List<Reserva> findByUsuarioIdUsuario(Long userId);
    // List<Reserva> findByFincaIdFinca(Long fincaId);
}