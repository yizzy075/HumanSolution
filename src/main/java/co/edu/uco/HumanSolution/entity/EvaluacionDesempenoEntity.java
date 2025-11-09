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

    @Column(name = "evaluador", nullable = false, length = 100)
    private String evaluador;

    @Column(name = "criterios", nullable = false, length = 500)
    private String criterios;

    @Column(name = "calificacion", nullable = false)
    private int calificacion;

    @Column(name = "observacion", length = 1000)
    private String observacion;

    public EvaluacionDesempenoEntity(UUID id, UUID idUsuario, LocalDate fecha, String evaluador,
                                      String criterios, int calificacion, String observacion) {
        super(id);
        setIdUsuario(idUsuario);
        setFecha(fecha);
        setEvaluador(evaluador);
        setCriterios(criterios);
        setCalificacion(calificacion);
        setObservacion(observacion);
    }

    public EvaluacionDesempenoEntity() {
        super();
        setIdUsuario(UUIDHelper.getDefaultUUID());
        setFecha(LocalDate.now());
        setEvaluador(TextHelper.EMPTY);
        setCriterios(TextHelper.EMPTY);
        setCalificacion(0);
        setObservacion(TextHelper.EMPTY);
    }

    public static EvaluacionDesempenoEntity create(UUID id, UUID idUsuario, LocalDate fecha,
                                                     String evaluador, String criterios,
                                                     int calificacion, String observacion) {
        return new EvaluacionDesempenoEntity(id, idUsuario, fecha, evaluador, criterios, calificacion, observacion);
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

    public String getEvaluador() {
        return evaluador;
    }

    private void setEvaluador(String evaluador) {
        this.evaluador = TextHelper.applyTrim(evaluador);
    }

    public String getCriterios() {
        return criterios;
    }

    private void setCriterios(String criterios) {
        this.criterios = TextHelper.applyTrim(criterios);
    }

    public int getCalificacion() {
        return calificacion;
    }

    private void setCalificacion(int calificacion) {
        this.calificacion = Math.max(0, Math.min(calificacion, 100));
    }

    public String getObservacion() {
        return observacion;
    }

    private void setObservacion(String observacion) {
        this.observacion = TextHelper.applyTrim(observacion);
    }
}
