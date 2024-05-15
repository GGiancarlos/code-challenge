package com.ggutierrez.challenge.controller;

import com.ggutierrez.challenge.dto.AlumnoDto;
import com.ggutierrez.challenge.model.Alumno;
import com.ggutierrez.challenge.service.AlumnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/alumnos")
@RequiredArgsConstructor
@Validated
public class AlumnoController {

    private final AlumnoService alumnoService;

    @Operation(summary = "Listar todos los alumnos", description = "Lista todos los alumnos en el sistema")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    @GetMapping
    public Flux<AlumnoDto> getAlumnos() {
        return alumnoService.findAll();
    }

    @Operation(summary = "Crear un alumno", description = "Crea un nuevo alumno en el sistema")
    @ApiResponse(responseCode = "201", description = "Alumno creado correctamente")
    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> createAlumno(@Valid @RequestBody AlumnoDto alumnoDto) {
        return alumnoService.createAlumno(alumnoDto);
    }

    @Operation(summary = "Listar alumnos activos", description = "Lista todos los alumnos activos en el sistema")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    @GetMapping("/activos")
    public Flux<AlumnoDto> getAlumnosActivos(@RequestParam String estado) {
        return alumnoService.getAlumnosActivos(estado);
    }
}
