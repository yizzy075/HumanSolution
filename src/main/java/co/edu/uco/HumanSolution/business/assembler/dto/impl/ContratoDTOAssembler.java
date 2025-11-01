package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.domain.ContratoDomain;
import co.edu.uco.HumanSolution.dto.ContratoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContratoDTOAssembler implements DTOAssembler<ContratoDomain, ContratoDTO> {

    @Override
    public ContratoDomain toDomain(ContratoDTO dto) {
        return ContratoDomain.create(
                UUID.fromString(dto.getId()),
                UUID.fromString(dto.getIdUsuario()),
                dto.getFechaInicio(),
                dto.getFechaFin(),
                dto.getSueldo()
        );
    }

    @Override
    public ContratoDTO toDTO(ContratoDomain domain) {
        return ContratoDTO.create(
                domain.getId().toString(),
                domain.getIdUsuario().toString(),
                domain.getFechaInicio(),
                domain.getFechaFin(),
                domain.getSueldo()
        );
    }

    public List<ContratoDTO> toDTOList(List<ContratoDomain> domains) {
        List<ContratoDTO> dtos = new ArrayList<>();
        for (ContratoDomain domain : domains) {
            dtos.add(toDTO(domain));
        }
        return dtos;
    }
}