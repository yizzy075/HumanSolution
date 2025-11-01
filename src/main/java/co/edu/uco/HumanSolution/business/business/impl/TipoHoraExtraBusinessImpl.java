package co.edu.uco.HumanSolution.business.business.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.impl.TipoHoraExtraEntityAssembler;
import co.edu.uco.HumanSolution.business.business.TipoHoraExtraBusiness;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.domain.TipoHoraExtraDomain;
import co.edu.uco.HumanSolution.entity.TipoHoraExtraEntity;

import java.util.List;
import java.util.UUID;

public class TipoHoraExtraBusinessImpl implements TipoHoraExtraBusiness {

    private DAOFactory daoFactory;
    private TipoHoraExtraEntityAssembler assembler;

    public TipoHoraExtraBusinessImpl() {
        this.daoFactory = DAOFactory.getFactory();
        this.assembler = new TipoHoraExtraEntityAssembler();
    }

    @Override
    public void create(TipoHoraExtraDomain domain) {
        try {
            domain.validar();

            if (daoFactory.getTipoHoraExtraDAO().existsByNombre(domain.getNombre())) {
                throw new HumanSolutionException(
                        "Ya existe un tipo de hora extra con ese nombre",
                        "El nombre ya está registrado"
                );
            }

            daoFactory.initTransaction();

            TipoHoraExtraEntity entity = assembler.toEntity(domain);
            daoFactory.getTipoHoraExtraDAO().create(entity);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error técnico creando tipo de hora extra",
                    "Error inesperado",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<TipoHoraExtraDomain> list() {
        try {
            TipoHoraExtraEntity filter = TipoHoraExtraEntity.create();
            List<TipoHoraExtraEntity> entities = daoFactory.getTipoHoraExtraDAO().read(filter);
            return assembler.toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando tipos de hora extra",
                    "Error inesperado",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public TipoHoraExtraDomain findById(UUID id) {
        try {
            TipoHoraExtraEntity filter = TipoHoraExtraEntity.create(id, "", 0);
            List<TipoHoraExtraEntity> entities = daoFactory.getTipoHoraExtraDAO().read(filter);

            if (entities.isEmpty()) {
                throw new HumanSolutionException(
                        "No se encontró el tipo de hora extra",
                        "Tipo de hora extra no existe"
                );
            }

            return assembler.toDomain(entities.get(0));

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando tipo de hora extra",
                    "Error inesperado",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void update(TipoHoraExtraDomain domain) {
        try {
            domain.validar();

            daoFactory.initTransaction();

            TipoHoraExtraEntity entity = assembler.toEntity(domain);
            daoFactory.getTipoHoraExtraDAO().update(entity);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error técnico actualizando tipo de hora extra",
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
            daoFactory.getTipoHoraExtraDAO().delete(id);
            daoFactory.commitTransaction();

        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error técnico eliminando tipo de hora extra",
                    "Error inesperado",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}