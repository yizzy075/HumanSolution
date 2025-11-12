package co.edu.uco.HumanSolution.business.business.rule.evaluaciondesempeno;

import co.edu.uco.HumanSolution.business.business.rule.Rule;
import co.edu.uco.HumanSolution.business.domain.EvaluacionDesempenoDomain;
import co.edu.uco.HumanSolution.crosscutting.exception.BusinessException;
import co.edu.uco.HumanSolution.data.dao.EvaluacionDesempenoDAO;

/**
 * ED-02: No se pueden registrar dos evaluaciones en la misma fecha para el mismo usuario
 */
public class EvaluacionNotDuplicatedForSameDateRule implements Rule<EvaluacionDesempenoDomain> {

    private EvaluacionDesempenoDAO evaluacionDAO;

    public EvaluacionNotDuplicatedForSameDateRule(EvaluacionDesempenoDAO evaluacionDAO) {
        this.evaluacionDAO = evaluacionDAO;
    }

    @Override
    public void validate(EvaluacionDesempenoDomain data) {
        if (evaluacionDAO.existsByUsuarioAndFecha(data.getIdUsuario(), data.getFecha())) {
            throw new BusinessException(
                    String.format("Ya existe una evaluación para el usuario %s en la fecha %s",
                            data.getIdUsuario(), data.getFecha()),
                    "ED-02: No se pueden registrar dos evaluaciones en la misma fecha para el mismo usuario"
            );
        }
    }
}
