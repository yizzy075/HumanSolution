package co.edu.uco.HumanSolution.business.domain;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.time.LocalDate;
import java.util.UUID;

public class EvaluacionDesempenoDomain extends Domain {

    private UUID idUsuario;
    private LocalDate fecha;
    private int calificacion;
    private String observacion;

    private EvaluacionDesempenoDomain(UUID id, UUID idUsuario, LocalDate fecha, int calificacion, String observacion) {
        super(id);
        setIdUsuario(idUsuario);
        setFecha(fecha);
        setCalificacion(calificacion);
        setObservacion(observacion);
    }

    public static EvaluacionDesempenoDomain create(UUID id, UUID idUsuario, LocalDate fecha, int calificacion, String observacion) {
        return new EvaluacionDesempenoDomain(id, idUsuario, fecha, calificacion, observacion);
    }

    public static EvaluacionDesempenoDomain create() {
        return new EvaluacionDesempenoDomain(UUIDHelper.getDefaultUUID(), UUIDHelper.getDefaultUUID(), LocalDate.now(), 0, TextHelper.EMPTY);
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
        this.calificacion = calificacion;
    }

    public String getObservacion() {
        return observacion;
    }

    private void setObservacion(String observacion) {
        this.observacion = TextHelper.applyTrim(observacion);
    }

    public void validarIdUsuario() {
        if (UUIDHelper.isDefault(idUsuario)) {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }
    }

    public void validarFecha() {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha es obligatoria");
        }
        if (fecha.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser futura");
        }
    }

    public void validarCalificacion() {
        if (calificacion < 0 || calificacion > 10) {
            throw new IllegalArgumentException("La calificación debe estar entre 0 y 10");
        }
    }

    public void validarObservacion() {
        if (TextHelper.isEmpty(observacion)) {
            throw new IllegalArgumentException("La observación es obligatoria");
        }
        if (observacion.length() > 500) {
            throw new IllegalArgumentException("La observación no puede exceder 500 caracteres");
        }
    }

    public void validar() {
        validarIdUsuario();
        validarFecha();
        validarCalificacion();
        validarObservacion();
    }
}