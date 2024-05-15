package com.ggutierrez.challenge.repository;

import com.ggutierrez.challenge.model.Alumno;
import com.ggutierrez.challenge.exception.IdDuplicadoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class AlumnoRepository {

    private final List<Alumno> alumnos = new ArrayList<>();

    public Mono<Void> saveAlumno(Alumno alumno) {
        if (alumnos.stream().anyMatch(a -> a.getId().equals(alumno.getId()))) {
            List<String> errors = List.of("El alumno con ID " + alumno.getId() + " ya existe");
            return Mono.error(new IdDuplicadoException("No se pudo realizar la grabación", errors));
        } else {
            alumnos.add(alumno);
            log.info("Alumno creado: {}", alumno);
            return Mono.empty();
        }
    }

    public Flux<Alumno> findAll() {
        return Flux.fromIterable(alumnos);
    }
}
