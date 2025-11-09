package co.edu.uco.HumanSolution.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EvaluacionDesempenoDTO {

    private String id;
    private UsuarioDTO usuario;
    private String fecha;
    private Integer calificacion;
    private String observacion;

    public EvaluacionDesempenoDTO() {
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("usuario")
    public UsuarioDTO getUsuario() {
        return usuario;
    }

    @JsonProperty("usuario")
    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    @JsonProperty("fecha")
    public String getFecha() {
        return fecha;
    }

    @JsonProperty("fecha")
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @JsonProperty("calificacion")
    public Integer getCalificacion() {
        return calificacion;
    }

    @JsonProperty("calificacion")
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    @JsonProperty("observacion")
    public String getObservacion() {
        return observacion;
    }

    @JsonProperty("observacion")
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public String toString() {
        return "EvaluacionDesempenoDTO{" +
                "id='" + id + '\'' +
                ", usuario=" + (usuario != null ? usuario.getId() : "null") +
                ", fecha='" + fecha + '\'' +
                ", calificacion=" + calificacion +
                ", observacion='" + observacion + '\'' +
                '}';
    }
}
