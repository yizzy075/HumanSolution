package co.edu.uco.HumanSolution.business.domain;

import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class ContratoDomain extends Domain {

    private UUID idUsuario;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private BigDecimal sueldo;

    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1423500");

    private ContratoDomain(UUID id, UUID idUsuario, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal sueldo) {
        super(id);
        setIdUsuario(idUsuario);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        setSueldo(sueldo);
    }

    public static ContratoDomain create(UUID id, UUID idUsuario, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal sueldo) {
        return new ContratoDomain(id, idUsuario, fechaInicio, fechaFin, sueldo);
    }

    public static ContratoDomain create() {
        return new ContratoDomain(UUIDHelper.getDefaultUUID(), UUIDHelper.getDefaultUUID(), LocalDate.now(), null, BigDecimal.ZERO);
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    private void setIdUsuario(UUID idUsuario) {
        this.idUsuario = UUIDHelper.getDefault(idUsuario, UUIDHelper.getDefaultUUID());
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    private void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio != null ? fechaInicio : LocalDate.now();
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    private void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    private void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo != null ? sueldo : BigDecimal.ZERO;
    }

    public void validarIdUsuario() {
        if (UUIDHelper.isDefault(idUsuario)) {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }
    }

    public void validarFechaInicio() {
        if (fechaInicio == null) {
            throw new IllegalArgumentException("La fecha de inicio es obligatoria");
        }
    }

    public void validarFechaFin() {
        if (fechaFin != null && fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
    }

    public void validarSueldo() {
        if (sueldo.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El sueldo debe ser mayor a cero");
        }
        if (sueldo.compareTo(SALARIO_MINIMO) < 0) {
            throw new IllegalArgumentException("El sueldo no puede ser menor al salario mínimo");
        }
    }

    public void validar() {
        validarIdUsuario();
        validarFechaInicio();
        validarFechaFin();
        validarSueldo();
    }
}