package co.edu.uco.HumanSolution.dto;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;

public class UsuarioDTO {

    private String id;
    private String documento;
    private String nombre;
    private String correo;
    private String contrasenia;
    private String idRol;

    public UsuarioDTO(String id, String documento, String nombre, String correo, String contrasenia, String idRol) {
        setId(id);
        setDocumento(documento);
        setNombre(nombre);
        setCorreo(correo);
        setContrasenia(contrasenia);
        setIdRol(idRol);
    }

    public UsuarioDTO() {
        setId(UUIDHelper.getDefaultUUIDAsString());
        setDocumento(TextHelper.EMPTY);
        setNombre(TextHelper.EMPTY);
        setCorreo(TextHelper.EMPTY);
        setContrasenia(TextHelper.EMPTY);
        setIdRol(UUIDHelper.getDefaultUUIDAsString());
    }

    public static UsuarioDTO create(String id, String documento, String nombre, String correo, String contrasenia, String idRol) {
        return new UsuarioDTO(id, documento, nombre, correo, contrasenia, idRol);
    }

    public static UsuarioDTO create() {
        return new UsuarioDTO();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = TextHelper.applyTrim(documento);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = TextHelper.applyTrim(correo);
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = TextHelper.applyTrim(contrasenia);
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }
}