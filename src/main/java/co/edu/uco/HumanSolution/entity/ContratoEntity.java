package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "contrato")
public class ContratoEntity extends Entity {

    @Column(name = "id_usuario", nullable = false)
    private UUID idUsuario;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "sueldo", nullable = false, precision = 10, scale = 2)
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
