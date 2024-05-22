package Backend_Voluntarios.Backend.Repository;

import Backend_Voluntarios.Backend.Entity.HabilidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface HabilidadRepository extends JpaRepository<HabilidadEntity, Long> {

        @Query("SELECT palabra FROM HabilidadEntity palabra WHERE"
                        + " CONCAT(palabra.idHabilidad, palabra.nombreHabilidad)"
                        + " LIKE %?1%")
        public List<HabilidadEntity> findAll(String palabraClave);

        @Query(value = "SELECT v FROM HabilidadEntity v WHERE v.idHabilidad = :idHabilidad")
        HabilidadEntity encontrarPorId(@Param("idHabilidad") Long idHabilidad);

        @Query(value = "SELECT nombreHabilidad FROM HabilidadEntity WHERE HabilidadEntity.idHabilidad = :idHabilidad", nativeQuery = true)
        String findNombreHabilidad(@Param("idHabilidad") Long idHabilidad);

        @Transactional
        @Modifying
        @Query(value = "INSERT INTO HabilidadEntity ( nombreHabilidad) " +
                        "VALUES (:nombreHabilidad)")
        void crearHabilidad(@Param("nombreHabilidad") String nombreHabilidad);

        @Query(value = "DELETE FROM HabilidadEntity WHERE HabilidadEntity.idHabilidad = :idHabilidad", nativeQuery = true)
        HabilidadEntity borrarHabilidad(@Param("idHabilidad") Long idHabilidad);

        @Query(value = "SELECT v FROM HabilidadEntity v")
        List<HabilidadEntity> findtodos();

        HabilidadEntity save(HabilidadEntity habilidadEntity);

}
