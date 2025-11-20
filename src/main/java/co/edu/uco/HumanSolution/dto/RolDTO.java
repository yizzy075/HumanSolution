package co.edu.uco.HumanSolution.dto;

public class RolDTO {

    private String id;
    private String nombre;
    private String descripcion;

    // ==================== CONSTRUCTORES ====================

    public RolDTO() {
        this.id = "";
        this.nombre = "";
        this.descripcion = "";
    }

    public RolDTO(String id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public static RolDTO create(String string, String nombre) {
        return null;
    }

    // ==================== GETTERS Y SETTERS ====================

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    // ==================== BUILDER PATTERN (Opcional) ====================

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String nombre;
        private String descripcion;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder descripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public RolDTO build() {
            return new RolDTO(id, nombre, descripcion);
        }
    }

    // ==================== MÉTODOS AUXILIARES ====================

    @Override
    public String toString() {
        return "RolDTO{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}