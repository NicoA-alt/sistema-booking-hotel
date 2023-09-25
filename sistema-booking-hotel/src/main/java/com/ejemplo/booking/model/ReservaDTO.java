package com.ejemplo.booking.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {
    private Integer id;
    private Date fechaInicio;
    private Date fechaFin;
    private Habitacion habitacion;
    private String estado;
}
