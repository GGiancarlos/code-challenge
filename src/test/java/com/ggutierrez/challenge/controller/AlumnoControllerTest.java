package com.ggutierrez.challenge.controller;

import com.ggutierrez.challenge.dto.AlumnoDto;
import com.ggutierrez.challenge.exception.ExceptionResponse;
import com.ggutierrez.challenge.exception.IdDuplicadoException;
import com.ggutierrez.challenge.service.AlumnoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = AlumnoController.class)
public class AlumnoControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private AlumnoService alumnoService;

    @Test
    @DisplayName("Test crear alumno")
    public void testCreateAlumno() {
        AlumnoDto alumnoDto = new AlumnoDto(1L, "Gian", "Morales", "1", 20);

        when(alumnoService.createAlumno(alumnoDto)).thenReturn(Mono.empty());

        webTestClient.post()
            .uri("/api/alumnos")
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(alumnoDto), AlumnoDto.class)
            .exchange()
            .expectStatus().isCreated();
    }

    @Test
    @DisplayName("Test crear alumno con id duplicado")
    public void testCreateAlumnoIdDuplicado() {
        AlumnoDto alumnoExistente = new AlumnoDto(1L, "NombreExistente", "ApellidoExistente", "1", 25);

        when(alumnoService.createAlumno(alumnoExistente))
            .thenReturn(Mono.error(new IdDuplicadoException("No se pudo realizar la grabación", null)));

        webTestClient.post()
            .uri("/api/alumnos")
            .bodyValue(alumnoExistente)
            .exchange()
            .expectStatus().is4xxClientError()
            .expectBody(ExceptionResponse.class)
            .value(response -> assertEquals("No se pudo realizar la grabación", response.getMessage()));
    }

    @Test
    @DisplayName("Test crear alumno con datos inconsistentes")
    public void testCreateAlumnoBadRequestException() {
        AlumnoDto alumnoDto = new AlumnoDto(-1L, "Gian", "", "13", -20);

        webTestClient.post()
            .uri("/api/alumnos")
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(alumnoDto), AlumnoDto.class)
            .exchange()
            .expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("Test listar alumnos activos")
    public void testGetAlumnosActivos() {
        AlumnoDto alumno1 = new AlumnoDto(1L, "Ariana", "Larico", "1", 25);
        AlumnoDto alumno2 = new AlumnoDto(2L, "Belen", "Larico", "1", 18);


        when(alumnoService.getAlumnosActivos("1")).thenReturn(Flux.just(alumno1, alumno2));

        Flux<AlumnoDto> responseBody = webTestClient.get()
            .uri("/api/alumnos/activos?estado=1")
            .exchange()
            .expectStatus().isOk()
            .returnResult(AlumnoDto.class)
            .getResponseBody();

        StepVerifier.create(responseBody)
            .expectSubscription()
            .expectNext(new AlumnoDto(1L, "Ariana", "Larico", "1", 25))
            .expectNext(new AlumnoDto(2L, "Belen", "Larico", "1", 18))
            .verifyComplete();
    }


}