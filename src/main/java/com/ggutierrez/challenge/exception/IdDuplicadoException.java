package com.ggutierrez.challenge.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.CONFLICT)
@Getter
public class IdDuplicadoException extends RuntimeException {

    private final List<String> errores;

    public IdDuplicadoException(String message, List<String> errores) {
        super(message);
        this.errores = errores;
    }
}
