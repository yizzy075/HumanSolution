package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.business.facade.ContratoFacade;
import co.edu.uco.HumanSolution.business.facade.impl.ContratoFacadeImpl;
import co.edu.uco.HumanSolution.controller.dto.response.ResponseDTO;
import co.edu.uco.HumanSolution.crosscutting.exception.ControllerException;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.dto.ContratoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/contratos")
public class ContratoController {

    private final ContratoFacade facade;

    public ContratoController() {
        this.facade = new ContratoFacadeImpl();
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<ContratoDTO>> create(@RequestBody ContratoDTO dto) {
        try {
            facade.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseDTO.<ContratoDTO>builder()
                            .success(true)
                            .message("Contrato creado exitosamente")
                            .data(null)
                            .build());
        } catch (HumanSolutionException exception) {
            throw new ControllerException(
                    exception.getTechnicalMessage(),
                    exception.getUserMessage(),
                    exception
            );
        } catch (Exception exception) {
            throw new ControllerException(
                    "Error inesperado creando contrato: " + exception.getMessage(),
                    "Error al crear contrato",
                    exception
            );
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<ContratoDTO>>> list() {
        try {
            List<ContratoDTO> contratos = facade.list();
            return ResponseEntity.ok(ResponseDTO.<List<ContratoDTO>>builder()
                    .success(true)
                    .message("Contratos consultados exitosamente")
                    .data(contratos)
                    .build());
        } catch (HumanSolutionException exception) {
            throw new ControllerException(
                    exception.getTechnicalMessage(),
                    exception.getUserMessage(),
                    exception
            );
        } catch (Exception exception) {
            throw new ControllerException(
                    "Error inesperado listando contratos: " + exception.getMessage(),
                    "Error al listar contratos",
                    exception
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<ContratoDTO>> findById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            ContratoDTO contrato = facade.findById(uuid);
            return ResponseEntity.ok(ResponseDTO.<ContratoDTO>builder()
                    .success(true)
                    .message("Contrato consultado exitosamente")
                    .data(contrato)
                    .build());
        } catch (IllegalArgumentException exception) {
            throw new ControllerException(
                    "El ID proporcionado no es válido",
                    "ID de contrato inválido"
            );
        } catch (HumanSolutionException exception) {
            throw new ControllerException(
                    exception.getTechnicalMessage(),
                    exception.getUserMessage(),
                    exception
            );
        } catch (Exception exception) {
            throw new ControllerException(
                    "Error inesperado buscando contrato: " + exception.getMessage(),
                    "Error al buscar contrato",
                    exception
            );
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<ResponseDTO<List<ContratoDTO>>> findByUsuario(@PathVariable String idUsuario) {
        try {
            UUID uuid = UUID.fromString(idUsuario);
            List<ContratoDTO> contratos = facade.findByUsuario(uuid);
            return ResponseEntity.ok(ResponseDTO.<List<ContratoDTO>>builder()
                    .success(true)
                    .message("Contratos consultados exitosamente")
                    .data(contratos)
                    .build());
        } catch (IllegalArgumentException exception) {
            throw new ControllerException(
                    "El ID de usuario proporcionado no es válido",
                    "ID de usuario inválido"
            );
        } catch (HumanSolutionException exception) {
            throw new ControllerException(
                    exception.getTechnicalMessage(),
                    exception.getUserMessage(),
                    exception
            );
        } catch (Exception exception) {
            throw new ControllerException(
                    "Error inesperado buscando contratos: " + exception.getMessage(),
                    "Error al buscar contratos",
                    exception
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<ContratoDTO>> update(@PathVariable String id, @RequestBody ContratoDTO dto) {
        try {
            dto.setId(id);
            facade.update(dto);
            return ResponseEntity.ok(ResponseDTO.<ContratoDTO>builder()
                    .success(true)
                    .message("Contrato actualizado exitosamente")
                    .data(null)
                    .build());
        } catch (IllegalArgumentException exception) {
            throw new ControllerException(
                    "El ID proporcionado no es válido",
                    "ID de contrato inválido"
            );
        } catch (HumanSolutionException exception) {
            throw new ControllerException(
                    exception.getTechnicalMessage(),
                    exception.getUserMessage(),
                    exception
            );
        } catch (Exception exception) {
            throw new ControllerException(
                    "Error inesperado actualizando contrato: " + exception.getMessage(),
                    "Error al actualizar contrato",
                    exception
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Void>> delete(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            facade.delete(uuid);
            return ResponseEntity.ok(ResponseDTO.<Void>builder()
                    .success(true)
                    .message("Contrato eliminado exitosamente")
                    .data(null)
                    .build());
        } catch (IllegalArgumentException exception) {
            throw new ControllerException(
                    "El ID proporcionado no es válido",
                    "ID de contrato inválido"
            );
        } catch (HumanSolutionException exception) {
            throw new ControllerException(
                    exception.getTechnicalMessage(),
                    exception.getUserMessage(),
                    exception
            );
        } catch (Exception exception) {
            throw new ControllerException(
                    "Error inesperado eliminando contrato: " + exception.getMessage(),
                    "Error al eliminar contrato",
                    exception
            );
        }
    }
}

