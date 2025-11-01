package co.edu.uco.HumanSolution.data.dao.entity.postgresql;

import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.dao.EvaluacionDesempenoDAO;
import co.edu.uco.HumanSolution.entity.EvaluacionDesempenoEntity;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EvaluacionDesempenoPostgreSqlDAO implements EvaluacionDesempenoDAO {

    private Connection connection;

    public EvaluacionDesempenoPostgreSqlDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(EvaluacionDesempenoEntity entity) {
        String sql = "INSERT INTO evaluacion_desempeno (id, id_usuario, fecha, calificacion, observacion) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getId());
            statement.setObject(2, entity.getIdUsuario());
            statement.setDate(3, Date.valueOf(entity.getFecha()));
            statement.setInt(4, entity.getCalificacion());
            statement.setString(5, entity.getObservacion());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico creando evaluación de desempeño",
                    "Error al crear evaluación de desempeño en la base de datos",
                    exception
            );
        }
    }

    @Override
    public List<EvaluacionDesempenoEntity> read(EvaluacionDesempenoEntity entity) {
        List<EvaluacionDesempenoEntity> resultados = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT id, id_usuario, fecha, calificacion, observacion FROM evaluacion_desempeno WHERE 1=1");
        List<Object> parametros = new ArrayList<>();

        if (!UUIDHelper.isDefault(entity.getId())) {
            sql.append(" AND id = ?");
            parametros.add(entity.getId());
        }

        if (!UUIDHelper.isDefault(entity.getIdUsuario())) {
            sql.append(" AND id_usuario = ?");
            parametros.add(entity.getIdUsuario());
        }

        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                statement.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    EvaluacionDesempenoEntity resultado = EvaluacionDesempenoEntity.create(
                            (UUID) resultSet.getObject("id"),
                            (UUID) resultSet.getObject("id_usuario"),
                            resultSet.getDate("fecha").toLocalDate(),
                            resultSet.getInt("calificacion"),
                            resultSet.getString("observacion")
                    );
                    resultados.add(resultado);
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando evaluaciones de desempeño",
                    "Error al consultar evaluaciones de desempeño",
                    exception
            );
        }

        return resultados;
    }

    @Override
    public void update(EvaluacionDesempenoEntity entity) {
        String sql = "UPDATE evaluacion_desempeno SET id_usuario = ?, fecha = ?, calificacion = ?, observacion = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdUsuario());
            statement.setDate(2, Date.valueOf(entity.getFecha()));
            statement.setInt(3, entity.getCalificacion());
            statement.setString(4, entity.getObservacion());
            statement.setObject(5, entity.getId());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando evaluación de desempeño",
                    "Error al actualizar evaluación de desempeño",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        String sql = "DELETE FROM evaluacion_desempeno WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando evaluación de desempeño",
                    "Error al eliminar evaluación de desempeño",
                    exception
            );
        }
    }
}