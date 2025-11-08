package co.edu.uco.HumanSolution.controller;

import co.edu.uco.HumanSolution.business.facade.UsuarioFacade;
import co.edu.uco.HumanSolution.dto.UsuarioDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    private static final String KEY_MENSAJE = "mensaje";
    private static final String KEY_ERROR = "error";

    private final UsuarioFacade usuarioFacade;

    public UsuarioController(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> register(@RequestBody String rawJson) {
        try {
            System.out.println("======= JSON CRUDO RECIBIDO =======");
            System.out.println(rawJson);
            System.out.println("===================================");

            // Deserialización manual
            ObjectMapper mapper = new ObjectMapper();
            UsuarioDTO usuarioDTO = mapper.readValue(rawJson, UsuarioDTO.class);

            System.out.println("DTO deserializado manualmente: " + usuarioDTO);
            System.out.println("ID: " + usuarioDTO.getId());
            System.out.println("Documento: " + usuarioDTO.getDocumento());
            System.out.println("Nombre: " + usuarioDTO.getNombre());
            System.out.println("Correo: " + usuarioDTO.getCorreo());
            System.out.println("Contrasena: " + usuarioDTO.getContrasena());
            System.out.println("Rol: " + usuarioDTO.getRol());
            if (usuarioDTO.getRol() != null) {
                System.out.println("Rol ID: " + usuarioDTO.getRol().getId());
            }

            // Guardar usuario en base de datos
            usuarioFacade.register(usuarioDTO);

            Map<String, String> response = new HashMap<>();
            response.put(KEY_MENSAJE, "Usuario registrado correctamente");

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            System.err.println("ERROR AL DESERIALIZAR MANUALMENTE: " + e.getMessage());
            e.printStackTrace();

            Map<String, String> error = new HashMap<>();
            error.put(KEY_ERROR, "Error al registrar usuario: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
