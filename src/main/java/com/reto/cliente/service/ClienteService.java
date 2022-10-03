package com.reto.cliente.service;

import com.reto.cliente.controller.dto.ClienteDTO;
import com.reto.cliente.controller.dto.KpiDTO;
import com.reto.cliente.entity.Cliente;

import java.util.List;

public interface ClienteService {

    public List<ClienteDTO> obtenerClientes();

    public Cliente guardarCliente(Cliente cliente);

    KpiDTO obtenerKpiClientes();
}
