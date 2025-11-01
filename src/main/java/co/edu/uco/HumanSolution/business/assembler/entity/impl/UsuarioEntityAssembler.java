package co.edu.uco.HumanSolution.business.assembler.entity.impl;

import co.edu.uco.HumanSolution.business.assembler.entity.EntityAssembler;
import co.edu.uco.HumanSolution.domain.UsuarioDomain;
import co.edu.uco.HumanSolution.entity.UsuarioEntity;

import java.util.ArrayList;
import java.util.List;

public class UsuarioEntityAssembler implements EntityAssembler<UsuarioDomain, UsuarioEntity> {

    @Override
    public UsuarioDomain toDomain(UsuarioEntity entity) {
        return UsuarioDomain.create(
                entity.getId(),
                entity.getDocumento(),
                entity.getNombre(),
                entity.getCorreo(),
                entity.getContrasenia(),
                entity.getIdRol()
        );
    }

    @Override
    public UsuarioEntity toEntity(UsuarioDomain domain) {
        return UsuarioEntity.create(
                domain.getId(),
                domain.getDocumento(),
                domain.getNombre(),
                domain.getCorreo(),
                domain.getContrasenia(),
                domain.getIdRol()
        );
    }

    public List<UsuarioDomain> toDomainList(List<UsuarioEntity> entities) {
        List<UsuarioDomain> domains = new ArrayList<>();
        for (UsuarioEntity entity : entities) {
            domains.add(toDomain(entity));
        }
        return domains;
    }
}