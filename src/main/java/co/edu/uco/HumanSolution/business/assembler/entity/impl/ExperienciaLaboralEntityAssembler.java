package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.EntityAssembler;
import co.edu.uco.HumanSolution.domain.ExperienciaLaboralDomain;
import co.edu.uco.HumanSolution.entity.ExperienciaLaboralEntity;

import java.util.ArrayList;
import java.util.List;

public class ExperienciaLaboralEntityAssembler implements EntityAssembler<ExperienciaLaboralDomain, ExperienciaLaboralEntity> {

    @Override
    public ExperienciaLaboralDomain toDomain(ExperienciaLaboralEntity entity) {
        return ExperienciaLaboralDomain.create(
                entity.getId(),
                entity.getIdUsuario(),
                entity.getEmpresa(),
                entity.getCargo(),
                entity.getFechaInicio(),
                entity.getFechaFin()
        );
    }

    @Override
    public ExperienciaLaboralEntity toEntity(ExperienciaLaboralDomain domain) {
        return ExperienciaLaboralEntity.create(
                domain.getId(),
                domain.getIdUsuario(),
                domain.getEmpresa(),
                domain.getCargo(),
                domain.getFechaInicio(),
                domain.getFechaFin()
        );
    }

    public List<ExperienciaLaboralDomain> toDomainList(List<ExperienciaLaboralEntity> entities) {
        List<ExperienciaLaboralDomain> domains = new ArrayList<>();
        for (ExperienciaLaboralEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}