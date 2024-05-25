package Backend_Voluntarios.Backend.Repository;

import Backend_Voluntarios.Backend.Entity.HabilidadEntity;
import Backend_Voluntarios.Backend.Entity.VoluntarioEntity;
import Backend_Voluntarios.Backend.Entity.VoluntarioHabilidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface VoluntarioHabilidadRepository extends JpaRepository<VoluntarioHabilidadEntity, Long> {
    // findById
    @Query("SELECT vh FROM VoluntarioHabilidadEntity vh WHERE vh.idVoluntarioHabilidad = :id")
    VoluntarioHabilidadEntity findVoluntarioHabilidadById(@Param("id") Long id);

    // findAll
    @Query("SELECT vh FROM VoluntarioHabilidadEntity vh")
    List<VoluntarioHabilidadEntity> findAllVoluntarioHabilidades();

    // save
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO voluntario_habilidad (id_voluntario, id_habilidad) " +
            "VALUES (:id_voluntario, :id_habilidad)", nativeQuery = true)
    void saveVoluntarioHabilidad(@Param("id_voluntario") Long voluntario,
            @Param("id_habilidad") Long habilidad);

    VoluntarioHabilidadEntity save(VoluntarioHabilidadEntity voluntarioHabilidadEntity);
}