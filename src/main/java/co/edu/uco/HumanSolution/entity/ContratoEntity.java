package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class ContratoEntity extends Entity {

    private UUID idUsuario;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private BigDecimal sueldo;

    public ContratoEntity(UUID id, UUID idUsuario, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal sueldo) {
        super(id);
        setIdUsuario(idUsuario);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        setSueldo(sueldo);
    }

    public ContratoEntity() {
        super();
        setIdUsuario(UUIDHelper.getDefaultUUID());
        setFechaInicio(LocalDate.now());
        setFechaFin(null);
        setSueldo(BigDecimal.ZERO);
    }

    public static ContratoEntity create(UUID id, UUID idUsuario, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal sueldo) {
        return new ContratoEntity(id, idUsuario, fechaInicio, fechaFin, sueldo);
    }

    public static ContratoEntity create() {
        return new ContratoEntity();
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
}