package co.edu.uco.HumanSolution.business.facade.impl;

import co.edu.uco.HumanSolution.business.business.RolBusiness;  // ✅ DOS "business"
import co.edu.uco.HumanSolution.business.facade.RolFacade;
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.dto.RolDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class RolFacadeImpl implements RolFacade {

    private final RolBusiness rolBusiness;

    // ✅ Constructor con inyección de dependencias
    public RolFacadeImpl(RolBusiness rolBusiness) {
        this.rolBusiness = rolBusiness;
        System.out.println("✅ RolFacadeImpl inicializado con inyección de dependencias");
    }

    @Override
    public void create(RolDTO dto) {
        try {
            System.out.println("📋 RolFacade - Creando rol: " + dto.getNombre());
            rolBusiness.create(dto);
            System.out.println("✅ RolFacade - Rol creado exitosamente");
        } catch (HumanSolutionException exception) {
            System.err.println("❌ RolFacade - Error: " + exception.getUserMessage());
            throw exception;
        } catch (Exception exception) {
            System.err.println("❌ RolFacade - Error inesperado: " + exception.getMessage());
            throw new HumanSolutionException(
                    "Error inesperado en facade creando rol: " + exception.getMessage(),
                    "Ocurrió un error inesperado al crear el rol"
            );
        }
    }

    @Override
    public List<RolDTO> list() {
        try {
            System.out.println("📋 RolFacade - Listando todos los roles");
            List<RolDTO> roles = rolBusiness.list();
            System.out.println("✅ RolFacade - " + roles.size() + " roles encontrados");
            return roles;
        } catch (HumanSolutionException exception) {
            System.err.println("❌ RolFacade - Error: " + exception.getUserMessage());
            throw exception;
        } catch (Exception exception) {
            System.err.println("❌ RolFacade - Error inesperado: " + exception.getMessage());
            throw new HumanSolutionException(
                    "Error inesperado en facade listando roles: " + exception.getMessage(),
                    "Ocurrió un error inesperado al listar los roles"
            );
        }
    }

    @Override
    public RolDTO findById(UUID id) {
        try {
            System.out.println("📋 RolFacade - Buscando rol con ID: " + id);
            RolDTO rol = rolBusiness.findById(id);
            System.out.println("✅ RolFacade - Rol encontrado: " + rol.getNombre());
            return rol;
        } catch (HumanSolutionException exception) {
            System.err.println("❌ RolFacade - Error: " + exception.getUserMessage());
            throw exception;
        } catch (Exception exception) {
            System.err.println("❌ RolFacade - Error inesperado: " + exception.getMessage());
            throw new HumanSolutionException(
                    "Error inesperado en facade buscando rol: " + exception.getMessage(),
                    "Ocurrió un error inesperado al buscar el rol"
            );
        }
    }

    @Override
    public void update(RolDTO dto) {
        try {
            System.out.println("📋 RolFacade - Actualizando rol: " + dto.getId());
            rolBusiness.update(dto);
            System.out.println("✅ RolFacade - Rol actualizado exitosamente");
        } catch (HumanSolutionException exception) {
            System.err.println("❌ RolFacade - Error: " + exception.getUserMessage());
            throw exception;
        } catch (Exception exception) {
            System.err.println("❌ RolFacade - Error inesperado: " + exception.getMessage());
            throw new HumanSolutionException(
                    "Error inesperado en facade actualizando rol: " + exception.getMessage(),
                    "Ocurrió un error inesperado al actualizar el rol"
            );
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            System.out.println("📋 RolFacade - Eliminando rol con ID: " + id);
            rolBusiness.delete(id);
            System.out.println("✅ RolFacade - Rol eliminado exitosamente");
        } catch (HumanSolutionException exception) {
            System.err.println("❌ RolFacade - Error: " + exception.getUserMessage());
            throw exception;
        } catch (Exception exception) {
            System.err.println("❌ RolFacade - Error inesperado: " + exception.getMessage());
            throw new HumanSolutionException(
                    "Error inesperado en facade eliminando rol: " + exception.getMessage(),
                    "Ocurrió un error inesperado al eliminar el rol"
            );
        }
    }
}