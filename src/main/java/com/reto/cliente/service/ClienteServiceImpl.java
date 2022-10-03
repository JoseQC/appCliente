package com.reto.cliente.service;

import com.reto.cliente.controller.dto.ClienteDTO;
import com.reto.cliente.controller.dto.KpiDTO;
import com.reto.cliente.entity.Cliente;
import com.reto.cliente.repositorio.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {
    private ModelMapper modelMapper = new ModelMapper();
    static final int PROBAILIDAD_VIDA = 77;
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<ClienteDTO> obtenerClientes() {

        List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();
        return clientes
                .stream()
                .map(cliente -> {
                            ClienteDTO clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
                            clienteDTO.setEdad((Period.between(clienteDTO.getFechaNacimiento(), LocalDate.now())).getYears());
                            clienteDTO.setFechaProbableMuerte(LocalDate.now().plusYears(PROBAILIDAD_VIDA - Period.between(clienteDTO.getFechaNacimiento(), LocalDate.now()).getYears()));
                            return clienteDTO;
                        }
                )
                .collect(Collectors.toList());
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public KpiDTO obtenerKpiClientes() {

        List<Cliente> clientes = new ArrayList<>();
        KpiDTO kpiDTO = new KpiDTO();

        clienteRepository
                .findAll()
                .iterator()
                .forEachRemaining(clientes::add);

        IntSummaryStatistics datosEstadisticos = clientes
                .stream()
                .mapToInt(Cliente::getEdad)
                .summaryStatistics();

        double sumaDesviacion = clientes
                .stream()
                .map(c -> Math.pow((c.getEdad() - datosEstadisticos.getAverage()), 2))
                .mapToDouble(s -> s).sum();

        double divisionDesviacion = (sumaDesviacion / clientes.size());

        kpiDTO.setPromedioEdad(datosEstadisticos.getAverage());
        kpiDTO.setDesviacionEstandar(Math.sqrt(divisionDesviacion));

        return kpiDTO;
    }
}
