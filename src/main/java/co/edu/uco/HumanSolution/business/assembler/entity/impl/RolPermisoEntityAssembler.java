package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.EntityAssembler;
import co.edu.uco.HumanSolution.domain.RolPermisoDomain;
import co.edu.uco.HumanSolution.entity.RolPermisoEntity;

import java.util.ArrayList;
import java.util.List;

public class RolPermisoEntityAssembler implements EntityAssembler<RolPermisoDomain, RolPermisoEntity> {

    @Override
    public RolPermisoDomain toDomain(RolPermisoEntity entity) {
        return RolPermisoDomain.create(
                entity.getId(),
                entity.getIdRol(),
                entity.getIdPermiso()
        );
    }

    @Override
    public RolPermisoEntity toEntity(RolPermisoDomain domain) {
        return RolPermisoEntity.create(
                domain.getId(),
                domain.getIdRol(),
                domain.getIdPermiso()
        );
    }

    public List<RolPermisoDomain> toDomainList(List<RolPermisoEntity> entities) {
        List<RolPermisoDomain> domains = new ArrayList<>();
        for (RolPermisoEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}