package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.EntityAssembler;
import co.edu.uco.HumanSolution.domain.TipoHoraExtraDomain;
import co.edu.uco.HumanSolution.entity.TipoHoraExtraEntity;

import java.util.ArrayList;
import java.util.List;

public class TipoHoraExtraEntityAssembler implements EntityAssembler<TipoHoraExtraDomain, TipoHoraExtraEntity> {

    @Override
    public TipoHoraExtraDomain toDomain(TipoHoraExtraEntity entity) {
        return TipoHoraExtraDomain.create(
                entity.getId(),
                entity.getNombre(),
                entity.getRecargo()
        );
    }

    @Override
    public TipoHoraExtraEntity toEntity(TipoHoraExtraDomain domain) {
        return TipoHoraExtraEntity.create(
                domain.getId(),
                domain.getNombre(),
                domain.getRecargo()
        );
    }

    public List<TipoHoraExtraDomain> toDomainList(List<TipoHoraExtraEntity> entities) {
        List<TipoHoraExtraDomain> domains = new ArrayList<>();
        for (TipoHoraExtraEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}