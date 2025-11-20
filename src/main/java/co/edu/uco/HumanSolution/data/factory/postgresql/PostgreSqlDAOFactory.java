package co.edu.uco.HumanSolution.data.factory.postgresql;

import co.edu.uco.HumanSolution.crosscutting.exception.HumanSolutionException;
import co.edu.uco.HumanSolution.data.dao.*;
import co.edu.uco.HumanSolution.data.dao.entity.postgresql.*;
import co.edu.uco.HumanSolution.data.factory.DAOFactory;
import co.edu.uco.HumanSolution.entity.RolEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public final class PostgreSqlDAOFactory extends DAOFactory {

    private Connection connection;

    // SINGLETON PATTERN
    private static PostgreSqlDAOFactory instance;

    private PostgreSqlDAOFactory() {
        // No abrir conexión en el constructor - se abrirá cuando se necesite (lazy initialization)
    }

    // MÉTODO ESTÁTICO PARA OBTENER LA INSTANCIA
    public static PostgreSqlDAOFactory getInstance() {
        if (instance == null) {
            synchronized (PostgreSqlDAOFactory.class) {
                if (instance == null) {
                    instance = new PostgreSqlDAOFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public void initTransaction() {
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico iniciando transacción: " + exception.getMessage(),
                    "Error al iniciar transacción",
                    exception
            );
        }
    }

    @Override
    public void commitTransaction() {
        try {
            getConnection().commit();
        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico confirmando transacción: " + exception.getMessage(),
                    "Error al confirmar transacción",
                    exception
            );
        }
    }

    @Override
    public void rollbackTransaction() {
        try {
            getConnection().rollback();
        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico revirtiendo transacción: " + exception.getMessage(),
                    "Error al revertir transacción",
                    exception
            );
        }
    }

    @Override
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico cerrando conexión: " + exception.getMessage(),
                    "Error al cerrar conexión",
                    exception
            );
        }
    }

    private void openConnection() {
        // Usar las mismas credenciales que application.properties
        String url = "jdbc:postgresql://localhost:5432/sistema_usuarios";
        String user = "postgres";
        String password = "dino2020";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico abriendo conexión a base de datos: " + exception.getMessage(),
                    "Error al conectar con la base de datos",
                    exception
            );
        }
    }

    private Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                openConnection();
            }
        } catch (SQLException exception) {
            throw new HumanSolutionException(
                    "Error técnico verificando conexión: " + exception.getMessage(),
                    "Error con la conexión a base de datos",
                    exception
            );
        }
        return connection;
    }

    @Override
    public TipoDocumentoDAO getTipoDocumentoDAO() {
        return new TipoDocumentoPostgreSqlDAO(getConnection());
    }

    @Override
    public TipoPermisoDAO getTipoPermisoDAO() {
        return new TipoPermisoPostgreSqlDAO(getConnection());
    }

    @Override
    public EstadoSolicitudDAO getEstadoSolicitudDAO() {
        return new EstadoSolicitudPostgreSqlDAO(getConnection());
    }

    @Override
    public TipoHoraExtraDAO getTipoHoraExtraDAO() {
        return new TipoHoraExtraPostgreSqlDAO(getConnection());
    }

    @Override
    public EstadoPuestoDAO getEstadoPuestoDAO() {
        return new EstadoPuestoPostgreSqlDAO(getConnection());
    }

    @Override
    public RolDAO getRolDAO() {
        return new RolPostgreSqlDAO(getConnection()) {
            @Override
            public List<RolEntity> findAll(Sort sort) {
                return List.of();
            }

            @Override
            public Page<RolEntity> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends RolEntity> S save(S entity) {
                return null;
            }

            @Override
            public <S extends RolEntity> List<S> saveAll(Iterable<S> entities) {
                return List.of();
            }

            @Override
            public Optional<RolEntity> findById(UUID uuid) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(UUID uuid) {
                return false;
            }

            @Override
            public List<RolEntity> findAll() {
                return List.of();
            }

            @Override
            public List<RolEntity> findAllById(Iterable<UUID> uuids) {
                return List.of();
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(UUID uuid) {

            }

            @Override
            public void delete(RolEntity entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends UUID> uuids) {

            }

            @Override
            public void deleteAll(Iterable<? extends RolEntity> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends RolEntity> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends RolEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
                return List.of();
            }

            @Override
            public void deleteAllInBatch(Iterable<RolEntity> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<UUID> uuids) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public RolEntity getOne(UUID uuid) {
                return null;
            }

            @Override
            public RolEntity getById(UUID uuid) {
                return null;
            }

            @Override
            public RolEntity getReferenceById(UUID uuid) {
                return null;
            }

            @Override
            public <S extends RolEntity> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends RolEntity> List<S> findAll(Example<S> example) {
                return List.of();
            }

            @Override
            public <S extends RolEntity> List<S> findAll(Example<S> example, Sort sort) {
                return List.of();
            }

            @Override
            public <S extends RolEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends RolEntity> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends RolEntity> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends RolEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };
    }

    @Override
    public PermisoSistemaDAO getPermisoSistemaDAO() {
        return new PermisoSistemaPostgreSqlDAO(getConnection());
    }

    @Override
    public RolPermisoDAO getRolPermisoDAO() {
        return new RolPermisoPostgreSqlDAO(getConnection());
    }

    @Override
    public UnidadOrganizativaDAO getUnidadOrganizativaDAO() {
        return new UnidadOrganizativaPostgreSqlDAO(getConnection());
    }

    @Override
    public UsuarioDAO getUsuarioDAO() {
        return new UsuarioPostgreSqlDAO(getConnection());
    }

    @Override
    public PuestoDAO getPuestoDAO() {
        return new PuestoPostgreSqlDAO(getConnection());
    }

    @Override
    public ContratoDAO getContratoDAO() {
        return new ContratoPostgreSqlDAO(getConnection());
    }

    @Override
    public ExperienciaLaboralDAO getExperienciaLaboralDAO() {
        return new ExperienciaLaboralPostgreSqlDAO(getConnection());
    }

    @Override
    public EvaluacionDesempenoDAO getEvaluacionDesempenoDAO() {
        return new EvaluacionDesempenoPostgreSqlDAO(getConnection());
    }

    @Override
    public UsuarioDocumentoDAO getUsuarioDocumentoDAO() {
        return new UsuarioDocumentoPostgreSqlDAO(getConnection());
    }

    @Override
    public UsuarioPermisoDAO getUsuarioPermisoDAO() {
        return new UsuarioPermisoPostgreSqlDAO(getConnection());
    }

    @Override
    public UsuarioHoraExtraDAO getUsuarioHoraExtraDAO() {
        return new UsuarioHoraExtraPostgreSqlDAO(getConnection());
    }
}