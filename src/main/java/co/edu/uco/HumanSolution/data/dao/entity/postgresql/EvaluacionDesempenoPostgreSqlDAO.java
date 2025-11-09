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
import java.time.LocalDate;
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
        String sql = "INSERT INTO evaluacion_desempeno (id, id_usuario, fecha, calificacion, observacion) " +
                     "VALUES (?, ?, ?, ?, ?)";

        System.out.println("======= DEBUG CREATE EVALUACION =======");
        System.out.println("Entity: " + entity.getId());
        System.out.println("ID Usuario: " + entity.getIdUsuario());
        System.out.println("Fecha: " + entity.getFecha());
        System.out.println("Calificacion: " + entity.getCalificacion());
        System.out.println("Observacion: " + entity.getObservacion());

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getId());
            statement.setObject(2, entity.getIdUsuario());
            statement.setDate(3, Date.valueOf(entity.getFecha()));
            statement.setInt(4, entity.getCalificacion());
            statement.setString(5, entity.getObservacion());

            statement.executeUpdate();
            System.out.println("Evaluación creada exitosamente");

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico creando evaluación de desempeño: " + exception.getMessage(),
                    "Error al registrar la evaluación de desempeño",
                    exception
            );
        }
    }

    @Override
    public List<EvaluacionDesempenoEntity> read(EvaluacionDesempenoEntity entity) {
        List<EvaluacionDesempenoEntity> resultados = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT id, id_usuario, fecha, calificacion, observacion " +
            "FROM evaluacion_desempeno WHERE 1=1"
        );
        List<Object> parametros = new ArrayList<>();

        if (!UUIDHelper.isDefault(entity.getId())) {
            sql.append(" AND id = ?");
            parametros.add(entity.getId());
        }

        if (!UUIDHelper.isDefault(entity.getIdUsuario())) {
            sql.append(" AND id_usuario = ?");
            parametros.add(entity.getIdUsuario());
        }

        if (entity.getFecha() != null) {
            sql.append(" AND fecha = ?");
            parametros.add(entity.getFecha());
        }

        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                if (parametros.get(i) instanceof LocalDate) {
                    statement.setDate(i + 1, Date.valueOf((LocalDate) parametros.get(i)));
                } else {
                    statement.setObject(i + 1, parametros.get(i));
                }
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
                    "Error técnico consultando evaluaciones de desempeño: " + exception.getMessage(),
                    "Error al consultar las evaluaciones de desempeño",
                    exception
            );
        }

        return resultados;
    }

    @Override
    public void update(EvaluacionDesempenoEntity entity) {
        String sql = "UPDATE evaluacion_desempeno SET id_usuario = ?, fecha = ?, " +
                     "calificacion = ?, observacion = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdUsuario());
            statement.setDate(2, Date.valueOf(entity.getFecha()));
            statement.setInt(3, entity.getCalificacion());
            statement.setString(4, entity.getObservacion());
            statement.setObject(5, entity.getId());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando evaluación de desempeño: " + exception.getMessage(),
                    "Error al actualizar la evaluación de desempeño",
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
                    "Error técnico eliminando evaluación de desempeño: " + exception.getMessage(),
                    "Error al eliminar la evaluación de desempeño",
                    exception
            );
        }
    }

    @Override
    public boolean existsByUsuarioAndFecha(UUID idUsuario, LocalDate fecha) {
        String sql = "SELECT COUNT(*) FROM evaluacion_desempeno WHERE id_usuario = ? AND fecha = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, idUsuario);
            statement.setDate(2, Date.valueOf(fecha));

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico verificando duplicado de evaluación: " + exception.getMessage(),
                    "Error al verificar si existe una evaluación para este usuario en la fecha indicada",
                    exception
            );
        }

        return false;
    }

    @Override
    public List<EvaluacionDesempenoEntity> findByUsuario(UUID idUsuario) {
        var filter = EvaluacionDesempenoEntity.create(
                UUIDHelper.getDefaultUUID(),
                idUsuario,
                null,
                0,
                ""
        );
        return read(filter);
    }
}
