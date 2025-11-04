package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.business.domain.UsuarioDomain;
import co.edu.uco.HumanSolution.dto.UsuarioDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class UsuarioDTOAssembler implements DTOAssembler<UsuarioDomain, UsuarioDTO> {

    private static final UsuarioDTOAssembler instance = new UsuarioDTOAssembler();

    private UsuarioDTOAssembler() {
    }

    public static UsuarioDTOAssembler getInstance() {
        return instance;
    }

    @Override
    public UsuarioDomain toDomain(UsuarioDTO dto) {
        return UsuarioDomain.create(
                UUID.fromString(dto.getId()),
                dto.getDocumento(),
                dto.getNombre(),
                dto.getCorreo(),
                dto.getContrasenia(),
                UUID.fromString(dto.getIdRol())
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