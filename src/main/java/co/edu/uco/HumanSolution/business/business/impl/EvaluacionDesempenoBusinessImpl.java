package co.edu.uco.HumanSolution.business.business.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.impl.EvaluacionDesempenoEntityAssembler;
import co.edu.uco.HumanSolution.business.business.EvaluacionDesempenoBusiness;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.business.domain.EvaluacionDesempenoDomain;
import co.edu.uco.HumanSolution.entity.EvaluacionDesempenoEntity;

import java.util.List;
import java.util.UUID;

public final class EvaluacionDesempenoBusinessImpl implements EvaluacionDesempenoBusiness {

    private DAOFactory daoFactory;

    public EvaluacionDesempenoBusinessImpl(final DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void create(EvaluacionDesempenoDomain domain) {
        try {
            domain.validar();

            var id = generateId();
            var domainWithId = EvaluacionDesempenoDomain.create(
                    id,
                    domain.getIdUsuario(),
                    domain.getFecha(),
                    domain.getCalificacion(),
                    domain.getObservacion()
            );

            var entity = EvaluacionDesempenoEntityAssembler.getEvaluacionDesempenoEntityAssembler().toEntity(domainWithId);
            daoFactory.getEvaluacionDesempenoDAO().create(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico creando evaluación de desempeño: " + exception.getMessage(),
                    "Error inesperado al crear evaluación",
                    exception
            );
        }
    }

    @Override
    public List<EvaluacionDesempenoDomain> list() {
        try {
            EvaluacionDesempenoEntity filter = EvaluacionDesempenoEntity.create();
            List<EvaluacionDesempenoEntity> entities = daoFactory.getEvaluacionDesempenoDAO().read(filter);
            return EvaluacionDesempenoEntityAssembler.getEvaluacionDesempenoEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando evaluaciones de desempeño: " + exception.getMessage(),
                    "Error inesperado al listar evaluaciones",
                    exception
            );
        }
    }

    @Override
    public List<EvaluacionDesempenoDomain> findByUsuario(UUID idUsuario) {
        try {
            EvaluacionDesempenoEntity filter = EvaluacionDesempenoEntity.create(
                    UUIDHelper.getDefaultUUID(),
                    idUsuario,
                    null,
                    0,
                    ""
            );
            List<EvaluacionDesempenoEntity> entities = daoFactory.getEvaluacionDesempenoDAO().read(filter);
            return EvaluacionDesempenoEntityAssembler.getEvaluacionDesempenoEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando evaluaciones por usuario: " + exception.getMessage(),
                    "Error inesperado al buscar evaluaciones",
                    exception
            );
        }
    }

    @Override
    public EvaluacionDesempenoDomain findById(UUID id) {
        try {
            EvaluacionDesempenoEntity filter = EvaluacionDesempenoEntity.create(
                    id,
                    UUIDHelper.getDefaultUUID(),
                    null,
                    0,
                    ""
            );
            List<EvaluacionDesempenoEntity> entities = daoFactory.getEvaluacionDesempenoDAO().read(filter);

            if (entities.isEmpty()) {
                throw new HumanSolutionException(
                        "Evaluación de desempeño con ID " + id + " no existe",
                        "No se encontró la evaluación"
                );
            }

            return EvaluacionDesempenoEntityAssembler.getEvaluacionDesempenoEntityAssembler().toDomain(entities.get(0));

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando evaluación de desempeño: " + exception.getMessage(),
                    "Error inesperado al buscar evaluación",
                    exception
            );
        }
    }

    @Override
    public void update(EvaluacionDesempenoDomain domain) {
        try {
            domain.validar();

            var entity = EvaluacionDesempenoEntityAssembler.getEvaluacionDesempenoEntityAssembler().toEntity(domain);
            daoFactory.getEvaluacionDesempenoDAO().update(entity);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando evaluación de desempeño: " + exception.getMessage(),
                    "Error inesperado al actualizar evaluación",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            daoFactory.getEvaluacionDesempenoDAO().delete(id);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando evaluación de desempeño: " + exception.getMessage(),
                    "Error inesperado al eliminar evaluación",
                    exception
            );
        }
    }

    private UUID generateId() {
        var id = UUIDHelper.generateNewUUID();
        var entity = EvaluacionDesempenoEntity.create(
                id,
                UUIDHelper.getDefaultUUID(),
                null,
                0,
                ""
        );
        var existing = daoFactory.getEvaluacionDesempenoDAO().read(entity);

        while (!existing.isEmpty()) {
            id = UUIDHelper.generateNewUUID();
            entity = EvaluacionDesempenoEntity.create(
                    id,
                    UUIDHelper.getDefaultUUID(),
                    null,
                    0,
                    ""
            );
            existing = daoFactory.getEvaluacionDesempenoDAO().read(entity);
        }

        return id;
    }
}