package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.EntityAssembler;
import co.edu.uco.HumanSolution.domain.TipoPermisoDomain;
import co.edu.uco.HumanSolution.entity.TipoPermisoEntity;

import java.util.ArrayList;
import java.util.List;

public class TipoPermisoEntityAssembler implements EntityAssembler<TipoPermisoDomain, TipoPermisoEntity> {

    @Override
    public TipoPermisoDomain toDomain(TipoPermisoEntity entity) {
        return TipoPermisoDomain.create(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion()
        );
    }

    @Override
    public TipoPermisoEntity toEntity(TipoPermisoDomain domain) {
        return TipoPermisoEntity.create(
                domain.getId(),
                domain.getNombre(),
                domain.getDescripcion()
        );
    }

    public List<TipoPermisoDomain> toDomainList(List<TipoPermisoEntity> entities) {
        List<TipoPermisoDomain> domains = new ArrayList<>();
        for (TipoPermisoEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}