package com.reto.cliente.controller;


import com.reto.cliente.controller.dto.ClienteDTO;
import com.reto.cliente.controller.dto.KpiDTO;
import com.reto.cliente.entity.Cliente;
import com.reto.cliente.service.ClienteService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Test
    public void obtenerClientesTest() throws Exception {

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombre("Jose");
        clienteDTO.setApellido("Quispe");
        clienteDTO.setEdad(30);
        clienteDTO.setFechaNacimiento(LocalDate.now());
        clienteDTO.setFechaProbableMuerte(LocalDate.now());

        ClienteDTO clienteDTO1 = new ClienteDTO();
        clienteDTO.setNombre("Luis");
        clienteDTO.setApellido("Quispe");
        clienteDTO.setEdad(30);
        clienteDTO.setFechaNacimiento(LocalDate.now());
        clienteDTO.setFechaProbableMuerte(LocalDate.now());

        clienteDTO1.setNombre("Luis");
        List<ClienteDTO> listaClientes = new ArrayList<>();
        listaClientes.add(clienteDTO);
        listaClientes.add(clienteDTO1);

        given(clienteService.obtenerClientes()).willReturn(listaClientes);

        mockMvc.perform(get("/api/v1/listclientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", Matchers.equalTo("Luis")))
        ;
    }

    @Test
    public void crearClienteTest() throws Exception {

        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNombre("Jose");
        cliente.setEdad(30);
        cliente.setApellido("Quispe");
        cliente.setFechaNacimiento(LocalDate.now());

        when(clienteService.guardarCliente(any())).thenReturn(cliente);

        mockMvc.perform(post("/api/v1/creaCliente", cliente)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nombre\": \"Jose\",\"apellido\": \"Cordova\",\"edad\": 20,\"fechaNacimiento\": \"2022-10-02\"}"))
                .andExpect(status().isOk());

    }

    @Test
    public void obtenerKPITest() throws Exception {
        KpiDTO kpiDTO = new KpiDTO();
        kpiDTO.setPromedioEdad(3.2);
        kpiDTO.setDesviacionEstandar(3.223);

        given(clienteService.obtenerKpiClientes()).willReturn(kpiDTO);

        mockMvc.perform(get("/api/v1/kpideclientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.promedioEdad", Matchers.equalTo(3.2)))
                .andExpect(jsonPath("$.desviacionEstandar", Matchers.equalTo(3.223)))
        ;

    }

}