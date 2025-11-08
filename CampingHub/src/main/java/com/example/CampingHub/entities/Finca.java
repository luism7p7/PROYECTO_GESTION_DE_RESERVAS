package com.example.CampingHub.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Finca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFinca;
    private String nombre;
    private String ubicacion;
    private int capacidad;

    @Column(nullable = false)
    private double precioNoche = 0.0; // Añadido para la lógica de negocio

    // Relación Bidireccional: Manager de JSON
    @OneToMany(mappedBy = "finca", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("finca-reservas")
    private List<Reserva> reservas;

    // Constructores, Getters y Setters
    public Finca() { }
    public Finca(Long idFinca, int capacidad, String ubicacion, String nombre, double precioNoche) {
        this.idFinca = idFinca;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.nombre = nombre;
        this.precioNoche = precioNoche;
    }

    public Long getIdFinca() { return idFinca; }
    public void setIdFinca(Long idFinca) { this.idFinca = idFinca; }
    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPrecioNoche() { return precioNoche; }
    public void setPrecioNoche(double precioNoche) { this.precioNoche = precioNoche; }
    public List<Reserva> getReservas() { return reservas; }
    public void setReservas(List<Reserva> reservas) { this.reservas = reservas; }

    public void mostrarDisponibilidad(){
        System.out.println("la finca" + nombre + "tiene capacidad de " + capacidad + "personas");
    }
}