package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.util.UUID;

public class UsuarioEntity extends Entity {

    private String documento;
    private String nombre;
    private String correo;
    private String contrasenia;
    private UUID idRol;

    public UsuarioEntity(UUID id, String documento, String nombre, String correo, String contrasenia, UUID idRol) {
        super(id);
        setDocumento(documento);
        setNombre(nombre);
        setCorreo(correo);
        setContrasenia(contrasenia);
        setIdRol(idRol);
    }

    public UsuarioEntity() {
        super();
        setDocumento(TextHelper.EMPTY);
        setNombre(TextHelper.EMPTY);
        setCorreo(TextHelper.EMPTY);
        setContrasenia(TextHelper.EMPTY);
        setIdRol(UUIDHelper.getDefaultUUID());
    }

    public static UsuarioEntity create(UUID id, String documento, String nombre, String correo, String contrasenia, UUID idRol) {
        return new UsuarioEntity(id, documento, nombre, correo, contrasenia, idRol);
    }

    public static UsuarioEntity create() {
        return new UsuarioEntity();
    }

    public String getDocumento() {
        return documento;
    }

    private void setDocumento(String documento) {
        this.documento = TextHelper.applyTrim(documento);
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public String getCorreo() {
        return correo;
    }

    private void setCorreo(String correo) {
        this.correo = TextHelper.applyTrim(correo);
    }

    public String getContrasenia() {
        return contrasenia;
    }

    private void setContrasenia(String contrasenia) {
        this.contrasenia = TextHelper.applyTrim(contrasenia);
    }

    public UUID getIdRol() {
        return idRol;
    }

    private void setIdRol(UUID idRol) {
        this.idRol = UUIDHelper.getDefault(idRol, UUIDHelper.getDefaultUUID());
    }
}