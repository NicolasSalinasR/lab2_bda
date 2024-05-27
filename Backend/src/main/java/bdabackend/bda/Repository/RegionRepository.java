package bdabackend.bda.Repository;

import bdabackend.bda.Entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Long> {

    @Query("SELECT r FROM RegionEntity r WHERE r.nombre = :nombreRegion")
    RegionEntity findByNombre(String nombreRegion);

    @Query(value = "SELECT * FROM regiones_chile", nativeQuery = true)
    List<?> findAllRegiones();
}
