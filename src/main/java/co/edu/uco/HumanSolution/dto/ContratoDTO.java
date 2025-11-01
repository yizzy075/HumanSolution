package co.edu.uco.HumanSolution.dto;

import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ContratoDTO {

    private String id;
    private String idUsuario;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private BigDecimal sueldo;

    public ContratoDTO(String id, String idUsuario, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal sueldo) {
        setId(id);
        setIdUsuario(idUsuario);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        setSueldo(sueldo);
    }

    public ContratoDTO() {
        setId(UUIDHelper.getDefaultUUIDAsString());
        setIdUsuario(UUIDHelper.getDefaultUUIDAsString());
        setFechaInicio(LocalDate.now());
        setFechaFin(null);
        setSueldo(BigDecimal.ZERO);
    }

    public static ContratoDTO create(String id, String idUsuario, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal sueldo) {
        return new ContratoDTO(id, idUsuario, fechaInicio, fechaFin, sueldo);
    }

    public static ContratoDTO create() {
        return new ContratoDTO();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
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
}