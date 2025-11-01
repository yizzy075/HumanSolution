package co.edu.uco.HumanSolution.dto;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;

public class RolDTO {

    private String id;
    private String nombre;

    public RolDTO(String id, String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public RolDTO() {
        setId(UUIDHelper.getDefaultUUIDAsString());
        setNombre(TextHelper.EMPTY);
    }

    public static RolDTO create(String id, String nombre) {
        return new RolDTO(id, nombre);
    }

    public static RolDTO create() {
        return new RolDTO();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = TextHelper.getDefault(id, UUIDHelper.getDefaultUUIDAsString());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }
}