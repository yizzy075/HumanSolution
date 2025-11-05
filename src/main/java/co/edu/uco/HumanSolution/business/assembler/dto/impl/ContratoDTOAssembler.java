package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.business.domain.ContratoDomain;
import co.edu.uco.HumanSolution.dto.ContratoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class ContratoDTOAssembler implements DTOAssembler<ContratoDomain, ContratoDTO> {

    private static final DTOAssembler<ContratoDomain, ContratoDTO> instance = new ContratoDTOAssembler();

    private ContratoDTOAssembler() {
    }

    public static DTOAssembler<ContratoDomain, ContratoDTO> getContratoDTOAssembler() {
        return instance;
    }

    @Override
    public ContratoDomain toDomain(ContratoDTO dto) {
        return ContratoDomain.create(
                UUID.fromString(dto.getId()),
                UUID.fromString(dto.getIdUsuario()),
                dto.getFechaInicio(),  // ✅ LocalDate → LocalDate directo
                dto.getFechaFin(),     // ✅ LocalDate → LocalDate directo
                dto.getSueldo()
        );
    }

    @Override
    public ContratoDTO toDTO(ContratoDomain domain) {
        return ContratoDTO.create(
                domain.getId().toString(),
                domain.getIdUsuario().toString(),
                domain.getFechaInicio(),  // ✅ LocalDate → LocalDate directo
                domain.getFechaFin(),     // ✅ LocalDate → LocalDate directo
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