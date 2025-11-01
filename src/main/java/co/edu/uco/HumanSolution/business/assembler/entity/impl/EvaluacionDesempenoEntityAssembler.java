package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.EntityAssembler;
import co.edu.uco.HumanSolution.domain.EvaluacionDesempenoDomain;
import co.edu.uco.HumanSolution.entity.EvaluacionDesempenoEntity;

import java.util.ArrayList;
import java.util.List;

public class EvaluacionDesempenoEntityAssembler implements EntityAssembler<EvaluacionDesempenoDomain, EvaluacionDesempenoEntity> {

    @Override
    public EvaluacionDesempenoDomain toDomain(EvaluacionDesempenoEntity entity) {
        return EvaluacionDesempenoDomain.create(
                entity.getId(),
                entity.getIdUsuario(),
                entity.getFecha(),
                entity.getCalificacion(),
                entity.getObservacion()
        );
    }

    @Override
    public EvaluacionDesempenoEntity toEntity(EvaluacionDesempenoDomain domain) {
        return EvaluacionDesempenoEntity.create(
                domain.getId(),
                domain.getIdUsuario(),
                domain.getFecha(),
                domain.getCalificacion(),
                domain.getObservacion()
        );
    }

    public List<EvaluacionDesempenoDomain> toDomainList(List<EvaluacionDesempenoEntity> entities) {
        List<EvaluacionDesempenoDomain> domains = new ArrayList<>();
        for (EvaluacionDesempenoEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}