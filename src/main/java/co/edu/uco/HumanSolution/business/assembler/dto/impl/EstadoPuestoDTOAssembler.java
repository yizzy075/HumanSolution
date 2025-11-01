package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.domain.EstadoPuestoDomain;
import co.edu.uco.HumanSolution.dto.EstadoPuestoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EstadoPuestoDTOAssembler implements DTOAssembler<EstadoPuestoDomain, EstadoPuestoDTO> {

    @Override
    public EstadoPuestoDomain toDomain(EstadoPuestoDTO dto) {
        return EstadoPuestoDomain.create(
                UUID.fromString(dto.getId()),
                dto.getNombre()
        );
    }

    @Override
    public EstadoPuestoDTO toDTO(EstadoPuestoDomain domain) {
        return EstadoPuestoDTO.create(
                domain.getId().toString(),
                domain.getNombre()
        );
    }

    public List<EstadoPuestoDTO> toDTOList(List<EstadoPuestoDomain> domains) {
        List<EstadoPuestoDTO> dtos = new ArrayList<>();
        for (EstadoPuestoDomain domain : domains) {
            dtos.add(toDTO(domain));
        }
        return dtos;
    }
}