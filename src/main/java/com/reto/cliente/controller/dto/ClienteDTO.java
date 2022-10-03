package com.reto.cliente.controller.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClienteDTO {

    private Integer id;
    private String nombre;
    private String apellido;
    private Integer edad;
    private LocalDate fechaNacimiento;
    private LocalDate fechaProbableMuerte;
}
