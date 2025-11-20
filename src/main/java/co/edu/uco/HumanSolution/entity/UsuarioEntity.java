package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "documento", nullable = false, length = 50)
    private String documento;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "correo", nullable = false, unique = true, length = 100)
    private String correo;

    @Column(name = "contrasenia", nullable = false, length = 255)
    private String contrasenia;

    @Column(name = "id_rol", nullable = false)
    private UUID idRol;

    public UsuarioEntity() {
        this.id = UUIDHelper.generate();
        this.documento = TextHelper.EMPTY;
        this.nombre = TextHelper.EMPTY;
        this.correo = TextHelper.EMPTY;
        this.contrasenia = TextHelper.EMPTY;
        this.idRol = UUIDHelper.getDefaultUUID();
    }

    public UsuarioEntity(UUID id, String documento, String nombre, String correo, String contrasenia, UUID idRol) {
        this.id = id;
        setDocumento(documento);
        setNombre(nombre);
        setCorreo(correo);
        setContrasenia(contrasenia);
        setIdRol(idRol);
    }

    public static UsuarioEntity create(UUID id, String documento, String nombre, String correo, String contrasenia, UUID idRol) {
        return new UsuarioEntity(id, documento, nombre, correo, contrasenia, idRol);
    }

    public static UsuarioEntity create() {
        return new UsuarioEntity();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public UUID getIdRol() {
        return idRol;
    }

    public void setIdRol(UUID idRol) {
        this.idRol = UUIDHelper.getDefault(idRol, UUIDHelper.getDefaultUUID());
    }
}