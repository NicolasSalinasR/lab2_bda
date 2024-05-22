package bdabackend.bda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import bdabackend.bda.Entity.EmergenciaHabilidadEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface EmergenciaHabilidadRepository extends JpaRepository<EmergenciaHabilidadEntity, Long> {
        // Crear
        @Transactional
        @Modifying
        @Query(value = "INSERT INTO eme_habilidad (id_habilidad, id_emergencia)" +
                        " VALUES (:id_habilidad, :id_emergencia)", nativeQuery = true)
        public void insertarEmergenciaHabilidad(@Param("id_habilidad") Long id_habilidad,
                        @Param("id_emergencia") Long id_emergencia);

        // Leer
        @Query("SELECT v FROM EmergenciaHabilidadEntity v WHERE v.id = ?1")
        public EmergenciaHabilidadEntity buscarEmergenciaHabilidadPorId(Long id);

        @Query("SELECT v FROM EmergenciaHabilidadEntity v")
        public List<EmergenciaHabilidadEntity> listaEmergenciaHabilidad();

        // Delete
        @Transactional
        @Modifying
        @Query("DELETE FROM EmergenciaHabilidadEntity v WHERE v.id = :id")
        public void eliminarEmergenciaHabilidadPorId(@Param("id") Long id);
}
