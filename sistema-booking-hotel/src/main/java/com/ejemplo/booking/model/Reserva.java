package com.ejemplo.booking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date fechaInicio;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date fechaFin;

    // Relación con Habitación
    @ManyToOne
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacion;

    // Relación con Huéspedes
    @ManyToMany
    @JoinTable(
            name = "reserva_huesped",
            joinColumns = @JoinColumn(name = "reserva_id"),
            inverseJoinColumns = @JoinColumn(name = "huesped_id")
    )
    private List<Huesped> huespedes;
    @NotNull
    private String estado;

}