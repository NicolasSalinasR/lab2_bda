package bdabackend.bda.Controller;

import bdabackend.bda.DTO.RegionDTO;
import bdabackend.bda.Repository.RegionRepository;
import org.locationtech.jts.io.WKTWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/regiones")
public class RegionController {

    @Autowired
    private RegionRepository regionRepository;

    @GetMapping
    public List<RegionDTO> getRegiones() {
        WKTWriter wktWriter = new WKTWriter();
        return regionRepository.findAllRegions().stream()
                .map(region -> new RegionDTO(region.getId(), region.getNombre(),
                        wktWriter.write(region.getGeometria())))
                .collect(Collectors.toList());
    }
}
