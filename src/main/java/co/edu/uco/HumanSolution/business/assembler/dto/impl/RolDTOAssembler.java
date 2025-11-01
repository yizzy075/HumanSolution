package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.domain.RolDomain;
import co.edu.uco.HumanSolution.dto.RolDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RolDTOAssembler implements DTOAssembler<RolDomain, RolDTO> {

    @Override
    public RolDomain toDomain(RolDTO dto) {
        return RolDomain.create(
                UUID.fromString(dto.getId()),
                dto.getNombre()
        );
    }

    @Override
    public RolDTO toDTO(RolDomain domain) {
        return RolDTO.create(
                domain.getId().toString(),
                domain.getNombre()
        );
    }

    public List<RolDTO> toDTOList(List<RolDomain> domains) {
        List<RolDTO> dtos = new ArrayList<>();
        for (RolDomain domain : domains) {
            dtos.add(toDTO(domain));
        }
        return dtos;
    }
}