package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.domain.ExperienciaLaboralDomain;
import co.edu.uco.HumanSolution.dto.ExperienciaLaboralDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExperienciaLaboralDTOAssembler implements DTOAssembler<ExperienciaLaboralDomain, ExperienciaLaboralDTO> {

    @Override
    public ExperienciaLaboralDomain toDomain(ExperienciaLaboralDTO dto) {
        return ExperienciaLaboralDomain.create(
                UUID.fromString(dto.getId()),
                UUID.fromString(dto.getIdUsuario()),
                dto.getEmpresa(),
                dto.getCargo(),
                dto.getFechaInicio(),
                dto.getFechaFin()
        );
    }

    @Override
    public ExperienciaLaboralDTO toDTO(ExperienciaLaboralDomain domain) {
        return ExperienciaLaboralDTO.create(
                domain.getId().toString(),
                domain.getIdUsuario().toString(),
                domain.getEmpresa(),
                domain.getCargo(),
                domain.getFechaInicio(),
                domain.getFechaFin()
        );
    }

    public List<ExperienciaLaboralDTO> toDTOList(List<ExperienciaLaboralDomain> domains) {
        List<ExperienciaLaboralDTO> dtos = new ArrayList<>();
        for (ExperienciaLaboralDomain domain : domains) {
            dtos.add(toDTO(domain));
        }
        return dtos;
    }
}