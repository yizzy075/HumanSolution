package co.edu.uco.HumanSolution.data.dao;

import co.edu.uco.HumanSolution.entity.ContratoEntity;
import java.util.List;
import java.util.UUID;

public interface ContratoDAO {

    void create(ContratoEntity entity);

    List<ContratoEntity> read(ContratoEntity entity);

    void update(ContratoEntity entity);

    void delete(UUID id);

    boolean existsContratoVigenteByUsuario(UUID idUsuario);
}