package bdabackend.bda.Repository;

import bdabackend.bda.Entity.HabilidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import bdabackend.bda.Entity.EstadoTareaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface EstadoTareaRepository extends JpaRepository<EstadoTareaEntity, Long> {
    // Crear
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO EstadoTareaEntity (idTarea, estadoTarea) " +
            "VALUES (:idTarea, :estadoTarea)", nativeQuery = true)
    public void insertarEstadoTarea(@Param("idTarea") Long idTarea, @Param("estadoTarea") boolean estadoTarea);

    // Leer
    @Query("SELECT v FROM EstadoTareaEntity v WHERE v.id = ?1")
    public EstadoTareaEntity buscarEstadoTareaPorId(Long id);

    // Delete
    @Transactional
    @Modifying
    @Query("DELETE FROM EstadoTareaEntity v WHERE v.id = :id")
    public void eliminarEstadoTareaPorId(@Param("id") Long id);

    @Query("SELECT v FROM EstadoTareaEntity v")
    public List<EstadoTareaEntity> listaEstadoTarea();
}
