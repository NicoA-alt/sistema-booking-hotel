package com.ejemplo.booking.repositories;

import com.ejemplo.booking.model.Huesped;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HuespedRepository extends JpaRepository<Huesped, Integer> {
    @Query(value = "select reserva_id from reserva_huesped where huesped_id = :huespedId", nativeQuery = true)
    public List<Integer> getReservaHuesped(Integer huespedId);
}
