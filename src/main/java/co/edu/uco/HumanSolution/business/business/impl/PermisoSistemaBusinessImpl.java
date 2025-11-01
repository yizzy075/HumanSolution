package co.edu.uco.HumanSolution.business.business.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.impl.PermisoSistemaEntityAssembler;
import co.edu.uco.HumanSolution.business.business.PermisoSistemaBusiness;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.domain.PermisoSistemaDomain;
import co.edu.uco.HumanSolution.entity.PermisoSistemaEntity;

import java.util.List;
import java.util.UUID;

public class PermisoSistemaBusinessImpl implements PermisoSistemaBusiness {

    private DAOFactory daoFactory;
    private PermisoSistemaEntityAssembler assembler;

    public PermisoSistemaBusinessImpl() {
        this.daoFactory = DAOFactory.getFactory();
        this.assembler = new PermisoSistemaEntityAssembler();
    }

    @Override
    public void create(PermisoSistemaDomain domain) {
        try {
            domain.validar();

            if (daoFactory.getPermisoSistemaDAO().existsByNombre(domain.getNombre())) {
                throw new HumanSolutionException(
                        "Ya existe un permiso de sistema con ese nombre",
                        "El nombre ya está registrado"
                );
            }

            daoFactory.initTransaction();

            PermisoSistemaEntity entity = assembler.toEntity(domain);
            daoFactory.getPermisoSistemaDAO().create(entity);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error técnico creando permiso de sistema",
                    "Error inesperado",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<PermisoSistemaDomain> list() {
        try {
            PermisoSistemaEntity filter = PermisoSistemaEntity.create();
            List<PermisoSistemaEntity> entities = daoFactory.getPermisoSistemaDAO().read(filter);
            return assembler.toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando permisos de sistema",
                    "Error inesperado",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public PermisoSistemaDomain findById(UUID id) {
        try {
            PermisoSistemaEntity filter = PermisoSistemaEntity.create(id, "");
            List<PermisoSistemaEntity> entities = daoFactory.getPermisoSistemaDAO().read(filter);

            if (entities.isEmpty()) {
                throw new HumanSolutionException(
                        "No se encontró el permiso de sistema",
                        "Permiso de sistema no existe"
                );
            }

            return assembler.toDomain(entities.get(0));

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando permiso de sistema",
                    "Error inesperado",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void update(PermisoSistemaDomain domain) {
        try {
            domain.validar();

            daoFactory.initTransaction();

            PermisoSistemaEntity entity = assembler.toEntity(domain);
            daoFactory.getPermisoSistemaDAO().update(entity);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error técnico actualizando permiso de sistema",
                    "Error inesperado",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            daoFactory.initTransaction();
            daoFactory.getPermisoSistemaDAO().delete(id);
            daoFactory.commitTransaction();

        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error técnico eliminando permiso de sistema",
                    "Error inesperado",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}