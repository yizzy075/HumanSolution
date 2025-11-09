package co.edu.uco.HumanSolution.business.domain;

import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.crosscutting.messagecatalog.MessagesEnum;
import co.edu.uco.HumanSolution.crosscutting.exception.BusinessException;
import java.time.LocalDate;
import java.util.UUID;

public class EvaluacionDesempenoDomain extends Domain {

    private UUID idUsuario;
    private LocalDate fecha;
    private String evaluador;
    private String criterios;
    private int calificacion;
    private String observacion;

    private EvaluacionDesempenoDomain(UUID id, UUID idUsuario, LocalDate fecha, String evaluador,
                                       String criterios, int calificacion, String observacion) {
        super(id);
        setIdUsuario(idUsuario);
        setFecha(fecha);
        setEvaluador(evaluador);
        setCriterios(criterios);
        setCalificacion(calificacion);
        setObservacion(observacion);
    }

    public static EvaluacionDesempenoDomain create(UUID id, UUID idUsuario, LocalDate fecha,
                                                     String evaluador, String criterios,
                                                     int calificacion, String observacion) {
        return new EvaluacionDesempenoDomain(id, idUsuario, fecha, evaluador, criterios, calificacion, observacion);
    }

    public static EvaluacionDesempenoDomain create() {
        return new EvaluacionDesempenoDomain(
                UUIDHelper.getDefaultUUID(),
                UUIDHelper.getDefaultUUID(),
                LocalDate.now(),
                TextHelper.EMPTY,
                TextHelper.EMPTY,
                0,
                TextHelper.EMPTY
        );
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
        this.calificacion = calificacion;
    }

    public String getObservacion() {
        return observacion;
    }

    private void setObservacion(String observacion) {
        this.observacion = TextHelper.applyTrim(observacion);
    }

    // ED-01: Validar datos obligatorios (fecha, evaluador, criterios)
    public void validarIdUsuario() {
        if (UUIDHelper.isDefault(idUsuario)) {
            throw new BusinessException(
                    "El ID del usuario evaluado es obligatorio",
                    MessagesEnum.EVALUACION_USUARIO_VACIO.getMessage()
            );
        }
    }

    public void validarFecha() {
        if (fecha == null) {
            throw new BusinessException(
                    "La fecha de evaluación es nula",
                    MessagesEnum.EVALUACION_FECHA_VACIA.getMessage()
            );
        }
    }

    public void validarEvaluador() {
        if (TextHelper.isEmpty(evaluador)) {
            throw new BusinessException(
                    "El nombre del evaluador está vacío",
                    MessagesEnum.EVALUACION_EVALUADOR_VACIO.getMessage()
            );
        }
        // ED-05: Buenas prácticas - longitud mínima
        if (evaluador.length() < 3) {
            throw new BusinessException(
                    "El nombre del evaluador debe tener al menos 3 caracteres",
                    MessagesEnum.EVALUACION_EVALUADOR_CORTO.getMessage()
            );
        }
        // ED-05: Buenas prácticas - longitud máxima
        if (evaluador.length() > 100) {
            throw new BusinessException(
                    "El nombre del evaluador excede 100 caracteres",
                    MessagesEnum.EVALUACION_EVALUADOR_LARGO.getMessage()
            );
        }
    }

    public void validarCriterios() {
        if (TextHelper.isEmpty(criterios)) {
            throw new BusinessException(
                    "Los criterios de evaluación están vacíos",
                    MessagesEnum.EVALUACION_CRITERIOS_VACIOS.getMessage()
            );
        }
        // ED-05: Buenas prácticas - longitud mínima
        if (criterios.length() < 10) {
            throw new BusinessException(
                    "Los criterios deben tener al menos 10 caracteres",
                    MessagesEnum.EVALUACION_CRITERIOS_CORTOS.getMessage()
            );
        }
        // ED-05: Buenas prácticas - longitud máxima
        if (criterios.length() > 500) {
            throw new BusinessException(
                    "Los criterios exceden 500 caracteres",
                    MessagesEnum.EVALUACION_CRITERIOS_LARGOS.getMessage()
            );
        }
    }

    // ED-03: La fecha de evaluación no puede ser futura
    public void validarFechaNoFutura() {
        if (fecha != null && fecha.isAfter(LocalDate.now())) {
            throw new BusinessException(
                    "La fecha de evaluación " + fecha + " es futura",
                    MessagesEnum.EVALUACION_FECHA_FUTURA.getMessage()
            );
        }
    }

    // ED-05: Validar calificación (rango 0-100)
    public void validarCalificacion() {
        if (calificacion < 0 || calificacion > 100) {
            throw new BusinessException(
                    "La calificación " + calificacion + " está fuera del rango permitido (0-100)",
                    MessagesEnum.EVALUACION_CALIFICACION_RANGO_INVALIDO.getMessage()
            );
        }
    }

    // ED-05: Validar observación (opcional pero con límite de longitud)
    public void validarObservacion() {
        if (observacion != null && observacion.length() > 1000) {
            throw new BusinessException(
                    "La observación excede 1000 caracteres",
                    MessagesEnum.EVALUACION_OBSERVACION_LARGA.getMessage()
            );
        }
    }

    public void validar() {
        validarIdUsuario();
        validarFecha();
        validarFechaNoFutura();
        validarEvaluador();
        validarCriterios();
        validarCalificacion();
        validarObservacion();
    }
}
