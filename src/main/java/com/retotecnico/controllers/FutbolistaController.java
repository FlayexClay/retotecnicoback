package com.retotecnico.controllers;

import com.retotecnico.models.Futbolista;
import com.retotecnico.models.Posicion;
import com.retotecnico.repositories.FutbolistaRepository;
import com.retotecnico.repositories.PosicionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/futbolistas")
public class FutbolistaController {

    @Autowired
    private FutbolistaRepository futbolistaRepository;

    @Autowired
    private PosicionRepository posicionRepository;

    @GetMapping
    public List<Futbolista> obtenerTodosLosFutbolistas() {
        return futbolistaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Futbolista> obtenerFutbolistaPorId(@PathVariable Long id) {
        Optional<Futbolista> futbolista = futbolistaRepository.findById(id);
        return futbolista.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Futbolista> crearFutbolista(@RequestBody Futbolista futbolista) {
        if (futbolista.getPosicion() != null && futbolista.getPosicion().getId() != null) {
            Optional<Posicion> optionalPosicion = posicionRepository.findById(futbolista.getPosicion().getId());
            if (!optionalPosicion.isPresent()) {
                return ResponseEntity.badRequest().build();
            }
            futbolista.setPosicion(optionalPosicion.get());
        }
        Futbolista nuevoFutbolista = futbolistaRepository.save(futbolista);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoFutbolista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Futbolista> actualizarFutbolista(@PathVariable Long id, @RequestBody Futbolista futbolistaActualizado) {
        Optional<Futbolista> optionalFutbolista = futbolistaRepository.findById(id);
        if (optionalFutbolista.isPresent()) {
            if (futbolistaActualizado.getPosicion() != null && futbolistaActualizado.getPosicion().getId() != null) {
                Optional<Posicion> optionalPosicion = posicionRepository.findById(futbolistaActualizado.getPosicion().getId());
                if (!optionalPosicion.isPresent()) {
                    return ResponseEntity.badRequest().build();
                }
                futbolistaActualizado.setPosicion(optionalPosicion.get());
            }
            futbolistaActualizado.setId(id);
            futbolistaRepository.save(futbolistaActualizado);
            return ResponseEntity.ok(futbolistaActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFutbolista(@PathVariable Long id) {
        Optional<Futbolista> optionalFutbolista = futbolistaRepository.findById(id);
        if (optionalFutbolista.isPresent()) {
            futbolistaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
