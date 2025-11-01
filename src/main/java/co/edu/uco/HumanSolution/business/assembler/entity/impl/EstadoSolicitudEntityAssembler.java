package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.EntityAssembler;
import co.edu.uco.HumanSolution.domain.EstadoSolicitudDomain;
import co.edu.uco.HumanSolution.entity.EstadoSolicitudEntity;

import java.util.ArrayList;
import java.util.List;

public class EstadoSolicitudEntityAssembler implements EntityAssembler<EstadoSolicitudDomain, EstadoSolicitudEntity> {

    @Override
    public EstadoSolicitudDomain toDomain(EstadoSolicitudEntity entity) {
        return EstadoSolicitudDomain.create(
                entity.getId(),
                entity.getNombre()
        );
    }

    @Override
    public EstadoSolicitudEntity toEntity(EstadoSolicitudDomain domain) {
        return EstadoSolicitudEntity.create(
                domain.getId(),
                domain.getNombre()
        );
    }

    public List<EstadoSolicitudDomain> toDomainList(List<EstadoSolicitudEntity> entities) {
        List<EstadoSolicitudDomain> domains = new ArrayList<>();
        for (EstadoSolicitudEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}