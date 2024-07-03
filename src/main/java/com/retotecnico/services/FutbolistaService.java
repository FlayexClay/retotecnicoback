package com.retotecnico.services;


import com.retotecnico.models.Futbolista;
import com.retotecnico.repositories.FutbolistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FutbolistaService {

    @Autowired
    private FutbolistaRepository ifutbolistas;

    public List<Futbolista> getAllFutbolistas(){
        return ifutbolistas.findAll();
    }

    public Optional<Futbolista> getFutbolistaById(Long id){
        return ifutbolistas.findById(id);
    }
    public Futbolista saveFutbolista(Futbolista futbolistas){
        return ifutbolistas.save(futbolistas);
    }

    public void deleteFutbolista(Long id){
        ifutbolistas.deleteById(id);
    }

}
