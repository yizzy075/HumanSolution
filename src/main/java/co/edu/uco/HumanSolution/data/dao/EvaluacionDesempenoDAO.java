package co.edu.uco.HumanSolution.data.dao;

import co.edu.uco.HumanSolution.entity.EvaluacionDesempenoEntity;
import java.util.List;
import java.util.UUID;

public interface EvaluacionDesempenoDAO {

    void create(EvaluacionDesempenoEntity entity);

    List<EvaluacionDesempenoEntity> read(EvaluacionDesempenoEntity entity);

    void update(EvaluacionDesempenoEntity entity);

    void delete(UUID id);
}