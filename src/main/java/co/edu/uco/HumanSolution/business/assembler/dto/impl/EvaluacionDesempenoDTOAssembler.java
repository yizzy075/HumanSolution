package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.business.domain.EvaluacionDesempenoDomain;
import co.edu.uco.HumanSolution.dto.EvaluacionDesempenoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class EvaluacionDesempenoDTOAssembler implements DTOAssembler<EvaluacionDesempenoDomain, EvaluacionDesempenoDTO> {

    private static final DTOAssembler<EvaluacionDesempenoDomain, EvaluacionDesempenoDTO> instance = new EvaluacionDesempenoDTOAssembler();

    private EvaluacionDesempenoDTOAssembler() {
    }

    public static DTOAssembler<EvaluacionDesempenoDomain, EvaluacionDesempenoDTO> getEvaluacionDesempenoDTOAssembler() {
        return instance;
    }

    @Override
    public EvaluacionDesempenoDomain toDomain(EvaluacionDesempenoDTO dto) {
        return EvaluacionDesempenoDomain.create(
                UUID.fromString(dto.getId()),
                UUID.fromString(dto.getIdUsuario()),
                dto.getFecha(),  // ✅ LocalDate → LocalDate directo
                dto.getCalificacion(),
                dto.getObservacion()
        );
    }

    @Override
    public EvaluacionDesempenoDTO toDTO(EvaluacionDesempenoDomain domain) {
        return EvaluacionDesempenoDTO.create(
                domain.getId().toString(),
                domain.getIdUsuario().toString(),
                domain.getFecha(),  // ✅ LocalDate → LocalDate directo
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