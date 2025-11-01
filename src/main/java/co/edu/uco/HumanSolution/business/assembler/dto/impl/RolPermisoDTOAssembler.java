package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.domain.RolPermisoDomain;
import co.edu.uco.HumanSolution.dto.RolPermisoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RolPermisoDTOAssembler implements DTOAssembler<RolPermisoDomain, RolPermisoDTO> {

    @Override
    public RolPermisoDomain toDomain(RolPermisoDTO dto) {
        return RolPermisoDomain.create(
                UUID.fromString(dto.getId()),
                UUID.fromString(dto.getIdRol()),
                UUID.fromString(dto.getIdPermiso())
        );
    }

    @Override
    public RolPermisoDTO toDTO(RolPermisoDomain domain) {
        return RolPermisoDTO.create(
                domain.getId().toString(),
                domain.getIdRol().toString(),
                domain.getIdPermiso().toString()
        );
    }

    public List<RolPermisoDTO> toDTOList(List<RolPermisoDomain> domains) {
        List<RolPermisoDTO> dtos = new ArrayList<>();
        for (RolPermisoDomain domain : domains) {
            dtos.add(toDTO(domain));
        }
        return dtos;
    }
}