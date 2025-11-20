package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@jakarta.persistence.Entity
@Table(name = "rol")
public class RolEntity {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "nombre", nullable = false, length = 50, unique = true)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    // ==================== CONSTRUCTORES ====================

    public RolEntity() {
        this.id = UUIDHelper.generate();
        this.nombre = TextHelper.EMPTY;
        this.descripcion = TextHelper.EMPTY;
    }

    public RolEntity(UUID id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // ==================== MÉTODOS ESTÁTICOS CREATE ====================

    public static RolEntity create(UUID id, String nombre) {
        return new RolEntity();
    }

    public static RolEntity create(UUID id, String nombre, String descripcion) {
        return new RolEntity(id, nombre, descripcion);
    }

    // ==================== GETTERS Y SETTERS ====================

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // ==================== MÉTODOS AUXILIARES ====================

    @Override
    public String toString() {
        return "RolEntity{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolEntity rolEntity = (RolEntity) o;
        return id != null && id.equals(rolEntity.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}