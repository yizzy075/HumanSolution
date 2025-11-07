package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.business.domain.UsuarioDomain;
import co.edu.uco.HumanSolution.dto.RolDTO;
import co.edu.uco.HumanSolution.dto.UsuarioDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class UsuarioDTOAssembler implements DTOAssembler<UsuarioDomain, UsuarioDTO> {

    private static final UsuarioDTOAssembler INSTANCE = new UsuarioDTOAssembler();

    private UsuarioDTOAssembler() {
    }

    public static UsuarioDTOAssembler getUsuarioDTOAssembler() {
        return INSTANCE;
    }

    @Override
    public UsuarioDomain toDomain(UsuarioDTO dto) {
        // Extraer ID del rol si existe
        UUID idRol = null;
        if (dto.getRol() != null && dto.getRol().getId() != null && !dto.getRol().getId().isBlank()) {
            idRol = UUID.fromString(dto.getRol().getId());
        }

        return UsuarioDomain.create(
                dto.getId() != null && !dto.getId().isBlank() ? UUID.fromString(dto.getId()) : null,
                dto.getDocumento(),
                dto.getNombre(),
                dto.getCorreo(),
                dto.getContrasena(),  // ← DTO usa "contrasena"
                idRol  // ← Solo el UUID del rol
        );
    }

    @Override
    public UsuarioDTO toDTO(UsuarioDomain domain) {
        UsuarioDTO dto = new UsuarioDTO();

        dto.setId(domain.getId() != null ? domain.getId().toString() : null);
        dto.setDocumento(domain.getDocumento());
        dto.setNombre(domain.getNombre());
        dto.setCorreo(domain.getCorreo());
        dto.setContrasena(domain.getContrasenia());  // ← Domain usa "contrasenia"

        // Crear RolDTO con solo el ID
        if (domain.getIdRol() != null) {
            RolDTO rolDTO = new RolDTO();
            rolDTO.setId(domain.getIdRol().toString());
            dto.setRol(rolDTO);
        }

        return dto;
    }

    @Override
    public List<UsuarioDTO> toDTOList(List<UsuarioDomain> domainList) {
        List<UsuarioDTO> dtoList = new ArrayList<>();

        if (domainList != null) {
            for (UsuarioDomain domain : domainList) {
                dtoList.add(toDTO(domain));
            }
        }

        return dtoList;
    }

    @Override
    public List<UsuarioDomain> toDomainList(List<UsuarioDTO> dtoList) {
        List<UsuarioDomain> domainList = new ArrayList<>();

        if (dtoList != null) {
            for (UsuarioDTO dto : dtoList) {
                domainList.add(toDomain(dto));
            }
        }

        return domainList;
    }
}