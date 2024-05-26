package bdabackend.bda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import bdabackend.bda.Entity.InstitucionEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface InstitucionRepository extends JpaRepository<InstitucionEntity, Long> {
    // Crear
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO institucion (nombre) VALUES (:nombre)", nativeQuery = true)
    public void insertarInstitucion(@Param("nombre") String nombre);

    // Leer
    @Query(value = "SELECT * FROM institucion WHERE institucion.id = ?1", nativeQuery = true)
    public List<?> buscarInstitucionPorId(@Param("v") Long id);

    @Query(value = "SELECT * FROM institucion", nativeQuery = true)
    public List<?> listaInstitucion();

    // Delete
    @Transactional
    @Modifying
    @Query("DELETE FROM InstitucionEntity v WHERE v.id = :id")
    public void eliminarInstitucionPorId(@Param("id") Long id);
}