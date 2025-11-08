package com.example.CampingHub.repositories;

import com.example.CampingHub.entities.Cliente;
import com.example.CampingHub.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

}
