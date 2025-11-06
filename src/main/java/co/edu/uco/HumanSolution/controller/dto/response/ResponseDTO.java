package co.edu.uco.HumanSolution.controller.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO<T> {
    
    private boolean success;
    private String message;
    private T data;
}

