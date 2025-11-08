package com.example.CampingHub.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idUsuario;
    private String nombre;
    private String correo;
    private String contraseña;

    public Usuario() {
    }

    public Usuario(String correo, String nombre, Long idUsuario, String contraseña) {
        this.correo = correo;
        this.nombre = nombre;
        this.idUsuario = idUsuario;
        this.contraseña = contraseña;
    }


    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void iniciarSccion(){
        System.out.println(nombre + "ha iniciado sección");
    }

    public void cerrarSeccion(){
        System.out.println(nombre + "ha cerrado seccion");
    }


    

}
