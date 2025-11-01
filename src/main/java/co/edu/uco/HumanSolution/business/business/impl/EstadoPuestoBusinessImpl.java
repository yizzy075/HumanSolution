package co.edu.uco.HumanSolution.business.business.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.impl.EstadoPuestoEntityAssembler;
import co.edu.uco.HumanSolution.business.business.EstadoPuestoBusiness;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.domain.EstadoPuestoDomain;
import co.edu.uco.HumanSolution.entity.EstadoPuestoEntity;

import java.util.List;
import java.util.UUID;

public class EstadoPuestoBusinessImpl implements EstadoPuestoBusiness {

    private DAOFactory daoFactory;
    private EstadoPuestoEntityAssembler assembler;

    public EstadoPuestoBusinessImpl() {
        this.daoFactory = DAOFactory.getFactory();
        this.assembler = new EstadoPuestoEntityAssembler();
    }

    @Override
    public void create(EstadoPuestoDomain domain) {
        try {
            domain.validar();

            if (daoFactory.getEstadoPuestoDAO().existsByNombre(domain.getNombre())) {
                throw new HumanSolutionException(
                        "Ya existe un estado de puesto con ese nombre",
                        "El nombre ya está registrado"
                );
            }

            daoFactory.initTransaction();

            EstadoPuestoEntity entity = assembler.toEntity(domain);
            daoFactory.getEstadoPuestoDAO().create(entity);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error técnico creando estado de puesto",
                    "Error inesperado",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<EstadoPuestoDomain> list() {
        try {
            EstadoPuestoEntity filter = EstadoPuestoEntity.create();
            List<EstadoPuestoEntity> entities = daoFactory.getEstadoPuestoDAO().read(filter);
            return assembler.toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando estados de puesto",
                    "Error inesperado",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public EstadoPuestoDomain findById(UUID id) {
        try {
            EstadoPuestoEntity filter = EstadoPuestoEntity.create(id, "");
            List<EstadoPuestoEntity> entities = daoFactory.getEstadoPuestoDAO().read(filter);

            if (entities.isEmpty()) {
                throw new HumanSolutionException(
                        "No se encontró el estado de puesto",
                        "Estado de puesto no existe"
                );
            }

            return assembler.toDomain(entities.get(0));

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando estado de puesto",
                    "Error inesperado",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void update(EstadoPuestoDomain domain) {
        try {
            domain.validar();

            daoFactory.initTransaction();

            EstadoPuestoEntity entity = assembler.toEntity(domain);
            daoFactory.getEstadoPuestoDAO().update(entity);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error técnico actualizando estado de puesto",
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
            daoFactory.getEstadoPuestoDAO().delete(id);
            daoFactory.commitTransaction();

        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error técnico eliminando estado de puesto",
                    "Error inesperado",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}