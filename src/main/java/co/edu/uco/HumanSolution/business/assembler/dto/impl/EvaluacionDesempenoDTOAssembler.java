package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.domain.EvaluacionDesempenoDomain;
import co.edu.uco.HumanSolution.dto.EvaluacionDesempenoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EvaluacionDesempenoDTOAssembler implements DTOAssembler<EvaluacionDesempenoDomain, EvaluacionDesempenoDTO> {

    @Override
    public EvaluacionDesempenoDomain toDomain(EvaluacionDesempenoDTO dto) {
        return EvaluacionDesempenoDomain.create(
                UUID.fromString(dto.getId()),
                UUID.fromString(dto.getIdUsuario()),
                dto.getFecha(),
                dto.getCalificacion(),
                dto.getObservacion()
        );
    }

    @Override
    public EvaluacionDesempenoDTO toDTO(EvaluacionDesempenoDomain domain) {
        return EvaluacionDesempenoDTO.create(
                domain.getId().toString(),
                domain.getIdUsuario().toString(),
                domain.getFecha(),
                domain.getCalificacion(),
                domain.getObservacion()
        );
    }

    public List<EvaluacionDesempenoDTO> toDTOList(List<EvaluacionDesempenoDomain> domains) {
        List<EvaluacionDesempenoDTO> dtos = new ArrayList<>();
        for (EvaluacionDesempenoDomain domain : domains) {
            dtos.add(toDTO(domain));
        }
        return dtos;
    }
}