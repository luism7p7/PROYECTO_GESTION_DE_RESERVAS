package com.example.CampingHub.services;

import com.example.CampingHub.entities.Cliente;
import java.util.List;

public interface ClienteService {
    Cliente crearCliente(Cliente cliente);
    Cliente obtenerClientePorId(Long id);
    List<Cliente> obtenerTodosLosClientes();
    Cliente actualizarCliente(Cliente cliente);
    void eliminarCliente(Long id);

    Cliente realizarReserva(Long id);
}