package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.EntityAssembler;
import co.edu.uco.HumanSolution.domain.ContratoDomain;
import co.edu.uco.HumanSolution.entity.ContratoEntity;

import java.util.ArrayList;
import java.util.List;

public class ContratoEntityAssembler implements EntityAssembler<ContratoDomain, ContratoEntity> {

    @Override
    public ContratoDomain toDomain(ContratoEntity entity) {
        return ContratoDomain.create(
                entity.getId(),
                entity.getIdUsuario(),
                entity.getFechaInicio(),
                entity.getFechaFin(),
                entity.getSueldo()
        );
    }

    @Override
    public ContratoEntity toEntity(ContratoDomain domain) {
        return ContratoEntity.create(
                domain.getId(),
                domain.getIdUsuario(),
                domain.getFechaInicio(),
                domain.getFechaFin(),
                domain.getSueldo()
        );
    }

    public List<ContratoDomain> toDomainList(List<ContratoEntity> entities) {
        List<ContratoDomain> domains = new ArrayList<>();
        for (ContratoEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}