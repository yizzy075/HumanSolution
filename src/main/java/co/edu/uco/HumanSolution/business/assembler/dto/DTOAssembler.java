package co.edu.uco.HumanSolution.business.assembler.dto;

public interface DTOAssembler<D, T> {

    D toDomain(T dto);

    T toDTO(D domain);
}