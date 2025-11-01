package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.EntityAssembler;
import co.edu.uco.HumanSolution.domain.EstadoPuestoDomain;
import co.edu.uco.HumanSolution.entity.EstadoPuestoEntity;

import java.util.ArrayList;
import java.util.List;

public class EstadoPuestoEntityAssembler implements EntityAssembler<EstadoPuestoDomain, EstadoPuestoEntity> {

    @Override
    public EstadoPuestoDomain toDomain(EstadoPuestoEntity entity) {
        return EstadoPuestoDomain.create(
                entity.getId(),
                entity.getNombre()
        );
    }

    @Override
    public EstadoPuestoEntity toEntity(EstadoPuestoDomain domain) {
        return EstadoPuestoEntity.create(
                domain.getId(),
                domain.getNombre()
        );
    }

    public List<EstadoPuestoDomain> toDomainList(List<EstadoPuestoEntity> entities) {
        List<EstadoPuestoDomain> domains = new ArrayList<>();
        for (EstadoPuestoEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}