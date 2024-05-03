package Backend_Voluntarios.Backend.Repository;

import Backend_Voluntarios.Backend.Entity.EmeHabilidadEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmeHabilidadRepository extends JpaRepository<EmeHabilidadEntity, Long> {

        // findById
        @Query("SELECT e FROM EmeHabilidadEntity e WHERE e.idEmergenciaHabilidad = :id")
        EmeHabilidadEntity findEmeHabilidadById(@Param("id") Long id);

        // findAll
        @Query("SELECT e FROM EmeHabilidadEntity e")
        List<EmeHabilidadEntity> findAllEmeHabilidades();

        // save
        @Transactional
        @Modifying
        @Query(value = "INSERT INTO eme_habilidad (id_habilidad, id_emergencia)" +
                        " VALUES (:id_habilidad, :id_emergencia)", nativeQuery = true)
        void saveEmeHabilidad(@Param("id_habilidad") Long habilidad,
                        @Param("id_emergencia") Long emergencia);

        // delete
        // @Query("DELETE FROM EmeHabilidadEntity WHERE
        // EmeHabilidadEntity.idEmergenciaHabilidad= :id")
        // EmeHabilidadEntity deleteEmeHabilidad(@Param("id") Long id);

        // search
        @Query("SELECT palabra FROM EmeHabilidadEntity palabra WHERE"
                        + " CONCAT(palabra.idEmergenciaHabilidad, palabra.habilidad.idHabilidad, palabra.emergencia.idEmergencia)"
                        + " LIKE %?1%")
        public List<EmeHabilidadEntity> findAll(String palabraClave);

        EmeHabilidadEntity save(EmeHabilidadEntity emeHabilidadEntity);

}
