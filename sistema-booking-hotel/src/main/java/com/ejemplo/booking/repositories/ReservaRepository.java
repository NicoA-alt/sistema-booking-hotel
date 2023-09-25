package com.ejemplo.booking.repositories;

import com.ejemplo.booking.model.Habitacion;
import com.ejemplo.booking.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    @Query("SELECT r FROM Reserva r WHERE r.habitacion = :habitacion AND r.fechaFin >= :fechaInicio AND r.fechaInicio <= :fechaFin")
    List<Reserva> findReservasByHabitacionAndFechas(
            @Param("habitacion") Habitacion habitacion,
            @Param("fechaInicio") Date fechaInicio,
            @Param("fechaFin") Date fechaFin
    );
}
