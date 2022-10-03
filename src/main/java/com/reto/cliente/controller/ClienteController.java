package com.reto.cliente.controller;

import com.reto.cliente.controller.dto.ClienteDTO;
import com.reto.cliente.controller.dto.KpiDTO;
import com.reto.cliente.entity.Cliente;
import com.reto.cliente.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@ApiOperation("Clientes API")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping(value ="/listclientes")
    public List<ClienteDTO> listarClientes() {
        return clienteService.obtenerClientes();
    }

    @PostMapping("/creaCliente")
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return  clienteService.guardarCliente(cliente);
    }

    @GetMapping("/kpideclientes")
    public KpiDTO kpiClientes() {
        return clienteService.obtenerKpiClientes();
    }
}
