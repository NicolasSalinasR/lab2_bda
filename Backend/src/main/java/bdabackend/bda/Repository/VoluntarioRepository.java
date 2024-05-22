package bdabackend.bda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import bdabackend.bda.Entity.VoluntarioEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface VoluntarioRepository extends JpaRepository<VoluntarioEntity, Long> {
        // Crear
        @Transactional
        @Modifying
        @Query(value = "INSERT INTO voluntario (nombre, correo, numero_documento, zona_vivienda, contrasena, equipamiento) "
                        +
                        "VALUES (:nombre, :correo, :numeroDocumento, :zonaVivienda, :contrasena, :equipamiento)", nativeQuery = true)
        public void insertarVoluntario(@Param("nombre") String nombre, @Param("correo") String correo,
                        @Param("numeroDocumento") String numeroDocumento, @Param("zonaVivienda") String zonaVivienda,
                        @Param("contrasena") String contrasena, @Param("equipamiento") String equipamiento);

        // Leer
        @Query("SELECT v FROM VoluntarioEntity v WHERE v.id = ?1")
        public VoluntarioEntity buscarVoluntarioPorId(Long id);

        @Query("SELECT v FROM VoluntarioEntity v WHERE v.correo = ?1")
        public VoluntarioEntity buscarPorCorreo(String correo);

        @Query("SELECT v FROM VoluntarioEntity v")
        public List<VoluntarioEntity> listaVoluntario();

        // Delete
        @Transactional
        @Modifying
        @Query("DELETE FROM VoluntarioEntity v WHERE v.id = :id")
        public void eliminarVoluntarioPorId(@Param("id") Long id);

}
