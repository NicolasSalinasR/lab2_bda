package bdabackend.bda.Service;

import bdabackend.bda.Entity.RegionEntity;
import bdabackend.bda.Entity.TareaEntity;
import bdabackend.bda.Repository.RegionRepository;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKBReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RegionService {
    @Autowired
    RegionRepository regionRepository;
    public List<?> tablaRegiones(){
        return regionRepository.findAllRegiones();
    }

    public RegionEntity nombre(String nombre) {
        return regionRepository.findByNombre(nombre);
    }

    public MultiPolygon convertBytesToMultiPolygon(byte[] geomBytes) {
        WKBReader reader = new WKBReader();
        try {
            Geometry geometry = reader.read(geomBytes);
            if (geometry instanceof MultiPolygon) {
                return (MultiPolygon) geometry;
            } else {
                throw new IllegalArgumentException("La geometría no es multipoligonal");
            }
        } catch (ParseException e) {
            throw new RuntimeException("Error al convertir byte array a MultiPolygon", e);
        }
    }

    public MultiPolygon convertStringToMultiPolygon(String geomString) {
        // Convertir la cadena WKB a un arreglo de bytes
        byte[] geomBytes = hexStringToByteArray(geomString);

        // Crear un lector de WKB
        WKBReader reader = new WKBReader();
        try {
            // Convertir la geometría WKB en un objeto MultiPolygon
            Geometry geometry = reader.read(geomBytes);
            if (geometry instanceof MultiPolygon) {
                return (MultiPolygon) geometry;
            } else {
                throw new IllegalArgumentException("La geometría no es multipoligonal");
            }
        } catch (ParseException e) {
            throw new RuntimeException("Error al convertir cadena a MultiPolygon", e);
        }
    }

    // Función auxiliar para convertir una cadena hexadecimal a un arreglo de bytes
    private static byte[] hexStringToByteArray(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i+1), 16));
        }
        return data;
    }
}
