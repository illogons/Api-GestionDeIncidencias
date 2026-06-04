package com.worktrack.worktrack.shared;

import com.worktrack.worktrack.shared.ErrorResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> details = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (existing, replacement) -> existing
                ));
        log.warn("Validation failed: {}", details);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse(400, "Validation failed", LocalDateTime.now(), details)
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrity(DataIntegrityViolationException ex) {
        String rootCause = ex.getRootCause() != null ? ex.getRootCause().getMessage() : "Unknown cause";
        log.error("Data integrity violation: {}", rootCause);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErrorResponse(409, "Data integrity violation", LocalDateTime.now(), null)
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        String errorId = UUID.randomUUID().toString();
        log.error("Unexpected error [ID: {}]: {}", errorId, ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErrorResponse(500, "An unexpected error occurred", LocalDateTime.now(), Map.of("errorId", errorId))
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, Locale locale) {

        String errorId = UUID.randomUUID().toString();

        log.error("UNEXPECTED ERROR [ID: {}]: {} - Trace: ", errorId, ex.getMessage(), ex);

        Map<String, String> details = Map.of("errorId", errorId);
        return buildResponse("error.internal.server", HttpStatus.INTERNAL_SERVER_ERROR, locale, details);
    }

    private ResponseEntity<ErrorResponse> buildResponse(String messageKey, HttpStatus status, Locale locale, Map<String, String> details) {

        // Fetch the localized message using the message key and locale
        String message = messageSource.getMessage(messageKey, null, messageKey, locale);
        // Create an ErrorResponse object with the status, message, timestamp, and details
        ErrorResponse error = new ErrorResponse(status.value(), message, LocalDateTime.now(), details);
        return new ResponseEntity<>(error, status);
    }
    
}