package com.ejemplo.booking.controller;

import com.ejemplo.booking.model.Reserva;
import com.ejemplo.booking.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping("")
    public ResponseEntity<Reserva> crearReserva(@RequestBody Reserva reserva) {
        return reservaService.crearReserva(reserva);
    }
    @GetMapping("")
    public List<Reserva> getAll(){return this.reservaService.obtenerReservas();}
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtenerDetallesReserva(@PathVariable Integer id) {
        return reservaService.obtenerReservaPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizarReserva(@PathVariable Integer id, @RequestBody Reserva reserva) {
        return reservaService.actualizarReserva(id, reserva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity cancelarReserva(@PathVariable Integer id) {
        return reservaService.cancelarReserva(id);
    }
}