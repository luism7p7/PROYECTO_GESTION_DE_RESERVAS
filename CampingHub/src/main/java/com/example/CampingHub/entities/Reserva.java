package com.example.CampingHub.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;

    // Relación ManyToOne con Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonBackReference("usuario-reservas")
    private Usuario usuario;

    // Relación ManyToOne con Finca
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_finca", nullable = false)
    @JsonBackReference("finca-reservas")
    private Finca finca;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFin;

    @Column(nullable = false, length = 20)
    private String estado = "PENDIENTE";

    // Constructores, Getters y Setters
    public Reserva() { }
    public Reserva(Usuario usuario, Finca finca, LocalDate fechaInicio, LocalDate fechaFin) {
        this.usuario = usuario;
        this.finca = finca;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Long getIdReserva() { return idReserva; }
    public void setIdReserva(Long idReserva) { this.idReserva = idReserva; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Finca getFinca() { return finca; }
    public void setFinca(Finca finca) { this.finca = finca; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva=" + idReserva +
                ", usuarioId=" + (usuario != null ? usuario.getIdUsuario() : "null") +
                ", fincaId=" + (finca != null ? finca.getIdFinca() : "null") +
                ", estado='" + estado + '\'' +
                '}';
    }
}