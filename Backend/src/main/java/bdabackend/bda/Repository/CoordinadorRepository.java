package bdabackend.bda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import bdabackend.bda.Entity.CoordinadorEntity;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface CoordinadorRepository extends JpaRepository<CoordinadorEntity, Long> {
        // Crear
        @Transactional
        @Modifying
        @Query(value = "INSERT INTO CoordinadorEntity (nombre, " +
                        "contrasena, correoCoordinador, numeroDocumento) " +
                        "VALUES (:nombre, :contrasena, :correo, :numeroDocumento)")
        public void insertarCoordinador(@Param("nombre") String nombre,
                        @Param("contrasena") String contrasena,
                        @Param("correo") String correo,
                        @Param("numeroDocumento") String numeroDocumento);

        // Leer
        @Query("SELECT v FROM CoordinadorEntity v WHERE v.id = ?1")
        public CoordinadorEntity buscarCoordinadorPorId(Long id);

        @Query("SELECT v FROM CoordinadorEntity v WHERE v.correoCoordinador = ?1")
        public CoordinadorEntity buscarCoordinadorPorCorreo(String correo);

        @Query("SELECT v FROM CoordinadorEntity v")
        public List<CoordinadorEntity> listaCoordinadores();

        @Query("SELECT palabra FROM CoordinadorEntity palabra WHERE"
                        + " CONCAT(palabra.id, palabra.correoCoordinador, palabra.contrasena, " +
                        "palabra.contrasena, palabra.nombre)"
                        + " LIKE %?1%")
        public List<CoordinadorEntity> listaPalabraClave(@Param("palabra") String palabra);

        // Delete
        @Transactional
        @Modifying
        @Query("DELETE FROM CoordinadorEntity v WHERE v.id = :id")
        public void eliminarCoordinadorPorId(@Param("id") Long id);

}
