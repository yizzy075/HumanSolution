package co.edu.uco.HumanSolution.data.factory;

import co.edu.uco.HumanSolution.data.dao.*;
import co.edu.uco.HumanSolution.data.factory.postgresql.PostgreSqlDAOFactory;

import java.sql.Connection;

public abstract class DAOFactory {

    protected abstract Connection getConnection();

    public abstract void closeConnection();

    public abstract void initTransaction();

    public abstract void commitTransaction();

    public abstract void rollbackTransaction();

    // CATÁLOGOS
    public abstract TipoDocumentoDAO getTipoDocumentoDAO();

    public abstract TipoPermisoDAO getTipoPermisoDAO();

    public abstract EstadoSolicitudDAO getEstadoSolicitudDAO();

    public abstract TipoHoraExtraDAO getTipoHoraExtraDAO();

    public abstract EstadoPuestoDAO getEstadoPuestoDAO();

    public abstract RolDAO getRolDAO();

    public abstract PermisoSistemaDAO getPermisoSistemaDAO();

    // RELACIONES
    public abstract RolPermisoDAO getRolPermisoDAO();

    public abstract UnidadOrganizativaDAO getUnidadOrganizativaDAO();

    public abstract UsuarioDAO getUsuarioDAO();

    // OPERACIONALES
    public abstract PuestoDAO getPuestoDAO();

    public abstract ContratoDAO getContratoDAO();

    public abstract ExperienciaLaboralDAO getExperienciaLaboralDAO();

    public abstract EvaluacionDesempenoDAO getEvaluacionDesempenoDAO();

    public abstract UsuarioDocumentoDAO getUsuarioDocumentoDAO();

    public abstract UsuarioPermisoDAO getUsuarioPermisoDAO();

    public abstract UsuarioHoraExtraDAO getUsuarioHoraExtraDAO();

    // FACTORY METHOD
    public static DAOFactory getFactory() {
        return new PostgreSqlDAOFactory();
    }
}