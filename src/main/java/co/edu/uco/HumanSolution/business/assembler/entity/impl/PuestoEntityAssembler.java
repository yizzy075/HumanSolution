package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.EntityAssembler;
import co.edu.uco.HumanSolution.domain.PuestoDomain;
import co.edu.uco.HumanSolution.entity.PuestoEntity;

import java.util.ArrayList;
import java.util.List;

public class PuestoEntityAssembler implements EntityAssembler<PuestoDomain, PuestoEntity> {

    @Override
    public PuestoDomain toDomain(PuestoEntity entity) {
        return PuestoDomain.create(
                entity.getId(),
                entity.getNombre(),
                entity.getIdUsuario(),
                entity.getIdUnidad(),
                entity.getIdEstado(),
                entity.getIdJefe()
        );
    }

    @Override
    public PuestoEntity toEntity(PuestoDomain domain) {
        return PuestoEntity.create(
                domain.getId(),
                domain.getNombre(),
                domain.getIdUsuario(),
                domain.getIdUnidad(),
                domain.getIdEstado(),
                domain.getIdJefe()
        );
    }

    public List<PuestoDomain> toDomainList(List<PuestoEntity> entities) {
        List<PuestoDomain> domains = new ArrayList<>();
        for (PuestoEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}