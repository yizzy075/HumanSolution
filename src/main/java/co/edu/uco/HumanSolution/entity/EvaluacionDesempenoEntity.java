package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "evaluacion_desempeno")
public class EvaluacionDesempenoEntity {  // ✅ SIN extends Entity

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

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

    // ==================== CONSTRUCTORES ====================

    public EvaluacionDesempenoEntity() {
        this.id = UUIDHelper.generate();
        this.idUsuario = UUIDHelper.getDefaultUUID();
        this.fecha = LocalDate.now();
        this.evaluador = TextHelper.EMPTY;
        this.criterios = TextHelper.EMPTY;
        this.calificacion = 0;
        this.observacion = TextHelper.EMPTY;
    }

    public EvaluacionDesempenoEntity(UUID id, UUID idUsuario, LocalDate fecha, String evaluador,
                                     String criterios, int calificacion, String observacion) {
        this.id = id;
        setIdUsuario(idUsuario);
        setFecha(fecha);
        setEvaluador(evaluador);
        setCriterios(criterios);
        setCalificacion(calificacion);
        setObservacion(observacion);
    }

    public static EvaluacionDesempenoEntity create(UUID id, UUID idUsuario, LocalDate fecha,
                                                   String evaluador, String criterios,
                                                   int calificacion, String observacion) {
        return new EvaluacionDesempenoEntity(id, idUsuario, fecha, evaluador, criterios, calificacion, observacion);
    }

    public static EvaluacionDesempenoEntity create() {
        return new EvaluacionDesempenoEntity();
    }

    // ==================== GETTERS Y SETTERS ====================

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = UUIDHelper.getDefault(idUsuario, UUIDHelper.getDefaultUUID());
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha != null ? fecha : LocalDate.now();
    }

    public String getEvaluador() {
        return evaluador;
    }

    public void setEvaluador(String evaluador) {
        this.evaluador = TextHelper.applyTrim(evaluador);
    }

    public String getCriterios() {
        return criterios;
    }

    public void setCriterios(String criterios) {
        this.criterios = TextHelper.applyTrim(criterios);
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = Math.max(0, Math.min(calificacion, 100));
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = TextHelper.applyTrim(observacion);
    }

    // ==================== MÉTODOS AUXILIARES ====================

    @Override
    public String toString() {
        return "EvaluacionDesempenoEntity{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", fecha=" + fecha +
                ", evaluador='" + evaluador + '\'' +
                ", criterios='" + criterios + '\'' +
                ", calificacion=" + calificacion +
                ", observacion='" + observacion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EvaluacionDesempenoEntity that = (EvaluacionDesempenoEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}