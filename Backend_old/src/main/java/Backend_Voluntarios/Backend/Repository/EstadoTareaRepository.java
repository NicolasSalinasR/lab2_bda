package Backend_Voluntarios.Backend.Repository;

import Backend_Voluntarios.Backend.Entity.EstadoTareaEntity;
import Backend_Voluntarios.Backend.Entity.HabilidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EstadoTareaRepository extends JpaRepository<EstadoTareaEntity, Long> {
        @Query("SELECT palabra FROM EstadoTareaEntity palabra WHERE"
                        + " CONCAT(palabra.idEstadoTarea, palabra.estadoTarea)"
                        + " LIKE %?1%")
        public List<EstadoTareaEntity> listAll(String palabraClave);

        @Query("SELECT v FROM EstadoTareaEntity v WHERE v.idEstadoTarea = :idEstadoTarea")
        EstadoTareaEntity encontrarId(@Param("idEstadoTarea") Long idEstadoTarea);

        @Query("SELECT v FROM EstadoTareaEntity v  WHERE v.tarea.idTarea = :idTarea")
        EstadoTareaEntity findByIdTarea(@Param("idTarea") Long idTarea);

        @Query(value = "SELECT idEstadoTarea FROM EstadoTareaEntity  WHERE EstadoTareaEntity.estadoTarea = FALSE", nativeQuery = true)
        List<Long> findTareasSinTerminar();

        @Query(value = "SELECT idEstadoTarea FROM EstadoTareaEntity  WHERE EstadoTareaEntity.estadoTarea = TRUE", nativeQuery = true)
        List<Long> findTareasTerminadas();

        @Query(value = "DELETE FROM EstadoTareaEntity WHERE EstadoTareaEntity.idEstadoTarea = :idEstadoTarea", nativeQuery = true)
        EstadoTareaEntity borrarEstadoTarea(@Param("idEstadoTarea") Long idEstadoTarea);

        @Transactional
        @Modifying
        @Query(value = "INSERT INTO EstadoTareaEntity (idTarea, estadoTarea) " +
                        "VALUES (:idTarea, :estadoTarea)", nativeQuery = true)
        void guardarEstadoTarea(@Param("idTarea") Long idTarea,
                        @Param("estadoTarea") Boolean estadoTarea);

        @Query(value = "SELECT v FROM EstadoTareaEntity v")
        List<EstadoTareaEntity> findAlls();

        EstadoTareaEntity save(EstadoTareaEntity estadoTareaEntity);
}
