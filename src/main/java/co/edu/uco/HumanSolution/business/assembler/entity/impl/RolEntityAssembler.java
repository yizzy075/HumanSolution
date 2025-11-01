package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.EntityAssembler;
import co.edu.uco.HumanSolution.domain.RolDomain;
import co.edu.uco.HumanSolution.entity.RolEntity;

import java.util.ArrayList;
import java.util.List;

public class RolEntityAssembler implements EntityAssembler<RolDomain, RolEntity> {

    @Override
    public RolDomain toDomain(RolEntity entity) {
        return RolDomain.create(
                entity.getId(),
                entity.getNombre()
        );
    }

    @Override
    public RolEntity toEntity(RolDomain domain) {
        return RolEntity.create(
                domain.getId(),
                domain.getNombre()
        );
    }

    public List<RolDomain> toDomainList(List<RolEntity> entities) {
        List<RolDomain> domains = new ArrayList<>();
        for (RolEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}