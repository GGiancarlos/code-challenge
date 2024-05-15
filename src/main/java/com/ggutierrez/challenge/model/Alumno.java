package com.ggutierrez.challenge.model;

import lombok.*;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Alumno {

    private Long id;
    private String nombre;
    private String apellido;
    private String estado;
    private Integer edad;
}
