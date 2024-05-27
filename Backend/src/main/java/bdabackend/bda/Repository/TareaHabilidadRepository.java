package bdabackend.bda.Repository;

import bdabackend.bda.Entity.InstitucionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import bdabackend.bda.Entity.TareaHabilidadEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface TareaHabilidadRepository extends JpaRepository<TareaHabilidadEntity, Long> {
        // Crear
        // Se crea una tarea con el id de la emergencia y el id de la tarea
        @Transactional
        @Modifying
        @Query(value = "INSERT INTO tarea_habilidad (hablididad_requerida, id_tarea, id_emergencia_habilidad) " +
                        "VALUES (:habilidadRequerida, :idTarea, :id)", nativeQuery = true)
        public void insertarTareaHabilidad(@Param("habilidadRequerida") String habilidadRequerida,
                        @Param("idTarea") Long idTarea, @Param("id") Long id);

        // Crear
        // Se crea una tarea sin el id de la emergencia y el id de la tarea
        @Transactional
        @Modifying
        @Query(value = "INSERT INTO tarea_habilidad (habilidad_requerida) VALUES (:habilidadRequerida)", nativeQuery = true)
        public void insertarTareaHabilidadSinTareaEmergencia(@Param("habilidadRequerida") String habilidadRequerida);

        // Leer
        @Query("SELECT v FROM TareaHabilidadEntity v WHERE v.id = ?1")
        public TareaHabilidadEntity buscarTareaHabilidadPorId(Long id);

        @Query("SELECT v FROM TareaHabilidadEntity v")
        public List<TareaHabilidadEntity> listaTareaHabilidad();

        // Delete
        @Transactional
        @Modifying
        @Query("DELETE FROM TareaHabilidadEntity v WHERE v.id = :id")
        public void eliminarTareaHabilidadPorId(@Param("id") Long id);
}
