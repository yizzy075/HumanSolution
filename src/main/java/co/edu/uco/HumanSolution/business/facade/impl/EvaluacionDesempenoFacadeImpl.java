package co.edu.uco.HumanSolution.business.facade.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.impl.EvaluacionDesempenoDTOAssembler;
import co.edu.uco.HumanSolution.business.business.EvaluacionDesempenoBusiness;
import co.edu.uco.HumanSolution.business.business.impl.EvaluacionDesempenoBusinessImpl;
import co.edu.uco.HumanSolution.business.facade.EvaluacionDesempenoFacade;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.dto.EvaluacionDesempenoDTO;

import java.util.List;
import java.util.UUID;

public final class EvaluacionDesempenoFacadeImpl implements EvaluacionDesempenoFacade {

    private DAOFactory daoFactory;

    public EvaluacionDesempenoFacadeImpl() {
        this.daoFactory = DAOFactory.getDAOFactory();
    }

    @Override
    public void create(EvaluacionDesempenoDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = EvaluacionDesempenoDTOAssembler.getEvaluacionDesempenoDTOAssembler().toDomain(dto);
            EvaluacionDesempenoBusiness business = new EvaluacionDesempenoBusinessImpl(daoFactory);
            business.create(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade creando evaluación de desempeño: " + exception.getMessage(),
                    "Error al crear evaluación de desempeño",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<EvaluacionDesempenoDTO> list() {
        try {
            EvaluacionDesempenoBusiness business = new EvaluacionDesempenoBusinessImpl(daoFactory);
            var domains = business.list();
            return EvaluacionDesempenoDTOAssembler.getEvaluacionDesempenoDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade listando evaluaciones de desempeño: " + exception.getMessage(),
                    "Error al listar evaluaciones de desempeño",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<EvaluacionDesempenoDTO> findByUsuario(UUID idUsuario) {
        try {
            EvaluacionDesempenoBusiness business = new EvaluacionDesempenoBusinessImpl(daoFactory);
            var domains = business.findByUsuario(idUsuario);
            return EvaluacionDesempenoDTOAssembler.getEvaluacionDesempenoDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando evaluaciones de desempeño por usuario: " + exception.getMessage(),
                    "Error al buscar evaluaciones de desempeño",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public EvaluacionDesempenoDTO findById(UUID id) {
        try {
            EvaluacionDesempenoBusiness business = new EvaluacionDesempenoBusinessImpl(daoFactory);
            var domain = business.findById(id);
            return EvaluacionDesempenoDTOAssembler.getEvaluacionDesempenoDTOAssembler().toDTO(domain);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando evaluación de desempeño: " + exception.getMessage(),
                    "Error al buscar evaluación de desempeño",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void update(EvaluacionDesempenoDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = EvaluacionDesempenoDTOAssembler.getEvaluacionDesempenoDTOAssembler().toDomain(dto);
            EvaluacionDesempenoBusiness business = new EvaluacionDesempenoBusinessImpl(daoFactory);
            business.update(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade actualizando evaluación de desempeño: " + exception.getMessage(),
                    "Error al actualizar evaluación de desempeño",
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

            EvaluacionDesempenoBusiness business = new EvaluacionDesempenoBusinessImpl(daoFactory);
            business.delete(id);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade eliminando evaluación de desempeño: " + exception.getMessage(),
                    "Error al eliminar evaluación de desempeño",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}

