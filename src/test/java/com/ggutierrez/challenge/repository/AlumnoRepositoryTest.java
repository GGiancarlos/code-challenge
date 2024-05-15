package com.ggutierrez.challenge.repository;

import com.ggutierrez.challenge.exception.IdDuplicadoException;
import com.ggutierrez.challenge.model.Alumno;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class AlumnoRepositoryTest {

    @Test
    public void testSaveAlumno() {
        AlumnoRepository repository = new AlumnoRepository();
        Alumno alumno = new Alumno(1L, "Juan", "Pérez", "1", 20);

        Mono<Void> saveMono = repository.saveAlumno(alumno);

        StepVerifier.create(saveMono)
            .expectComplete()
            .verify();

        StepVerifier.create(repository.findAll())
            .expectNext(alumno)
            .verifyComplete();
    }

    @Test
    public void testSaveAlumnoExistente() {
        AlumnoRepository repository = new AlumnoRepository();
        Alumno alumno = new Alumno(1L, "Juan", "Pérez", "1", 20);
        repository.saveAlumno(alumno).block();

        Mono<Void> saveMono = repository.saveAlumno(alumno);

        StepVerifier.create(saveMono)
            .expectError(IdDuplicadoException.class)
            .verify();
    }

    @Test
    public void testFindAll() {
        AlumnoRepository repository = new AlumnoRepository();
        Alumno alumno1 = new Alumno(1L, "Juan", "Pérez", "1", 20);
        Alumno alumno2 = new Alumno(2L, "María", "Gómez", "0", 22);
        repository.saveAlumno(alumno1).block();
        repository.saveAlumno(alumno2).block();

        Flux<Alumno> alumnosFlux = repository.findAll();

        StepVerifier.create(alumnosFlux)
            .expectNext(alumno1, alumno2)
            .verifyComplete();
    }


}