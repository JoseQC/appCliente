package com.reto.cliente.service;


import com.reto.cliente.controller.dto.ClienteDTO;
import com.reto.cliente.controller.dto.KpiDTO;
import com.reto.cliente.entity.Cliente;
import com.reto.cliente.repositorio.ClienteRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;



    @Test
    public void obtenerClientes() {

        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNombre("Jose");
        cliente.setEdad(30);
        cliente.setApellido("Quispe");
        cliente.setFechaNacimiento(LocalDate.now());

        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente);

        when(clienteRepository.findAll()).thenReturn(clientes);

        List<ClienteDTO> clientesDTO = clienteService.obtenerClientes();

        assertThat(clientesDTO.size()).isEqualTo(1);

    }

    @Test
   public void guardarCliente() {

        Cliente clienteRequest = new Cliente();
        clienteRequest.setId(1);
        clienteRequest.setNombre("Jose");
        clienteRequest.setEdad(30);
        clienteRequest.setApellido("Quispe");
        clienteRequest.setFechaNacimiento(LocalDate.now());

        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteRequest);

        Cliente cliente = clienteService.guardarCliente(clienteRequest);

        assertThat(cliente.getNombre()).isEqualTo(clienteRequest.getNombre());
    }

    @Test
    public void obtenerKpiClientes() {

        Cliente clienteRequest = new Cliente();
        clienteRequest.setId(1);
        clienteRequest.setNombre("Jose");
        clienteRequest.setEdad(30);
        clienteRequest.setApellido("Quispe");
        clienteRequest.setFechaNacimiento(LocalDate.now());

        Cliente clienteRequestUno = new Cliente();
        clienteRequestUno.setId(1);
        clienteRequestUno.setNombre("Jose");
        clienteRequestUno.setEdad(30);
        clienteRequestUno.setApellido("Quispe");
        clienteRequestUno.setFechaNacimiento(LocalDate.now());

        List<Cliente> clientes = new ArrayList<>();
        clientes.add(clienteRequest);
        clientes.add(clienteRequestUno);

        when(clienteRepository.findAll()).thenReturn(clientes);



        KpiDTO kpiDTO = clienteService.obtenerKpiClientes();

        assertThat(kpiDTO.getPromedioEdad()).isEqualTo(30);

    }
}