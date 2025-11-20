package co.edu.uco.HumanSolution.business.business.impl;  // ← DOS "business"

import co.edu.uco.HumanSolution.business.business.RolBusiness;  // ← DOS "business"
import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.ObjectHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.StringHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.dao.RolDAO;
import co.edu.uco.HumanSolution.dto.RolDTO;
import co.edu.uco.HumanSolution.entity.RolEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class RolBusinessImpl implements RolBusiness {

    private final RolDAO rolDAO;

    public RolBusinessImpl(RolDAO rolDAO) {
        this.rolDAO = rolDAO;
        System.out.println("✅ RolBusinessImpl inicializado");
    }

    @Override
    public void create(RolDTO dto) {
        try {
            validateRolDTO(dto);
            validateRolNotExists(dto.getNombre());

            RolEntity entity = new RolEntity();
            entity.setId(UUIDHelper.generate());
            entity.setNombre(dto.getNombre());
            entity.setDescripcion(dto.getDescripcion());

            rolDAO.save(entity);

            System.out.println("✅ Rol creado: " + dto.getNombre());

        } catch (HumanSolutionException e) {
            throw e;
        } catch (Exception e) {
            System.err.println("❌ Error creando rol: " + e.getMessage());
            e.printStackTrace();
            throw new HumanSolutionException(
                    "Error inesperado creando rol: " + e.getMessage(),
                    "Error al crear el rol"
            );
        }
    }

    @Override
    public List<RolDTO> list() {
        try {
            List<RolEntity> entities = rolDAO.findAll();
            return entities.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("❌ Error listando roles: " + e.getMessage());
            e.printStackTrace();
            throw new HumanSolutionException(
                    "Error inesperado listando roles: " + e.getMessage(),
                    "Error al listar roles"
            );
        }
    }

    @Override
    public RolDTO findById(UUID id) {
        try {
            if (ObjectHelper.isNull(id)) {
                throw new HumanSolutionException(
                        "El ID del rol no puede ser nulo",
                        "ID requerido"
                );
            }

            RolEntity entity = rolDAO.findById(id)
                    .orElseThrow(() -> new HumanSolutionException(
                            "Rol no encontrado con ID: " + id,
                            "Rol no encontrado"
                    ));

            return convertToDTO(entity);

        } catch (HumanSolutionException e) {
            throw e;
        } catch (Exception e) {
            System.err.println("❌ Error buscando rol: " + e.getMessage());
            e.printStackTrace();
            throw new HumanSolutionException(
                    "Error inesperado buscando rol: " + e.getMessage(),
                    "Error al buscar rol"
            );
        }
    }

    @Override
    public void update(RolDTO dto) {
        try {
            validateRolDTO(dto);

            if (StringHelper.isEmpty(dto.getId())) {
                throw new HumanSolutionException(
                        "ID requerido para actualizar",
                        "ID requerido"
                );
            }

            UUID id = UUID.fromString(dto.getId());

            RolEntity existingEntity = rolDAO.findById(id)
                    .orElseThrow(() -> new HumanSolutionException(
                            "Rol no encontrado con ID: " + id,
                            "Rol no encontrado"
                    ));

            if (!existingEntity.getNombre().equals(dto.getNombre())) {
                validateRolNotExists(dto.getNombre());
            }

            existingEntity.setNombre(dto.getNombre());
            existingEntity.setDescripcion(dto.getDescripcion());

            rolDAO.save(existingEntity);

            System.out.println("✅ Rol actualizado: " + dto.getNombre());

        } catch (HumanSolutionException e) {
            throw e;
        } catch (Exception e) {
            System.err.println("❌ Error actualizando rol: " + e.getMessage());
            e.printStackTrace();
            throw new HumanSolutionException(
                    "Error inesperado actualizando rol: " + e.getMessage(),
                    "Error al actualizar rol"
            );
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            if (ObjectHelper.isNull(id)) {
                throw new HumanSolutionException(
                        "El ID no puede ser nulo",
                        "ID requerido"
                );
            }

            rolDAO.findById(id)
                    .orElseThrow(() -> new HumanSolutionException(
                            "Rol no encontrado con ID: " + id,
                            "Rol no encontrado"
                    ));

            rolDAO.deleteById(id);

            System.out.println("✅ Rol eliminado con ID: " + id);

        } catch (HumanSolutionException e) {
            throw e;
        } catch (Exception e) {
            System.err.println("❌ Error eliminando rol: " + e.getMessage());
            e.printStackTrace();
            throw new HumanSolutionException(
                    "Error inesperado eliminando rol: " + e.getMessage(),
                    "Error al eliminar rol"
            );
        }
    }

    private void validateRolDTO(RolDTO dto) {
        if (ObjectHelper.isNull(dto)) {
            throw new HumanSolutionException(
                    "El rol no puede ser nulo",
                    "Datos del rol requeridos"
            );
        }

        if (StringHelper.isEmpty(dto.getNombre())) {
            throw new HumanSolutionException(
                    "El nombre no puede estar vacío",
                    "Nombre requerido"
            );
        }

        if (dto.getNombre().length() < 3 || dto.getNombre().length() > 50) {
            throw new HumanSolutionException(
                    "El nombre debe tener entre 3 y 50 caracteres",
                    "Nombre inválido"
            );
        }

        if (StringHelper.isEmpty(dto.getDescripcion())) {
            throw new HumanSolutionException(
                    "La descripción no puede estar vacía",
                    "Descripción requerida"
            );
        }

        if (dto.getDescripcion().length() < 5 || dto.getDescripcion().length() > 200) {
            throw new HumanSolutionException(
                    "La descripción debe tener entre 5 y 200 caracteres",
                    "Descripción inválida"
            );
        }
    }

    private void validateRolNotExists(String nombre) {
        if (rolDAO.existsByNombre(nombre)) {
            throw new HumanSolutionException(
                    "Ya existe un rol con el nombre: " + nombre,
                    "Rol duplicado"
            );
        }
    }

    private RolDTO convertToDTO(RolEntity entity) {
        RolDTO dto = new RolDTO();
        dto.setId(entity.getId().toString());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        return dto;
    }
}