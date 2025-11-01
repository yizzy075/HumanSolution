package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.EntityAssembler;
import co.edu.uco.HumanSolution.domain.TipoDocumentoDomain;
import co.edu.uco.HumanSolution.entity.TipoDocumentoEntity;

import java.util.ArrayList;
import java.util.List;

public class TipoDocumentoEntityAssembler implements EntityAssembler<TipoDocumentoDomain, TipoDocumentoEntity> {

    @Override
    public TipoDocumentoDomain toDomain(TipoDocumentoEntity entity) {
        return TipoDocumentoDomain.create(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion()
        );
    }

    @Override
    public TipoDocumentoEntity toEntity(TipoDocumentoDomain domain) {
        return TipoDocumentoEntity.create(
                domain.getId(),
                domain.getNombre(),
                domain.getDescripcion()
        );
    }

    public List<TipoDocumentoDomain> toDomainList(List<TipoDocumentoEntity> entities) {
        List<TipoDocumentoDomain> domains = new ArrayList<>();
        for (TipoDocumentoEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}