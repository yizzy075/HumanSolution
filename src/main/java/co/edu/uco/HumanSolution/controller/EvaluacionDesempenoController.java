package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.business.facade.EvaluacionDesempenoFacade;
import co.edu.uco.HumanSolution.dto.EvaluacionDesempenoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/evaluaciones-desempeno")
@CrossOrigin(origins = "http://localhost:4200")
public class EvaluacionDesempenoController {

    private static final String KEY_MENSAJE = "mensaje";
    private static final String KEY_ERROR = "error";
    private static final String MSG_REGISTRO_EXITOSO = "Evaluación de desempeño registrada exitosamente";
    private static final String MSG_ACTUALIZACION_EXITOSA = "Evaluación de desempeño actualizada exitosamente";
    private static final String MSG_ELIMINACION_EXITOSA = "Evaluación de desempeño eliminada exitosamente";

    private final EvaluacionDesempenoFacade evaluacionDesempenoFacade;

    public EvaluacionDesempenoController(EvaluacionDesempenoFacade evaluacionDesempenoFacade) {
        this.evaluacionDesempenoFacade = evaluacionDesempenoFacade;
    }

    /**
     * Endpoint para registrar una nueva evaluación de desempeño
     * POST /api/v1/evaluaciones-desempeno
     *
     * Implementa las reglas de negocio:
     * - ED-01: Datos obligatorios
     * - ED-02: No duplicados (misma fecha y usuario)
     * - ED-03: Fecha no futura
     * - ED-04: Contrato vigente
     * - ED-05: Calificación en rango 1-10, observación 5-500 caracteres
     */
    @PostMapping
    public ResponseEntity<Map<String, String>> create(@RequestBody EvaluacionDesempenoDTO evaluacionDTO) {
        try {
            System.out.println("======= DEBUG CONTROLLER - EVALUACION DESEMPENO =======");
            System.out.println("DTO Recibido: " + evaluacionDTO);
            if (evaluacionDTO.getUsuario() != null) {
                System.out.println("Usuario ID: " + evaluacionDTO.getUsuario().getId());
            }
            System.out.println("Fecha: " + evaluacionDTO.getFecha());
            System.out.println("Calificación: " + evaluacionDTO.getCalificacion());
            System.out.println("Observación: " + evaluacionDTO.getObservacion());
            System.out.println("====================================================");

            evaluacionDesempenoFacade.create(evaluacionDTO);

            Map<String, String> response = new HashMap<>();
            response.put(KEY_MENSAJE, MSG_REGISTRO_EXITOSO);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            System.err.println("ERROR EN CONTROLLER: " + e.getMessage());
            e.printStackTrace();

            Map<String, String> error = new HashMap<>();
            error.put(KEY_ERROR, e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    /**
     * Endpoint para listar todas las evaluaciones de desempeño
     * GET /api/v1/evaluaciones-desempeno
     */
    @GetMapping
    public ResponseEntity<List<EvaluacionDesempenoDTO>> list() {
        try {
            return ResponseEntity.ok(evaluacionDesempenoFacade.list());
        } catch (Exception e) {
            System.err.println("ERROR AL LISTAR EVALUACIONES: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Endpoint para buscar evaluaciones de desempeño por usuario
     * GET /api/v1/evaluaciones-desempeno/usuario/{idUsuario}
     */
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<EvaluacionDesempenoDTO>> findByUsuario(@PathVariable String idUsuario) {
        try {
            UUID uuid = UUID.fromString(idUsuario);
            return ResponseEntity.ok(evaluacionDesempenoFacade.findByUsuario(uuid));
        } catch (IllegalArgumentException e) {
            System.err.println("ID DE USUARIO INVÁLIDO: " + idUsuario);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            System.err.println("ERROR AL BUSCAR EVALUACIONES POR USUARIO: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Endpoint para buscar una evaluación de desempeño por ID
     * GET /api/v1/evaluaciones-desempeno/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionDesempenoDTO> findById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            return ResponseEntity.ok(evaluacionDesempenoFacade.findById(uuid));
        } catch (IllegalArgumentException e) {
            System.err.println("ID DE EVALUACIÓN INVÁLIDO: " + id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            System.err.println("ERROR AL BUSCAR EVALUACIÓN: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Endpoint para actualizar una evaluación de desempeño
     * PUT /api/v1/evaluaciones-desempeno/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> update(@PathVariable String id, @RequestBody EvaluacionDesempenoDTO evaluacionDTO) {
        try {
            evaluacionDTO.setId(id);
            evaluacionDesempenoFacade.update(evaluacionDTO);

            Map<String, String> response = new HashMap<>();
            response.put(KEY_MENSAJE, MSG_ACTUALIZACION_EXITOSA);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("ERROR AL ACTUALIZAR EVALUACIÓN: " + e.getMessage());
            e.printStackTrace();

            Map<String, String> error = new HashMap<>();
            error.put(KEY_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    /**
     * Endpoint para eliminar una evaluación de desempeño
     * DELETE /api/v1/evaluaciones-desempeno/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            evaluacionDesempenoFacade.delete(uuid);

            Map<String, String> response = new HashMap<>();
            response.put(KEY_MENSAJE, MSG_ELIMINACION_EXITOSA);

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            System.err.println("ID DE EVALUACIÓN INVÁLIDO: " + id);
            Map<String, String> error = new HashMap<>();
            error.put(KEY_ERROR, "ID de evaluación inválido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (Exception e) {
            System.err.println("ERROR AL ELIMINAR EVALUACIÓN: " + e.getMessage());
            e.printStackTrace();

            Map<String, String> error = new HashMap<>();
            error.put(KEY_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}
