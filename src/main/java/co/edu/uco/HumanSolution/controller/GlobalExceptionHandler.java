package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.controller.dto.response.ResponseDTO;
import co.edu.uco.HumanSolution.crosscutting.exception.BusinessException;
import co.edu.uco.HumanSolution.crosscutting.exception.ControllerException;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<ResponseDTO<Object>> handleControllerException(ControllerException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseDTO.builder()
                        .success(false)
                        .message(exception.getUserMessage())
                        .data(null)
                        .build());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseDTO<Object>> handleBusinessException(BusinessException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseDTO.builder()
                        .success(false)
                        .message(exception.getUserMessage())
                        .data(null)
                        .build());
    }

    @ExceptionHandler(HumanSolutionException.class)
    public ResponseEntity<ResponseDTO<Object>> handleHumanSolutionException(HumanSolutionException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseDTO.builder()
                        .success(false)
                        .message(exception.getUserMessage())
                        .data(null)
                        .build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDTO<Object>> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseDTO.builder()
                        .success(false)
                        .message(exception.getMessage())
                        .data(null)
                        .build());
    }

<<<<<<< HEAD
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ResponseDTO<Object>> handleNoHandlerFoundException(NoHandlerFoundException exception) {
        String requestURL = exception.getRequestURL();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseDTO.builder()
                        .success(false)
                        .message("Endpoint no encontrado: " + requestURL)
                        .data(null)
                        .build());
    }

=======
>>>>>>> parent of 7105c49 (errores)
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ResponseDTO<Object>> handleNoResourceFoundException(NoResourceFoundException exception) {
        String resourcePath = exception.getResourcePath();
        // Ignorar errores de recursos estáticos como favicon.ico o /error
        if (resourcePath != null && (resourcePath.equals("/favicon.ico") || resourcePath.equals("/error"))) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // Si es una ruta de API, es un endpoint no encontrado
        if (resourcePath != null && resourcePath.startsWith("/api/")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseDTO.builder()
                            .success(false)
                            .message("Endpoint no encontrado: " + resourcePath + ". Verifique que el controlador esté registrado correctamente.")
                            .data(null)
                            .build());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseDTO.builder()
                        .success(false)
                        .message("Recurso no encontrado: " + resourcePath)
                        .data(null)
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<Object>> handleGenericException(Exception exception) {
        // Log del error completo para debugging
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseDTO.builder()
                        .success(false)
                        .message("Error inesperado en el servidor: " + exception.getMessage())
                        .data(null)
                        .build());
    }
}

