package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.domain.PermisoSistemaDomain;
import co.edu.uco.HumanSolution.dto.PermisoSistemaDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PermisoSistemaDTOAssembler implements DTOAssembler<PermisoSistemaDomain, PermisoSistemaDTO> {

    @Override
    public PermisoSistemaDomain toDomain(PermisoSistemaDTO dto) {
        return PermisoSistemaDomain.create(
                UUID.fromString(dto.getId()),
                dto.getNombre()
        );
    }

    @Override
    public PermisoSistemaDTO toDTO(PermisoSistemaDomain domain) {
        return PermisoSistemaDTO.create(
                domain.getId().toString(),
                domain.getNombre()
        );
    }

    public List<PermisoSistemaDTO> toDTOList(List<PermisoSistemaDomain> domains) {
        List<PermisoSistemaDTO> dtos = new ArrayList<>();
        for (PermisoSistemaDomain domain : domains) {
            dtos.add(toDTO(domain));
        }
        return dtos;
    }
}