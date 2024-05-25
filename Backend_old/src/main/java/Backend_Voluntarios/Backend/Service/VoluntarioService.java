package Backend_Voluntarios.Backend.Service;

import Backend_Voluntarios.Backend.Entity.VoluntarioEntity;
import Backend_Voluntarios.Backend.Repository.VoluntarioRepository;
import jakarta.transaction.Transactional;

import org.springframework.data.geo.Point;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

@Service
public class VoluntarioService {

    @Autowired
    private VoluntarioRepository repositoryVoluntario;

    public List<VoluntarioEntity> listaFiltro(String palabraClave) {
        return repositoryVoluntario.findAll(palabraClave);
    }

    public List<?> tablaCompleta() {
        return repositoryVoluntario.listAll();
    }

    public List<?> tablaId(Long idVoluntario) {
        return repositoryVoluntario.buscarIdVoluntario(idVoluntario);
    }

    public VoluntarioEntity borrarVoluntario(VoluntarioEntity voluntarioEntity) {
        return repositoryVoluntario.borrarVoluntario(voluntarioEntity.getIdVoluntario());
    }

    public VoluntarioEntity buscarId(Long idVoluntario) {
        return repositoryVoluntario.idVoluntario(idVoluntario);
    }

    public VoluntarioEntity buscarPorCorreo(String correoVoluntario) {
        return repositoryVoluntario.findByCorreo(correoVoluntario);
    }
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void crearVoluntario(String nombreVoluntario, String correoVoluntario, String numeroDocumentoVoluntario,
                                Double latitud, Double longitud, String contrasenaVoluntario, String equipamientoVoluntario) {

        // Convierte las coordenadas a un formato adecuado para PostgreSQL, como WKT
        DecimalFormat df = new DecimalFormat("#.######", new DecimalFormatSymbols(Locale.US));
        String zonaViviendaWKT = String.format("POINT(%s %s)", df.format(longitud), df.format(latitud));

        // Ejecuta la consulta SQL parametrizada para insertar el nuevo voluntario
        jdbcTemplate.update("INSERT INTO voluntario (nombre_voluntario, correo_voluntario, "
                        + "numero_documento_voluntario, zona_vivienda_voluntario, contrasena_voluntario, "
                        + "equipamiento_voluntario) VALUES (?, ?, ?, ST_GeomFromText(?), ?, ?)", nombreVoluntario,
                correoVoluntario, numeroDocumentoVoluntario, zonaViviendaWKT, contrasenaVoluntario,
                equipamientoVoluntario);
    }

}
