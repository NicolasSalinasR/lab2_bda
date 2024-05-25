package bdabackend.bda.Repository;

import org.springframework.data.geo.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import bdabackend.bda.Entity.RankingEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<RankingEntity, Long> {
    // Crear
    // Insertar un nuevo registro en la tabla ranking con los valores de idTarea y
    // idVoluntario
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO ranking (nivel, tarea_ranking, nombre_voluntario, numero_docuemto_voluntario, id_tarea, id_voluntario) "
            +
            "VALUES (:nivel, :tareaRanking, :nombreVoluntario, :numeroDocumentoVoluntario, :idTarea, :idVoluntario)", nativeQuery = true)
    public void insertarRanking(@Param("nivel") int nivel, @Param("tareaRanking") String tareaRanking,
            @Param("nombreVoluntario") String nombreVoluntario,
            @Param("numeroDocumentoVoluntario") String numeroDocumentoVoluntario, @Param("idTarea") Long idTarea,
            @Param("idVoluntario") Long idVoluntario);

    // Crear
    // Insertar un nuevo registro en la tabla ranking sin los valores de idTarea y
    // idVoluntario
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO ranking (nivel, tarea_ranking, nombre_voluntario, numero_docuemto_voluntario) "
            +
            "VALUES (:nivel, :tareaRanking, :nombreVoluntario, :numeroDocumentoVoluntario)", nativeQuery = true)
    public void insertarRankingSinTareaVoluntario(@Param("nivel") String nivel,
            @Param("tareaRanking") String tareaRanking,
            @Param("nombreVoluntario") String nombreVoluntario,
            @Param("numeroDocumentoVoluntario") String numeroDocumentoVoluntario);

    // Leer
    @Query("SELECT v FROM RankingEntity v WHERE v.id = ?1")
    public RankingEntity buscarRankingPorId(Long id);

    @Query("SELECT v FROM RankingEntity v")
    public List<RankingEntity> listaRanking();

    // Delete
    @Transactional
    @Modifying
    @Query("DELETE FROM RankingEntity v WHERE v.id = :id")
    public void eliminarRankingPorId(@Param("id") Long id);

    @Query("SELECT v.zonaEmergencia FROM EmergenciaEntity v WHERE v.id = :id")
    public List<?> sacarZonaEmergencia(@Param("id") Long id);

    @Query("SELECT v.zonaVivienda FROM VoluntarioEntity v WHERE v.id = :id")
    public List<?> sacarZonaVoluntario(@Param("id") Long id);

    @Query("SELECT v.habilidadRequerida FROM TareaHabilidadEntity v WHERE v.habilidadRequerida LIKE CONCAT('%', :equipo, '%')")
    public List<String> matchEquipo(@Param("equipo") String equipo);

    @Query("SELECT COUNT(v) FROM VoluntarioHabilidadEntity v WHERE v.voluntario.idVoluntario=:id")
    public int matchHabilidad(@Param("id") Long id);
}
