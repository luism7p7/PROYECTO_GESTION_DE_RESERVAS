package com.example.CampingHub.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Cliente extends Usuario {

    // Relación Bidireccional: Manager de JSON
    // Mapeado por el campo 'usuario' en la entidad Reserva
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("usuario-reservas")
    private List<Reserva> reservas;

    public void realizarReserva(){
        System.out.println("el cliente" + getNombre() + "ha realizado una reserva" );
    }

    // Getters y Setters para reservas
    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}