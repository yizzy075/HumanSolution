package co.edu.uco.HumanSolution.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EvaluacionDesempenoDTO {

    private String id;
    private String idUsuario;
    private String fecha;
    private String evaluador;
    private String criterios;
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

    @JsonProperty("idUsuario")
    public String getIdUsuario() {
        return idUsuario;
    }

    @JsonProperty("idUsuario")
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    @JsonProperty("fecha")
    public String getFecha() {
        return fecha;
    }

    @JsonProperty("fecha")
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @JsonProperty("evaluador")
    public String getEvaluador() {
        return evaluador;
    }

    @JsonProperty("evaluador")
    public void setEvaluador(String evaluador) {
        this.evaluador = evaluador;
    }

    @JsonProperty("criterios")
    public String getCriterios() {
        return criterios;
    }

    @JsonProperty("criterios")
    public void setCriterios(String criterios) {
        this.criterios = criterios;
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
                ", idUsuario='" + idUsuario + '\'' +
                ", fecha='" + fecha + '\'' +
                ", evaluador='" + evaluador + '\'' +
                ", criterios='" + criterios + '\'' +
                ", calificacion=" + calificacion +
                ", observacion='" + observacion + '\'' +
                '}';
    }
}
