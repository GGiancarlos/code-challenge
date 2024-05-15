package com.ggutierrez.challenge.utils;

import com.ggutierrez.challenge.dto.AlumnoDto;
import com.ggutierrez.challenge.model.Alumno;
import org.springframework.beans.BeanUtils;

public class AlumnoMapper {

    public static AlumnoDto entityToDto(Alumno alumno) {
        AlumnoDto alumnoDto = new AlumnoDto();
        BeanUtils.copyProperties(alumno, alumnoDto);
        return alumnoDto;
    }

    public static Alumno dtoToEntity(AlumnoDto alumnoDto) {
        Alumno alumno = new Alumno();
        BeanUtils.copyProperties(alumnoDto, alumno);
        return alumno;
    }
}
