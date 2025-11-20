package co.edu.uco.HumanSolution.data.dao;

import co.edu.uco.HumanSolution.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RolDAO extends JpaRepository<RolEntity, UUID> {

    void create(RolEntity entity);

    List<RolEntity> read(RolEntity entity);

    void update(RolEntity entity);

    void delete(UUID id);

    /**
     * Verifica si existe un rol con el nombre dado
     * @param nombre Nombre del rol a verificar
     * @return true si existe, false si no
     */
    boolean existsByNombre(String nombre);

}