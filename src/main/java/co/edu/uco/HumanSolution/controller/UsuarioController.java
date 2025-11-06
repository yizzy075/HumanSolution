package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.business.facade.UsuarioFacade;
import co.edu.uco.HumanSolution.business.facade.impl.UsuarioFacadeImpl;
import co.edu.uco.HumanSolution.controller.dto.response.ResponseDTO;
import co.edu.uco.HumanSolution.crosscutting.exception.ControllerException;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.dto.UsuarioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioFacade facade;

    public UsuarioController() {
        System.out.println("=== UsuarioController CONSTRUIDO ===");
        try {
            this.facade = new UsuarioFacadeImpl();
            System.out.println("=== UsuarioController INICIALIZADO - Endpoint /api/v1/usuarios DISPONIBLE ===");
        } catch (Exception e) {
            System.err.println("ERROR al crear UsuarioController: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<UsuarioDTO>> register(@RequestBody UsuarioDTO dto) {
        try {
            facade.register(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseDTO.<UsuarioDTO>builder()
                            .success(true)
                            .message("Usuario registrado exitosamente")
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
                    "Error inesperado registrando usuario: " + exception.getMessage(),
                    "Error al registrar usuario",
                    exception
            );
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<UsuarioDTO>>> list() {
        try {
            List<UsuarioDTO> usuarios = facade.list();
            return ResponseEntity.ok(ResponseDTO.<List<UsuarioDTO>>builder()
                    .success(true)
                    .message("Usuarios consultados exitosamente")
                    .data(usuarios)
                    .build());
        } catch (HumanSolutionException exception) {
            throw new ControllerException(
                    exception.getTechnicalMessage(),
                    exception.getUserMessage(),
                    exception
            );
        } catch (Exception exception) {
            throw new ControllerException(
                    "Error inesperado listando usuarios: " + exception.getMessage(),
                    "Error al listar usuarios",
                    exception
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<UsuarioDTO>> findById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            UsuarioDTO usuario = facade.findById(uuid);
            return ResponseEntity.ok(ResponseDTO.<UsuarioDTO>builder()
                    .success(true)
                    .message("Usuario consultado exitosamente")
                    .data(usuario)
                    .build());
        } catch (IllegalArgumentException exception) {
            throw new ControllerException(
                    "El ID proporcionado no es válido",
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
                    "Error inesperado buscando usuario: " + exception.getMessage(),
                    "Error al buscar usuario",
                    exception
            );
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseDTO<UsuarioDTO>> findByEmail(@PathVariable String email) {
        try {
            UsuarioDTO usuario = facade.findByEmail(email);
            return ResponseEntity.ok(ResponseDTO.<UsuarioDTO>builder()
                    .success(true)
                    .message("Usuario consultado exitosamente")
                    .data(usuario)
                    .build());
        } catch (HumanSolutionException exception) {
            throw new ControllerException(
                    exception.getTechnicalMessage(),
                    exception.getUserMessage(),
                    exception
            );
        } catch (Exception exception) {
            throw new ControllerException(
                    "Error inesperado buscando usuario por email: " + exception.getMessage(),
                    "Error al buscar usuario",
                    exception
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<UsuarioDTO>> update(@PathVariable String id, @RequestBody UsuarioDTO dto) {
        try {
            dto.setId(id);
            facade.update(dto);
            return ResponseEntity.ok(ResponseDTO.<UsuarioDTO>builder()
                    .success(true)
                    .message("Usuario actualizado exitosamente")
                    .data(null)
                    .build());
        } catch (IllegalArgumentException exception) {
            throw new ControllerException(
                    "El ID proporcionado no es válido",
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
                    "Error inesperado actualizando usuario: " + exception.getMessage(),
                    "Error al actualizar usuario",
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
                    .message("Usuario eliminado exitosamente")
                    .data(null)
                    .build());
        } catch (IllegalArgumentException exception) {
            throw new ControllerException(
                    "El ID proporcionado no es válido",
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
                    "Error inesperado eliminando usuario: " + exception.getMessage(),
                    "Error al eliminar usuario",
                    exception
            );
        }
    }
}

