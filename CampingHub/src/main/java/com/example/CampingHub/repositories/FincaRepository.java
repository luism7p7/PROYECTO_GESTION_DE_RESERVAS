package com.example.CampingHub.repositories;

import com.example.CampingHub.entities.Finca;
import com.example.CampingHub.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FincaRepository extends JpaRepository<Finca,Long> {

}
