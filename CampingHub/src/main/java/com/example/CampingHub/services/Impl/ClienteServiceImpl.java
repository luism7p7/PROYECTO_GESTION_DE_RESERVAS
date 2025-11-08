package com.example.CampingHub.services.Impl;

import com.example.CampingHub.entities.Cliente;
import com.example.CampingHub.repositories.ClienteRepository;
import com.example.CampingHub.services.ClienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
    }

    @Override
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        // Verifica existencia antes de guardar
        obtenerClientePorId(cliente.getIdUsuario());
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminarCliente(Long id) {
        // Verifica existencia antes de eliminar
        obtenerClientePorId(id);
        clienteRepository.deleteById(id);
    }

    @Override
    public Cliente realizarReserva(Long id) {
        Cliente c = obtenerClientePorId(id);
        c.realizarReserva();
        return c;
    }
}