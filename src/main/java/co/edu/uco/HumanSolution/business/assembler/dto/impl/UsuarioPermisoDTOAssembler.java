package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.domain.UsuarioPermisoDomain;
import co.edu.uco.HumanSolution.dto.UsuarioPermisoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsuarioPermisoDTOAssembler implements DTOAssembler<UsuarioPermisoDomain, UsuarioPermisoDTO> {

    @Override
    public UsuarioPermisoDomain toDomain(UsuarioPermisoDTO dto) {
        return UsuarioPermisoDomain.create(
                UUID.fromString(dto.getId()),
                UUID.fromString(dto.getIdUsuario()),
                UUID.fromString(dto.getIdTipoPermiso()),
                dto.getFechaInicio(),
                dto.getFechaFin(),
                UUID.fromString(dto.getIdEstadoSolicitud())
        );
    }

    @Override
    public UsuarioPermisoDTO toDTO(UsuarioPermisoDomain domain) {
        return UsuarioPermisoDTO.create(
                domain.getId().toString(),
                domain.getIdUsuario().toString(),
                domain.getIdTipoPermiso().toString(),
                domain.getFechaInicio(),
                domain.getFechaFin(),
                domain.getIdEstadoSolicitud().toString()
        );
    }

    public List<UsuarioPermisoDTO> toDTOList(List<UsuarioPermisoDomain> domains) {
        List<UsuarioPermisoDTO> dtos = new ArrayList<>();
        for (UsuarioPermisoDomain domain : domains) {
            dtos.add(toDTO(domain));
        }
        return dtos;
    }
}