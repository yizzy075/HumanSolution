package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.controller.dto.response.ResponseDTO;
import co.edu.uco.HumanSolution.crosscutting.exception.BusinessException;
import co.edu.uco.HumanSolution.crosscutting.exception.ControllerException;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<Object>> handleGenericException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseDTO.builder()
                        .success(false)
                        .message("Error inesperado en el servidor")
                        .data(null)
                        .build());
    }
}

