package co.edu.uco.HumanSolution.business.facade.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.impl.UsuarioDTOAssembler;
import co.edu.uco.HumanSolution.business.business.UsuarioBusiness;
import co.edu.uco.HumanSolution.business.business.impl.UsuarioBusinessImpl;
import co.edu.uco.HumanSolution.business.facade.UsuarioFacade;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.dto.UsuarioDTO;
import org.springframework.stereotype.Service;  // ← AGREGAR ESTE IMPORT

import java.util.List;
import java.util.UUID;

@Service  // ← AGREGAR ESTA ANOTACIÓN
public final class UsuarioFacadeImpl implements UsuarioFacade {

    private DAOFactory daoFactory;

    public UsuarioFacadeImpl() {
        this.daoFactory = DAOFactory.getDAOFactory();
    }

    @Override
    public void register(UsuarioDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = UsuarioDTOAssembler.getUsuarioDTOAssembler().toDomain(dto);
            UsuarioBusiness business = new UsuarioBusinessImpl(daoFactory);
            business.register(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade registrando usuario: " + exception.getMessage(),
                    "Error al registrar usuario",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public List<UsuarioDTO> list() {
        try {
            UsuarioBusiness business = new UsuarioBusinessImpl(daoFactory);
            var domains = business.list();
            return UsuarioDTOAssembler.getUsuarioDTOAssembler().toDTOList(domains);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade listando usuarios: " + exception.getMessage(),
                    "Error al listar usuarios",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public UsuarioDTO findById(UUID id) {
        try {
            UsuarioBusiness business = new UsuarioBusinessImpl(daoFactory);
            var domain = business.findById(id);
            return UsuarioDTOAssembler.getUsuarioDTOAssembler().toDTO(domain);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando usuario: " + exception.getMessage(),
                    "Error al buscar usuario",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public UsuarioDTO findByEmail(String email) {
        try {
            UsuarioBusiness business = new UsuarioBusinessImpl(daoFactory);
            var domain = business.findByEmail(email);
            return UsuarioDTOAssembler.getUsuarioDTOAssembler().toDTO(domain);

        } catch (HumanSolutionException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new HumanSolutionException(
                    "Error inesperado en Facade buscando usuario por email: " + exception.getMessage(),
                    "Error al buscar usuario",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }

    @Override
    public void update(UsuarioDTO dto) {
        try {
            daoFactory.initTransaction();

            var domain = UsuarioDTOAssembler.getUsuarioDTOAssembler().toDomain(dto);
            UsuarioBusiness business = new UsuarioBusinessImpl(daoFactory);
            business.update(domain);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade actualizando usuario: " + exception.getMessage(),
                    "Error al actualizar usuario",
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

            UsuarioBusiness business = new UsuarioBusinessImpl(daoFactory);
            business.delete(id);

            daoFactory.commitTransaction();

        } catch (HumanSolutionException exception) {
            daoFactory.rollbackTransaction();
            throw exception;
        } catch (Exception exception) {
            daoFactory.rollbackTransaction();
            throw new HumanSolutionException(
                    "Error inesperado en Facade eliminando usuario: " + exception.getMessage(),
                    "Error al eliminar usuario",
                    exception
            );
        } finally {
            daoFactory.closeConnection();
        }
    }
}