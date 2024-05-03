package Backend_Voluntarios.Backend.Repository;

import Backend_Voluntarios.Backend.Entity.TareaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<TareaEntity, Long> {

        // Encontrar tareas por Id
        @Query("SELECT t FROM TareaEntity t WHERE t.idTarea = :id")
        TareaEntity findTareaById(@Param("id") Long id);

        // Encontrar todas las tareas
        @Query("SELECT t FROM TareaEntity t")
        List<TareaEntity> findAllTareas();

        @Query("SELECT v FROM TareaEntity v WHERE v.emergencia.idEmergencia = :id")
        public List<TareaEntity> buscarIdTarea(@Param("id") Long id);

        // Guardar
        @Transactional
        @Modifying
        @Query(value = "INSERT INTO tarea (nombre_tarea, descripcion_tarea, tipo_tarea, id_emergencia)" +
                        " VALUES (:nombre_tarea, :descripcion_tarea, :tipo_tarea, :id_emergencia)", nativeQuery = true)
        void saveTarea(@Param("nombre_tarea") String nombreTarea,
                        @Param("descripcion_tarea") String descripcionTarea,
                        @Param("tipo_tarea") String tipoTarea,
                        @Param("id_emergencia") Long emergencia);

        // Crear en pantalla un listado de voluntarios por ranking para una tarea
        // específica

        @Query("SELECT t.nombreTarea, v.nombreVoluntario, r.nivelRanking " +
                        "FROM VoluntarioEntity v, TareaEntity t, RankingEntity r " +
                        "WHERE t.nombreTarea = :nombreTarea AND v.idVoluntario = r.voluntario.idVoluntario AND t.idTarea = r.tarea.idTarea "
                        +
                        "GROUP BY t.nombreTarea, v.nombreVoluntario, r.nivelRanking " +
                        "ORDER BY r.nivelRanking DESC")
        List<TareaEntity> listRankingTarea(@Param("nombreTarea") String nombreTarea);

        TareaEntity save(TareaEntity tareaEntity);
}
