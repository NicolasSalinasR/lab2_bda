package bdabackend.bda.Repository;

import bdabackend.bda.Entity.InstitucionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import bdabackend.bda.Entity.VoluntarioHabilidadEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface VoluntarioHabilidadRepository extends JpaRepository<VoluntarioHabilidadEntity, Long> {
    // Crear
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO voluntario_habilidad (id_voluntario, id_habilidad) " +
            "VALUES (:idVoluntario, :idHabilidad)", nativeQuery = true)
    public void insertarVoluntarioHabilidad(@Param("idVoluntario") Long idVoluntario,
            @Param("idHabilidad") Long idHabilidad);

    // Leer
    @Query("SELECT v FROM VoluntarioHabilidadEntity v WHERE v.id = ?1")
    public VoluntarioHabilidadEntity buscarVoluntarioHabilidadPorId(Long id);

    // Delete
    @Transactional
    @Modifying
    @Query("DELETE FROM VoluntarioHabilidadEntity v WHERE v.id = :id")
    public void eliminarVoluntarioHabilidadPorId(@Param("id") Long id);

    @Query("SELECT v FROM InstitucionEntity v")
    public List<VoluntarioHabilidadEntity> listaVoluntarioHabilidad();

}
