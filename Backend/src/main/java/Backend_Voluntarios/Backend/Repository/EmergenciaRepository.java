package Backend_Voluntarios.Backend.Repository;

import Backend_Voluntarios.Backend.Entity.EmergenciaEntity;
import Backend_Voluntarios.Backend.Entity.InstitucionEntity;

import org.springframework.data.geo.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmergenciaRepository extends JpaRepository<EmergenciaEntity, Long> {
    // findById
    @Query("SELECT e FROM EmergenciaEntity e WHERE e.idEmergencia = :id")
    EmergenciaEntity findEmergenciaById(@Param("id") Long id);

    // findAll
    @Query("SELECT e FROM EmergenciaEntity e")
    List<EmergenciaEntity> findAllEmergencias();


    // save
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO emergencia (tipo_emergencia, zona_emergencia, condicion_fisica, cantidad_voluntarios_minimo, cantidad_voluntarios_maximo, id_institucion) VALUES (:tipoEmergencia, :zonaEmergencia, :condicionFisica, :cantidadVoluntariosMinimo, :cantidadVoluntariosMaximo, :institucion)", nativeQuery = true)
    void saveEmergencia(@Param("tipoEmergencia") String tipoEmergencia,
            @Param("zonaEmergencia") Point zonaEmergencia,
            @Param("condicionFisica") String condicionFisica,
            @Param("cantidadVoluntariosMinimo") String cantidadVoluntariosMinimo,
            @Param("cantidadVoluntariosMaximo") String cantidadVoluntariosMaximo,
            @Param("institucion") Long institucion);

    @Query("SELECT e.zonaEmergencia FROM EmergenciaEntity e WHERE e.idEmergencia = :id")
    Point findZonaEmergenciaId(Long id);

    EmergenciaEntity save(EmergenciaEntity emergenciaEntity);
}
