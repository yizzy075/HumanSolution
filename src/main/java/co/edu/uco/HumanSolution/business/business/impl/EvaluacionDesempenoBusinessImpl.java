package co.edu.uco.HumanSolution.business.business.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.impl.EvaluacionDesempenoEntityAssembler;
import co.edu.uco.HumanSolution.business.business.EvaluacionDesempenoBusiness;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.exception.BusinessException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.crosscutting.messagecatalog.MessagesEnum;
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
            // ED-01, ED-03, ED-05: Validar datos obligatorios, fecha no futura y buenas prácticas
            domain.validar();

            // ED-02: Verificar que no exista una evaluación para el mismo usuario en la misma fecha
            validarNoExisteDuplicado(domain);

            // ED-04: Verificar que el usuario tenga un contrato vigente en la fecha de evaluación
            validarContratoVigente(domain);

            // Generar ID único
            var id = generateId();
            var domainWithId = EvaluacionDesempenoDomain.create(
                    id,
                    domain.getIdUsuario(),
                    domain.getFecha(),
                    domain.getEvaluador(),
                    domain.getCriterios(),
                    domain.getCalificacion(),
                    domain.getObservacion()
            );

            // Convertir a Entity y persistir
            var entity = EvaluacionDesempenoEntityAssembler.getEvaluacionDesempenoEntityAssembler().toEntity(domainWithId);
            daoFactory.getEvaluacionDesempenoDAO().create(entity);

        } catch (BusinessException exception) {
            throw exception;
        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico creando evaluación de desempeño: " + exception.getMessage(),
                    MessagesEnum.EVALUACION_ERROR_REGISTRO.getMessage(),
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
                    MessagesEnum.EVALUACION_ERROR_CONSULTA.getMessage(),
                    exception
            );
        }
    }

    @Override
    public List<EvaluacionDesempenoDomain> findByUsuario(UUID idUsuario) {
        try {
            List<EvaluacionDesempenoEntity> entities = daoFactory.getEvaluacionDesempenoDAO().findByUsuario(idUsuario);
            return EvaluacionDesempenoEntityAssembler.getEvaluacionDesempenoEntityAssembler().toDomainList(entities);

        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando evaluaciones por usuario: " + exception.getMessage(),
                    MessagesEnum.EVALUACION_ERROR_CONSULTA.getMessage(),
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
                    "",
                    "",
                    0,
                    ""
            );
            List<EvaluacionDesempenoEntity> entities = daoFactory.getEvaluacionDesempenoDAO().read(filter);

            if (entities.isEmpty()) {
                throw new HumanSolutionException(
                        "Evaluación de desempeño con ID " + id + " no existe",
                        MessagesEnum.EVALUACION_NO_EXISTE.getMessage()
                );
            }

            return EvaluacionDesempenoEntityAssembler.getEvaluacionDesempenoEntityAssembler().toDomain(entities.get(0));

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando evaluación de desempeño: " + exception.getMessage(),
                    MessagesEnum.EVALUACION_ERROR_CONSULTA.getMessage(),
                    exception
            );
        }
    }

    @Override
    public void update(EvaluacionDesempenoDomain domain) {
        try {
            // ED-01, ED-03, ED-05: Validar datos obligatorios, fecha no futura y buenas prácticas
            domain.validar();

            // ED-04: Verificar que el usuario tenga un contrato vigente en la fecha de evaluación
            validarContratoVigente(domain);

            var entity = EvaluacionDesempenoEntityAssembler.getEvaluacionDesempenoEntityAssembler().toEntity(domain);
            daoFactory.getEvaluacionDesempenoDAO().update(entity);

        } catch (BusinessException exception) {
            throw exception;
        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando evaluación de desempeño: " + exception.getMessage(),
                    MessagesEnum.EVALUACION_ERROR_ACTUALIZACION.getMessage(),
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
                    MessagesEnum.EVALUACION_ERROR_ELIMINACION.getMessage(),
                    exception
            );
        }
    }

    // ED-02: No se pueden registrar dos evaluaciones en la misma fecha para el mismo usuario
    private void validarNoExisteDuplicado(EvaluacionDesempenoDomain domain) {
        if (daoFactory.getEvaluacionDesempenoDAO().existsByUsuarioAndFecha(domain.getIdUsuario(), domain.getFecha())) {
            throw new BusinessException(
                    "Ya existe una evaluación registrada para el usuario " + domain.getIdUsuario() + " en la fecha " + domain.getFecha(),
                    MessagesEnum.EVALUACION_DUPLICADA.getMessage()
            );
        }
    }

    // ED-04: La evaluación debe estar vinculada a un contrato vigente del evaluado
    private void validarContratoVigente(EvaluacionDesempenoDomain domain) {
        // Verificar que el usuario tenga un contrato
        var contratos = daoFactory.getContratoDAO().findByUsuario(domain.getIdUsuario());

        if (contratos.isEmpty()) {
            throw new BusinessException(
                    "El usuario " + domain.getIdUsuario() + " no tiene contratos registrados",
                    MessagesEnum.EVALUACION_SIN_CONTRATO.getMessage()
            );
        }

        // Verificar que exista un contrato vigente en la fecha de evaluación
        boolean tieneContratoVigente = daoFactory.getContratoDAO()
                .existsContratoVigenteByUsuarioAndFecha(domain.getIdUsuario(), domain.getFecha());

        if (!tieneContratoVigente) {
            throw new BusinessException(
                    "El usuario " + domain.getIdUsuario() + " no tiene un contrato vigente en la fecha " + domain.getFecha(),
                    MessagesEnum.EVALUACION_CONTRATO_NO_VIGENTE.getMessage()
            );
        }
    }

    private UUID generateId() {
        var id = UUIDHelper.generateNewUUID();
        var entity = EvaluacionDesempenoEntity.create(
                id,
                UUIDHelper.getDefaultUUID(),
                null,
                "",
                "",
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
                    "",
                    "",
                    0,
                    ""
            );
            existing = daoFactory.getEvaluacionDesempenoDAO().read(entity);
        }

        return id;
    }
}
