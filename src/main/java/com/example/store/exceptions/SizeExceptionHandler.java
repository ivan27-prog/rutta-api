package com.example.store.exceptions;

import com.example.store.controllers.SizeController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

// Maneja los errores únicamente del controlador de tallas.
@RestControllerAdvice(assignableTypes = SizeController.class)
public class SizeExceptionHandler {

    // Responde con 404 cuando la talla solicitada no existe.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFound(
            ResourceNotFoundException exception) {

        // Usa el mensaje de la excepción como explicación del error.
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );

        // Agrega un título breve a la respuesta.
        problem.setTitle("Talla no encontrada");

        return problem;
    }
    // Responde con 400 y los mensajes de los campos que no son válidos.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(
            MethodArgumentNotValidException exception) {

        // Guarda los mensajes definidos en las anotaciones del DTO.
        List<String> errores = new ArrayList<>();

        // Recorre los errores encontrados durante la validación.
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errores.add(error.getDefaultMessage());
        }

        // Construye la respuesta de error.
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Revisa los datos de la talla."
        );

        problem.setTitle("Datos no válidos");

        // Agrega los mensajes al JSON de respuesta.
        problem.setProperty("errores", errores);

        return problem;
    }
}
