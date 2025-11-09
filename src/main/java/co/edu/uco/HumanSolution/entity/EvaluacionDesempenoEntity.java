package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "evaluacion_desempeno")
public class EvaluacionDesempenoEntity extends Entity {

    @Column(name = "id_usuario", nullable = false)
    private UUID idUsuario;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "calificacion", nullable = false)
    private Integer calificacion;

    @Column(name = "observacion", nullable = false, length = 500)
    private String observacion;

    public EvaluacionDesempenoEntity(UUID id, UUID idUsuario, LocalDate fecha, Integer calificacion, String observacion) {
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

    public static EvaluacionDesempenoEntity create(UUID id, UUID idUsuario, LocalDate fecha, Integer calificacion, String observacion) {
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

    public Integer getCalificacion() {
        return calificacion;
    }

    private void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion != null ? calificacion : 0;
    }

    public String getObservacion() {
        return observacion;
    }

    private void setObservacion(String observacion) {
        this.observacion = TextHelper.applyTrim(observacion);
    }
}
