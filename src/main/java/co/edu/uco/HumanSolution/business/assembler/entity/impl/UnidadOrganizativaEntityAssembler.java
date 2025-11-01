package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.EntityAssembler;
import co.edu.uco.HumanSolution.domain.UnidadOrganizativaDomain;
import co.edu.uco.HumanSolution.entity.UnidadOrganizativaEntity;

import java.util.ArrayList;
import java.util.List;

public class UnidadOrganizativaEntityAssembler implements EntityAssembler<UnidadOrganizativaDomain, UnidadOrganizativaEntity> {

    @Override
    public UnidadOrganizativaDomain toDomain(UnidadOrganizativaEntity entity) {
        return UnidadOrganizativaDomain.create(
                entity.getId(),
                entity.getNombre(),
                entity.getIdUnidadSuperior()
        );
    }

    @Override
    public UnidadOrganizativaEntity toEntity(UnidadOrganizativaDomain domain) {
        return UnidadOrganizativaEntity.create(
                domain.getId(),
                domain.getNombre(),
                domain.getIdUnidadSuperior()
        );
    }

    public List<UnidadOrganizativaDomain> toDomainList(List<UnidadOrganizativaEntity> entities) {
        List<UnidadOrganizativaDomain> domains = new ArrayList<>();
        for (UnidadOrganizativaEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}