package bdabackend.bda.Repository;

import bdabackend.bda.Entity.TareaEntity;
import org.springframework.data.geo.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import bdabackend.bda.Entity.EmergenciaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface EmergenciaRepository extends JpaRepository<EmergenciaEntity, Long> {
    // Crear
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO emergencia (tipo_emergencia, zona_emergencia, condicion_fisica, cantidad_voluntarios_minimo, cantidad_voluntarios_maximo, id_institucion) VALUES (:tipoEmergencia, :zonaEmergencia, :condicionFisica, :cantidadVoluntariosMin, :cantidadVoluntariosMax, :institucion)", nativeQuery = true)
    public void insertarEmergencia(@Param("tipoEmergencia") String tipoEmergencia,
            @Param("zonaEmergencia") Point zonaEmergencia,
            @Param("condicionFisica") String condicionFisica,
            @Param("cantidadVoluntariosMin") int cantidadVoluntariosMin,
            @Param("cantidadVoluntariosMax") int cantidadVoluntariosMax,
            @Param("institucion") Long institucion);

    // Leer
    @Query("SELECT v FROM EmergenciaEntity v WHERE v.id = ?1")
    public List<?> buscarEmergenciaPorId(Long id);

    @Query("SELECT v FROM EmergenciaEntity v")
    public List<?> listaEmergencia();

    @Query("SELECT palabra FROM EmergenciaEntity palabra WHERE"
            + " CONCAT(palabra.tipoEmergencia, palabra.cantidadVoluntariosMax, " +
            "palabra.cantidadVoluntariosMin, palabra.condicionFisica)"
            + " LIKE %?1%")
    public List<EmergenciaEntity> findAll(@Param("palabra") String palabraClave);

    // Delete
    @Transactional
    @Modifying
    @Query("DELETE FROM EmergenciaEntity v WHERE v.id = :id")
    public void eliminarEmergenciaPorId(@Param("id") Long id);
}
