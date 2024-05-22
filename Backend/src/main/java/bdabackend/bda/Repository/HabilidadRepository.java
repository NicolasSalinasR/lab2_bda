package bdabackend.bda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import bdabackend.bda.Entity.HabilidadEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface HabilidadRepository extends JpaRepository<HabilidadEntity, Long> {
    // Crear
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO habilidad (nombre) VALUES (:nombre)", nativeQuery = true)
    public void insertarHabilidad(@Param("nombre") String nombre);

    // Leer
    @Query("SELECT v FROM HabilidadEntity v WHERE v.id = ?1")
    public HabilidadEntity buscarHabilidadPorId(Long id);

    @Query("SELECT v FROM HabilidadEntity v")
    public List<HabilidadEntity> listaHabilidad();

    // Delete
    @Transactional
    @Modifying
    @Query("DELETE FROM HabilidadEntity v WHERE v.id = :id")
    public void eliminarHabilidadPorId(@Param("id") Long id);

}
