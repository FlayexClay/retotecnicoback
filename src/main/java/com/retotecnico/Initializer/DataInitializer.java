package com.retotecnico.Initializer;

import com.retotecnico.models.Posicion;
import com.retotecnico.repositories.PosicionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private PosicionRepository posicionRepository;

    @Override
    public void run(String... args) throws Exception {
        if (posicionRepository.count() == 0) {
            List<Posicion> posiciones = Arrays.asList(
                    new Posicion("Arquero"),
                    new Posicion("Defensa"),
                    new Posicion("Mediocampista"),
                    new Posicion("Delantero")
            );
            posicionRepository.saveAll(posiciones);
        }
    }
}