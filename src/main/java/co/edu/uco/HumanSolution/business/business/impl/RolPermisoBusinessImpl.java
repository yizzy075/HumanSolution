package co.edu.uco.HumanSolution.business.business.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.impl.RolPermisoEntityAssembler;
import co.edu.uco.HumanSolution.business.business.RolPermisoBusiness;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.domain.RolPermisoDomain;
import co.edu.uco.HumanSolution.entity.RolPermisoEntity;

import java.util.List;
import java.util.UUID;

public class RolPermisoBusinessImpl implements RolPermisoBusiness {

    private DAOFactory daoFactory;
    private RolPermisoEntityAssembler assembler;

    public RolPermisoBusinessImpl() {
        this.daoFactory = DAOFactory.getFactory();
        this.assembler = new RolPermisoEntityAssembler();
    }

    @Override
    public void create(RolPermisoDomain domain) {
        try {
            domain.validar();

            daoFactory.initTransaction();

            RolPermisoEntity entity = assembler.toEntity(domain);
            daoFactory.getRolPermisoDAO().create(entity);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error técnico asignando permiso a rol",
                    "Error inesperado",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<RolPermisoDomain> list() {
        try {
            RolPermisoEntity filter = RolPermisoEntity.create();
            List<RolPermisoEntity> entities = daoFactory.getRolPermisoDAO().read(filter);
            return assembler.toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando permisos de roles",
                    "Error inesperado",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<RolPermisoDomain> findByRol(UUID idRol) {
        try {
            RolPermisoEntity filter = RolPermisoEntity.create(UUID.randomUUID(), idRol, UUID.randomUUID());
            List<RolPermisoEntity> entities = daoFactory.getRolPermisoDAO().read(filter);
            return assembler.toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando permisos del rol",
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
            daoFactory.getRolPermisoDAO().delete(id);
            daoFactory.commitTransaction();

        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error técnico eliminando permiso de rol",
                    "Error inesperado",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}