package com.ggutierrez.challenge.service;

import com.ggutierrez.challenge.dto.AlumnoDto;
import com.ggutierrez.challenge.repository.AlumnoRepository;
import com.ggutierrez.challenge.utils.AlumnoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;


    public Flux<AlumnoDto> findAll() {
        return alumnoRepository.findAll().map(AlumnoMapper::entityToDto);
    }

    public Mono<Void> createAlumno(AlumnoDto alumnoDto) {

        return alumnoRepository.saveAlumno(AlumnoMapper.dtoToEntity(alumnoDto));

    }

    public Flux<AlumnoDto> getAlumnosActivos(String estado) {
        return alumnoRepository.findAll()
            .filter(alumno -> alumno.getEstado().equals(estado))
            .map(AlumnoMapper::entityToDto);
    }

}
