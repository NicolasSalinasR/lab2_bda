package bdabackend.bda.Controller;

import bdabackend.bda.DTO.RegionDto;
import bdabackend.bda.Service.RankingService;
import bdabackend.bda.Service.RegionService;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.io.WKTWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.locationtech.jts.geom.Coordinate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/regiones")
public class RegionController {
    @Autowired
    private RankingService rankingService;
    @Autowired
    private RegionService regionService;

    @GetMapping("/all")
    public List<RegionDto> getRegiones() {
        WKTWriter wktWriter = new WKTWriter();
        List<RegionDto> regionDtos = new ArrayList<>();

        List<?> listaregiones = regionService.tablaRegiones();
        for (Object regionObj : listaregiones) {
            Object[] region = (Object[]) regionObj;

            Long id = ((Number) region[0]).longValue();
            String nombreRegiones = (String) region[1];

            String text = rankingService.bytesToString((byte[]) region[2]);

            MultiPolygon multiPolygon = regionService.convertStringToMultiPolygon(text);

            String geometryWKT = wktWriter.write(multiPolygon);

            RegionDto regionDto = new RegionDto(id, nombreRegiones, geometryWKT);
            regionDtos.add(regionDto);
        }
        return regionDtos;
    }
}
