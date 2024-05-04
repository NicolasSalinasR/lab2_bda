package Backend_Voluntarios.Backend.Repository;

import Backend_Voluntarios.Backend.Entity.InstitucionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InstitucionRepository extends JpaRepository<InstitucionEntity, Long> {

    // findById
    @Query("SELECT e FROM InstitucionEntity e WHERE e.idInstitucion = :id")
    InstitucionEntity findInstitucionById(@Param("id") Long id);

    // findAll
    @Query("SELECT e FROM InstitucionEntity e")
    List<InstitucionEntity> findAllInstituciones();

    // save
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO InstitucionEntity (nombreInstitucion) VALUES (:nombreInstitucion)")
    void saveInstitucion(@Param("nombreInstitucion") String nombreInstitucion);

    // delete
    // @Query("DELETE FROM InstitucionEntity WHERE InstitucionEntity.idInstitucion=
    // :id")
    // InstitucionEntity deleteInstitucion(@Param("id") Long id);

    // search
    @Query("SELECT palabra FROM InstitucionEntity palabra WHERE"
            + " CONCAT(palabra.idInstitucion, palabra.nombreInstitucion)"
            + " LIKE %?1%")
    public List<InstitucionEntity> findAll(String palabraClave);

    InstitucionEntity save(InstitucionEntity institucionEntity);
}
