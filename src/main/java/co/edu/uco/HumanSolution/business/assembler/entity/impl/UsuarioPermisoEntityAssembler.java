package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.EntityAssembler;
import co.edu.uco.HumanSolution.domain.UsuarioPermisoDomain;
import co.edu.uco.HumanSolution.entity.UsuarioPermisoEntity;

import java.util.ArrayList;
import java.util.List;

public class UsuarioPermisoEntityAssembler implements EntityAssembler<UsuarioPermisoDomain, UsuarioPermisoEntity> {

    @Override
    public UsuarioPermisoDomain toDomain(UsuarioPermisoEntity entity) {
        return UsuarioPermisoDomain.create(
                entity.getId(),
                entity.getIdUsuario(),
                entity.getIdTipoPermiso(),
                entity.getFechaInicio(),
                entity.getFechaFin(),
                entity.getIdEstadoSolicitud()
        );
    }

    @Override
    public UsuarioPermisoEntity toEntity(UsuarioPermisoDomain domain) {
        return UsuarioPermisoEntity.create(
                domain.getId(),
                domain.getIdUsuario(),
                domain.getIdTipoPermiso(),
                domain.getFechaInicio(),
                domain.getFechaFin(),
                domain.getIdEstadoSolicitud()
        );
    }

    public List<UsuarioPermisoDomain> toDomainList(List<UsuarioPermisoEntity> entities) {
        List<UsuarioPermisoDomain> domains = new ArrayList<>();
        for (UsuarioPermisoEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}