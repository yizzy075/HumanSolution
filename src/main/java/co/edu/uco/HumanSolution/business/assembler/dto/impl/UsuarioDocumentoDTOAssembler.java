package co.edu.uco.HumanSolution.business.assembler.dto.impl;

import co.edu.uco.HumanSolution.business.assembler.dto.DTOAssembler;
import co.edu.uco.HumanSolution.domain.UsuarioDocumentoDomain;
import co.edu.uco.HumanSolution.dto.UsuarioDocumentoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsuarioDocumentoDTOAssembler implements DTOAssembler<UsuarioDocumentoDomain, UsuarioDocumentoDTO> {

    @Override
    public UsuarioDocumentoDomain toDomain(UsuarioDocumentoDTO dto) {
        return UsuarioDocumentoDomain.create(
                UUID.fromString(dto.getId()),
                UUID.fromString(dto.getIdUsuario()),
                UUID.fromString(dto.getIdTipoDocumento()),
                dto.getFecha(),
                UUID.fromString(dto.getIdEstadoSolicitud())
        );
    }

    @Override
    public UsuarioDocumentoDTO toDTO(UsuarioDocumentoDomain domain) {
        return UsuarioDocumentoDTO.create(
                domain.getId().toString(),
                domain.getIdUsuario().toString(),
                domain.getIdTipoDocumento().toString(),
                domain.getFecha(),
                domain.getIdEstadoSolicitud().toString()
        );
    }

    public List<UsuarioDocumentoDTO> toDTOList(List<UsuarioDocumentoDomain> domains) {
        List<UsuarioDocumentoDTO> dtos = new ArrayList<>();
        for (UsuarioDocumentoDomain domain : domains) {
            dtos.add(toDTO(domain));
        }
        return dtos;
    }
}