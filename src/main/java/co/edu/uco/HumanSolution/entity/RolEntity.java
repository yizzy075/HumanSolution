package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import java.util.UUID;

public class RolEntity extends Entity {

    private String nombre;

    public RolEntity(UUID id, String nombre) {
        super(id);
        setNombre(nombre);
    }

    public RolEntity() {
        super();
        setNombre(TextHelper.EMPTY);
    }

    public static RolEntity create(UUID id, String nombre) {
        return new RolEntity(id, nombre);
    }

    public static RolEntity create() {
        return new RolEntity();
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }
}