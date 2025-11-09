package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.business.domain.EvaluacionDesempenoDomain;
import co.edu.uco.HumanSolution.crosscutting.helper.TextHelper;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.dto.EvaluacionDesempenoDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class EvaluacionDesempenoDTOAssembler implements DTOAssembler<EvaluacionDesempenoDomain, EvaluacionDesempenoDTO> {

    private static final DTOAssembler<EvaluacionDesempenoDomain, EvaluacionDesempenoDTO> instance = new EvaluacionDesempenoDTOAssembler();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    private EvaluacionDesempenoDTOAssembler() {
    }

    public static DTOAssembler<EvaluacionDesempenoDomain, EvaluacionDesempenoDTO> getEvaluacionDesempenoDTOAssembler() {
        return instance;
    }

    @Override
    public EvaluacionDesempenoDomain toDomain(EvaluacionDesempenoDTO dto) {
        UUID id = (dto.getId() != null && !dto.getId().isBlank())
                ? UUID.fromString(dto.getId())
                : UUIDHelper.getDefaultUUID();

        UUID idUsuario = (dto.getIdUsuario() != null && !dto.getIdUsuario().isBlank())
                ? UUID.fromString(dto.getIdUsuario())
                : UUIDHelper.getDefaultUUID();

        LocalDate fecha = (dto.getFecha() != null && !dto.getFecha().isBlank())
                ? LocalDate.parse(dto.getFecha(), DATE_FORMATTER)
                : LocalDate.now();

        int calificacion = (dto.getCalificacion() != null)
                ? dto.getCalificacion()
                : 0;

        return EvaluacionDesempenoDomain.create(
                id,
                idUsuario,
                fecha,
                dto.getEvaluador() != null ? dto.getEvaluador() : TextHelper.EMPTY,
                dto.getCriterios() != null ? dto.getCriterios() : TextHelper.EMPTY,
                calificacion,
                dto.getObservacion() != null ? dto.getObservacion() : TextHelper.EMPTY
        );
    }

    @Override
    public EvaluacionDesempenoDTO toDTO(EvaluacionDesempenoDomain domain) {
        EvaluacionDesempenoDTO dto = new EvaluacionDesempenoDTO();

        dto.setId(domain.getId() != null ? domain.getId().toString() : null);
        dto.setIdUsuario(domain.getIdUsuario() != null ? domain.getIdUsuario().toString() : null);
        dto.setFecha(domain.getFecha() != null ? domain.getFecha().format(DATE_FORMATTER) : null);
        dto.setEvaluador(domain.getEvaluador());
        dto.setCriterios(domain.getCriterios());
        dto.setCalificacion(domain.getCalificacion());
        dto.setObservacion(domain.getObservacion());

        return dto;
    }

    public List<EvaluacionDesempenoDTO> toDTOList(List<EvaluacionDesempenoDomain> domains) {
        List<EvaluacionDesempenoDTO> dtos = new ArrayList<>();
        if (domains != null) {
            for (EvaluacionDesempenoDomain domain : domains) {
                dtos.add(toDTO(domain));
            }
        }
        return dtos;
    }
}
