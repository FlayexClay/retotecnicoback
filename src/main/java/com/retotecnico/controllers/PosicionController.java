package com.retotecnico.controllers;

import com.retotecnico.models.Posicion;
import com.retotecnico.repositories.PosicionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/posiciones")
public class PosicionController {

    @Autowired
    private PosicionRepository posicionRepository;

    @GetMapping("/")
    public List<Posicion> obtenerTodasLasPosiciones() {
        return posicionRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Posicion> obtenerPosicionPorId(@PathVariable Long id) {
        Optional<Posicion> posicion = posicionRepository.findById(id);
        return posicion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
