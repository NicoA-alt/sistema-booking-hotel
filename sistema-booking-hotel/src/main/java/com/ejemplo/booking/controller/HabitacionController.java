package com.ejemplo.booking.controller;

import com.ejemplo.booking.model.Habitacion;
import com.ejemplo.booking.model.HabitacionDTO;
import com.ejemplo.booking.model.Reserva;
import com.ejemplo.booking.services.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

    @GetMapping("")
    public List<Habitacion> listarHabitaciones() {
        return habitacionService.listarHabitaciones();
    }

    @PostMapping("")
    public ResponseEntity agregarHabitacion(@RequestBody Habitacion h){
        return  habitacionService.agregarHabitacion(h);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> obtenerDetallesHabitacion(@PathVariable Integer id) {
        return this.habitacionService.obtenerHabitacionPorId(id);
    }
    @GetMapping("/{id}/reservas")
    public ResponseEntity<List<Reserva>> obtenerReservasDeHabitacion(@PathVariable Integer id) {
        return this.habitacionService.obtenerReservasDeHabitacion(id);
    }
    @GetMapping("/disponibles")
    public List<HabitacionDTO> obtenerHabitacionesDisponibles() {
        return habitacionService.obtenerHabitacionesDisponibles();
    }

    @PutMapping("/{id}")
    public ResponseEntity actualizarHabitacion(@PathVariable Integer id, @RequestBody Habitacion habitacion) {
        return this.habitacionService.actualizarHabitacion(id,habitacion);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity borrarHabitacion(@PathVariable Integer id) {
        return habitacionService.borrarHabitacion(id);
    }
}