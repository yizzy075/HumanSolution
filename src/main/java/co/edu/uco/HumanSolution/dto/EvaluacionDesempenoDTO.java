package co.edu.uco.HumanSolution.dto;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.time.LocalDate;

public class EvaluacionDesempenoDTO {

    private String id;
    private String idUsuario;
    private LocalDate fecha;
    private int calificacion;
    private String observacion;

    public EvaluacionDesempenoDTO(String id, String idUsuario, LocalDate fecha, int calificacion, String observacion) {
        setId(id);
        setIdUsuario(idUsuario);
        setFecha(fecha);
        setCalificacion(calificacion);
        setObservacion(observacion);
    }

    public EvaluacionDesempenoDTO() {
        setId(UUIDHelper.getDefaultUUIDAsString());
        setIdUsuario(UUIDHelper.getDefaultUUIDAsString());
        setFecha(LocalDate.now());
        setCalificacion(0);
        setObservacion(TextHelper.EMPTY);
    }

    public static EvaluacionDesempenoDTO create(String id, String idUsuario, LocalDate fecha, int calificacion, String observacion) {
        return new EvaluacionDesempenoDTO(id, idUsuario, fecha, calificacion, observacion);
    }

    public static EvaluacionDesempenoDTO create() {
        return new EvaluacionDesempenoDTO();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = TextHelper.applyTrim(observacion);
    }
}