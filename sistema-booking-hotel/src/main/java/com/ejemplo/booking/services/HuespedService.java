package com.ejemplo.booking.services;

import com.ejemplo.booking.model.Huesped;
import com.ejemplo.booking.model.ReservaDTO;
import com.ejemplo.booking.repositories.HuespedRepository;
import com.ejemplo.booking.repositories.ReservaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.*;

@Service
public class HuespedService {

    @Autowired
    private HuespedRepository huespedRepository;
    @Autowired
    private ReservaRepository reservaRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public HuespedService(HuespedRepository huespedRepository,ReservaRepository reservaRepository){
        this.huespedRepository = huespedRepository;
        this.reservaRepository = reservaRepository;
    }
    public ResponseEntity registrarHuesped(Huesped huesped) {
        try {
            huespedRepository.save(huesped);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    public List<Huesped> listarHuespedes() {
        return huespedRepository.findAll();
    }
    public ResponseEntity obtenerHuespedPorId(Integer id) {
        try {
            Huesped huesped = huespedRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Huesped no encontrado"));
            return ResponseEntity.ok(huesped);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    public ResponseEntity actualizarHuesped(Integer id, Huesped huesped) {
        try {
            Huesped huespedActual = huespedRepository.findById(id).get();
            huespedActual.setNombre(huesped.getNombre());
            huespedActual.setEmail(huesped.getEmail());
            huespedActual.setTelefono(huesped.getTelefono());
            huespedRepository.save(huespedActual);
            return ResponseEntity.status(OK).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    public List<ReservaDTO> getReservaHuesped(Integer huespedId) {
        List<ReservaDTO> listaReservas = new ArrayList<>();
        List<Integer> huespedReservas = huespedRepository.getReservaHuesped(huespedId);
        for(int x = 0; x < huespedReservas.size(); x++) {
            listaReservas.add(modelMapper.map(reservaRepository.findById(huespedReservas.get(x)).get(), ReservaDTO.class));
        }
        return listaReservas;
    }
    public ResponseEntity borrarHuesped(Integer id) {
        try {
            huespedRepository.deleteById(id);
            return ResponseEntity.status(OK).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}