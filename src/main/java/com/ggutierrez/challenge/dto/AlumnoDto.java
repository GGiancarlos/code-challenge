package com.ggutierrez.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoDto {

    @NotNull(message = "El ID no puede ser nulo")
    @Min(value = 0, message = "El Id debe ser un número positivo")
    private Long id;

    @NotBlank(message = "El nombre no puede ser nulo ni estar en blanco")
    @Pattern(regexp = "^(\\p{L}+\\s?)+$", message = "El nombre solo puede contener letras")
    private String nombre;

    @NotBlank(message = "El apellido no puede ser nulo ni estar en blanco")
    @Pattern(regexp = "^(\\p{L}+\\s?)+$", message = "El apellido solo puede contener letras")
    private String apellido;

    @NotBlank(message = "El estado no puede ser nulo ni estar en blanco")
    @Pattern(regexp = "[01]", message = "El estado debe ser '1'=Activo o '0'=Inactivo")
    private String estado;

    @NotNull(message = "La edad no puede estar nula")
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "La edad debe ser un número entero")
    @Positive(message = "La edad debe ser un número positivo")
    private Integer edad;
}
