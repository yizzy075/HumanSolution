package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.time.LocalDate;
import java.util.UUID;

public class EvaluacionDesempenoEntity extends Entity {

    private UUID idUsuario;
    private LocalDate fecha;
    private int calificacion;
    private String observacion;

    public EvaluacionDesempenoEntity(UUID id, UUID idUsuario, LocalDate fecha, int calificacion, String observacion) {
        super(id);
        setIdUsuario(idUsuario);
        setFecha(fecha);
        setCalificacion(calificacion);
        setObservacion(observacion);
    }

    public EvaluacionDesempenoEntity() {
        super();
        setIdUsuario(UUIDHelper.getDefaultUUID());
        setFecha(LocalDate.now());
        setCalificacion(0);
        setObservacion(TextHelper.EMPTY);
    }

    public static EvaluacionDesempenoEntity create(UUID id, UUID idUsuario, LocalDate fecha, int calificacion, String observacion) {
        return new EvaluacionDesempenoEntity(id, idUsuario, fecha, calificacion, observacion);
    }

    public static EvaluacionDesempenoEntity create() {
        return new EvaluacionDesempenoEntity();
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    private void setIdUsuario(UUID idUsuario) {
        this.idUsuario = UUIDHelper.getDefault(idUsuario, UUIDHelper.getDefaultUUID());
    }

    public LocalDate getFecha() {
        return fecha;
    }

    private void setFecha(LocalDate fecha) {
        this.fecha = fecha != null ? fecha : LocalDate.now();
    }

    public int getCalificacion() {
        return calificacion;
    }

    private void setCalificacion(int calificacion) {
        this.calificacion = Math.max(0, Math.min(calificacion, 10));
    }

    public String getObservacion() {
        return observacion;
    }

    private void setObservacion(String observacion) {
        this.observacion = TextHelper.applyTrim(observacion);
    }
}