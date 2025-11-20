package co.edu.uco.HumanSolution.entity;

import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "contrato")
public class ContratoEntity {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "id_usuario", nullable = false)
    private UUID idUsuario;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "sueldo", nullable = false, precision = 10, scale = 2)
    private BigDecimal sueldo;

    // ==================== CONSTRUCTORES ====================

    public ContratoEntity() {
        this.id = UUIDHelper.generate();
        this.idUsuario = UUIDHelper.getDefaultUUID();
        this.fechaInicio = LocalDate.now();
        this.fechaFin = null;
        this.sueldo = BigDecimal.ZERO;
    }

    public ContratoEntity(UUID id, UUID idUsuario, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal sueldo) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.sueldo = sueldo;
    }

    // ==================== MÉTODOS ESTÁTICOS CREATE ====================

    public static ContratoEntity create() {
        return new ContratoEntity();
    }

    public static ContratoEntity create(UUID id, UUID idUsuario, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal sueldo) {
        return new ContratoEntity(id, idUsuario, fechaInicio, fechaFin, sueldo);
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
        this.idUsuario = idUsuario;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    // ==================== MÉTODOS AUXILIARES ====================

    @Override
    public String toString() {
        return "ContratoEntity{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", sueldo=" + sueldo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContratoEntity that = (ContratoEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}