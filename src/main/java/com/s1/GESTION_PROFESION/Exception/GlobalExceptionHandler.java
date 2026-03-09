package com.s1.GESTION_PROFESION.Exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Se activa cuando un campo del DTO no pasa el @NotBlank, @NotNull, @Size, etc.
    // Recorro todos los errores de validación y los devuelvo juntos.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("errorCode", "VALIDATION_FAILED");

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        response.put("errors", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Esta es mi excepción personalizada. La lanzo cuando se rompe una regla de negocio,
    // por ejemplo: stock insuficiente, ID que no existe, credenciales inválidas, etc.
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ErrorResponse> handleBusinessRuleException(BusinessRuleException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(), "BUSINESS_RULE_VIOLATION")
        );
    }

    // Se dispara cuando JPA no encuentra una entidad en base de datos.
    // Es similar a BusinessRuleException pero esta la lanza JPA solo, no yo.
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), "ENTITY_NOT_FOUND")
        );
    }

    // Se activa cuando alguien llama a una ruta que no existe en ningún controller.
    // Ej: /api/jajajajejejejijiji → no existe entonces se activa.
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NoHandlerFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
                        "Ruta no encontrada o incompleta", "ERR_NO_HANDLER_FOUND")
        );
    }

    // Cae aquí cuando el JSON que llega en el body está mal formado o le faltan campos obligatorios
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleParsingErrors(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                        "El cuerpo de la solicitud no es válido.", "BAD_REQUEST")
        );
    }

    // Red de seguridad general. Cualquier error que no capturé arriba termina aquí.
    // Devuelvo 500 para no exponer detalles internos del servidor.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericErrors(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Error interno del servidor", "INTERNAL_SERVER_ERROR")
        );
    }
}