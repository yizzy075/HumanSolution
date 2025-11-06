package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.business.domain.UsuarioDomain;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.dto.UsuarioDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class UsuarioDTOAssembler implements DTOAssembler<UsuarioDomain, UsuarioDTO> {

    private static final UsuarioDTOAssembler instance = new UsuarioDTOAssembler();

    private UsuarioDTOAssembler() {
    }

    public static UsuarioDTOAssembler getUsuarioDTOAssembler() {
        return instance;
    }

    @Override
    public UsuarioDomain toDomain(UsuarioDTO dto) {
        UUID id;
        if (dto.getId() != null && !dto.getId().isEmpty() && !UUIDHelper.isDefaultUUIDAsString(dto.getId())) {
            id = UUID.fromString(dto.getId());
        } else {
            id = UUIDHelper.getDefaultUUID(); // Usuario nuevo, se generará en el business
        }
        
        UUID idRol;
        if (dto.getIdRol() != null && !dto.getIdRol().isEmpty() && !UUIDHelper.isDefaultUUIDAsString(dto.getIdRol())) {
            idRol = UUID.fromString(dto.getIdRol());
        } else {
            throw new IllegalArgumentException("El rol es obligatorio");
        }
        
        return UsuarioDomain.create(
                id,
                dto.getDocumento(),
                dto.getNombre(),
                dto.getCorreo(),
                dto.getContrasenia(),
                idRol
        );
    }

    @Override
    public UsuarioDTO toDTO(UsuarioDomain domain) {
        return UsuarioDTO.create(
                domain.getId().toString(),
                domain.getDocumento(),
                domain.getNombre(),
                domain.getCorreo(),
                domain.getContrasenia(),
                domain.getIdRol().toString()
        );
    }

    public List<UsuarioDTO> toDTOList(List<UsuarioDomain> domains) {
        List<UsuarioDTO> dtos = new ArrayList<>();
        for (UsuarioDomain domain : domains) {
            dtos.add(toDTO(domain));
        }
        return dtos;
    }
}