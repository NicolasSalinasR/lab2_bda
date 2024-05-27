package bdabackend.bda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bdabackend.bda.Entity.RegionEntity;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Integer> {

    @Query("SELECT r FROM RegionEntity r")
    List<RegionEntity> findAllRegions();
}
