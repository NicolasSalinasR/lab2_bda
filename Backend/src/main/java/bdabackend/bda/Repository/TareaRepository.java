package bdabackend.bda.Repository;

import bdabackend.bda.Entity.VoluntarioEntity;
import org.springframework.data.geo.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import bdabackend.bda.Entity.TareaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<TareaEntity, Long> {
        // Crear
        // Se crea una tarea con el id de la emergencia
        @Transactional
        @Modifying
        @Query(value = "INSERT INTO tarea (nombre, descripcion, tipo, zona, id_emergencia) VALUES (:nombre, :descripcion, :tipo, :zona, :idEmergencia)", nativeQuery = true)
        public void insertarTarea(@Param("nombre") String nombre, @Param("descripcion") String descripcion,
                        @Param("tipo") String tipo, @Param("zona") Point zona,
                        @Param("idEmergencia") Long idEmergencia);

        // Crear
        // Se crea una tarea sin el id de la emergencia
        @Transactional
        @Modifying
        @Query(value = "INSERT INTO tarea (nombre, descripcion, tipo, zona) VALUES (:nombre, :descripcion, :tipo, :zona)", nativeQuery = true)
        public void insertarTareaSinEmergencia(@Param("nombre") String nombre, @Param("descripcion") String descripcion,
                        @Param("tipo") String tipo, @Param("zona") Point zona);

        // Leer
        @Query(value = "SELECT * FROM tarea WHERE tarea.id = ?1", nativeQuery = true)
        public List<?> buscarTareaPorId(@Param("v") Long id);

        @Query(value = "SELECT * FROM tarea", nativeQuery = true)
        public List<?> listaTarea();

        // Delete
        @Transactional
        @Modifying
        @Query("DELETE FROM TareaEntity v WHERE v.id = :id")
        public void eliminarTareaPorId(@Param("id") Long id);

        @Query("SELECT v FROM TareaEntity v WHERE v.emergencia = :id")
        public List<TareaEntity> tablaTareas(@Param("id") Long id);

        @Query("SELECT palabra FROM TareaEntity palabra WHERE"
                + " CONCAT(palabra.nombre, palabra.descripcion, " +
                "palabra.tipo)"
                + " LIKE %?1%")
        public List<TareaEntity> findAll(@Param("palabra") String palabraClave);


        @Query("SELECT t.nombre, v.nombre, r.nivel " +
                "FROM VoluntarioEntity v, TareaEntity t, RankingEntity r " +
                "WHERE t.nombre = :nombreTarea AND v.id = r.voluntario.id AND t.id = r.tarea.id "
                +
                "GROUP BY t.nombre, v.nombre, r.nivel " +
                "ORDER BY r.nivel DESC")
        List<TareaEntity> listRankingTarea(@Param("nombreTarea") String nombreTarea);

        @Query(value = "SELECT * FROM tarea WHERE tarea.id_emergencia =?1", nativeQuery = true)
        public  List<?> tareasPorEmergencia (@Param("v") Long id);

        @Query("SELECT v.nombre FROM TareaEntity v WHERE v.id = :id")
        public String nombre (@Param("id") Long id);
}

