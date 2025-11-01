package co.edu.uco.HumanSolution.data.dao.entity.postgresql;

import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.crosscutting.helper.UUIDHelper;
import co.edu.uco.HumanSolution.data.dao.UsuarioDAO;
import co.edu.uco.HumanSolution.entity.UsuarioEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsuarioPostgreSqlDAO implements UsuarioDAO {

    private Connection connection;

    public UsuarioPostgreSqlDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(UsuarioEntity entity) {
        String sql = "INSERT INTO usuario (id, documento, nombre, correo, contrasenia, id_rol) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getId());
            statement.setString(2, entity.getDocumento());
            statement.setString(3, entity.getNombre());
            statement.setString(4, entity.getCorreo());
            statement.setString(5, entity.getContrasenia());
            statement.setObject(6, entity.getIdRol());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico creando usuario",
                    "Error al crear usuario en la base de datos",
                    exception
            );
        }
    }

    @Override
    public List<UsuarioEntity> read(UsuarioEntity entity) {
        List<UsuarioEntity> resultados = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT id, documento, nombre, correo, contrasenia, id_rol FROM usuario WHERE 1=1");
        List<Object> parametros = new ArrayList<>();

        if (!UUIDHelper.isDefault(entity.getId())) {
            sql.append(" AND id = ?");
            parametros.add(entity.getId());
        }

        if (!entity.getDocumento().isEmpty()) {
            sql.append(" AND documento = ?");
            parametros.add(entity.getDocumento());
        }

        if (!entity.getNombre().isEmpty()) {
            sql.append(" AND nombre ILIKE ?");
            parametros.add("%" + entity.getNombre() + "%");
        }

        if (!entity.getCorreo().isEmpty()) {
            sql.append(" AND correo = ?");
            parametros.add(entity.getCorreo());
        }

        if (!UUIDHelper.isDefault(entity.getIdRol())) {
            sql.append(" AND id_rol = ?");
            parametros.add(entity.getIdRol());
        }

        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                statement.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    UsuarioEntity resultado = UsuarioEntity.create(
                            (UUID) resultSet.getObject("id"),
                            resultSet.getString("documento"),
                            resultSet.getString("nombre"),
                            resultSet.getString("correo"),
                            resultSet.getString("contrasenia"),
                            (UUID) resultSet.getObject("id_rol")
                    );
                    resultados.add(resultado);
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico consultando usuarios",
                    "Error al consultar usuarios",
                    exception
            );
        }

        return resultados;
    }

    @Override
    public void update(UsuarioEntity entity) {
        String sql = "UPDATE usuario SET documento = ?, nombre = ?, correo = ?, contrasenia = ?, id_rol = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getDocumento());
            statement.setString(2, entity.getNombre());
            statement.setString(3, entity.getCorreo());
            statement.setString(4, entity.getContrasenia());
            statement.setObject(5, entity.getIdRol());
            statement.setObject(6, entity.getId());

            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico actualizando usuario",
                    "Error al actualizar usuario",
                    exception
            );
        }
    }

    @Override
    public void delete(UUID id) {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.executeUpdate();

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico eliminando usuario",
                    "Error al eliminar usuario",
                    exception
            );
        }
    }

    @Override
    public boolean existsByDocumento(String documento) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE documento = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, documento);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico verificando usuario por documento",
                    "Error al verificar existencia de usuario por documento",
                    exception
            );
        }

        return false;
    }

    @Override
    public boolean existsByCorreo(String correo) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE correo = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, correo);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }

        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico verificando usuario por correo",
                    "Error al verificar existencia de usuario por correo",
                    exception
            );
        }

        return false;
    }
}