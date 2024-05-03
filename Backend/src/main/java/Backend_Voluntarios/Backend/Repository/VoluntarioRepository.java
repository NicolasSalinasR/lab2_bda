package Backend_Voluntarios.Backend.Repository;

import Backend_Voluntarios.Backend.Entity.VoluntarioEntity;
import org.springframework.data.geo.Point;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

@Repository
public interface VoluntarioRepository extends JpaRepository<VoluntarioEntity, Long> {
        @Query("SELECT palabra FROM VoluntarioEntity palabra WHERE"
                        + " CONCAT(palabra.idVoluntario, palabra.nombreVoluntario, palabra.correoVoluntario, " +
                        "palabra.numeroDocumentoVoluntario, palabra.equipamientoVoluntario, " +
                        "palabra.zonaViviendaVoluntario)"
                        + " LIKE %?1%")
        public List<VoluntarioEntity> findAll(@Param("palabra") String palabraClave);

        @Query(value = "SELECT * FROM voluntario ", nativeQuery = true)
        public List<?> listAll();

        @Query(value = "SELECT * FROM voluntario WHERE voluntario.id_voluntario = ?1", nativeQuery = true)
        public List<?> buscarIdVoluntario(@Param("v") Long idVoluntario);

        @Transactional
        @Modifying
        @Query(value = "INSERT INTO voluntario (nombre_voluntario, correo_voluntario, " +
                "numero_documento_voluntario, zona_vivienda_voluntario, contrasena_voluntario, " +
                "equipamiento_voluntario) " +
                "VALUES (:nombre, :correo, :numero, ST_GeomFromText(:zona), :contrasena, :equipamiento)",
                nativeQuery = true)
        void crearVoluntario(
                @Param("nombre") String nombreVoluntario,
                @Param("correo") String correoVoluntario,
                @Param("numero") String numeroDocumentoVoluntario,
                @Param("zona") String zonaViviendaVoluntarioWKT, // WKT en lugar de Point
                @Param("contrasena") String contrasenaVoluntario,
                @Param("equipamiento") String equipamientoVoluntario);
        @Query("DELETE FROM VoluntarioEntity WHERE idVoluntario = :id")
        VoluntarioEntity borrarVoluntario(@Param("id") Long idVoluntario);

        @Query("SELECT v FROM VoluntarioEntity v WHERE v.idVoluntario = ?1")
        VoluntarioEntity idVoluntario(@Param("v") Long idVoluntario);

        @Query("SELECT v FROM VoluntarioEntity v WHERE v.correoVoluntario = ?1")
        VoluntarioEntity findByCorreo(@Param("correo") String correoVoluntario);

        VoluntarioEntity save(VoluntarioEntity voluntarioEntity);
}
