package Backend_Voluntarios.Backend.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import Backend_Voluntarios.Backend.Entity.InstitucionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import Backend_Voluntarios.Backend.Repository.EmergenciaRepository;
import Backend_Voluntarios.Backend.Entity.EmergenciaEntity;

@Service
public class EmergenciaService {
    @Autowired
    private EmergenciaRepository emergenciaRepository;

    public EmergenciaEntity getEmergenciaById(Long id) {
        return emergenciaRepository.findEmergenciaById(id);
    }


    public List<EmergenciaEntity> getAllEmergencias() {
        return emergenciaRepository.findAllEmergencias();
    }

    public void addEmergencia(EmergenciaEntity emergencia) {
        emergenciaRepository.saveEmergencia(emergencia.getTipoEmergencia(),
                emergencia.getZonaEmergencia(),
                emergencia.getCondicionFisica(),
                emergencia.getCantidadVoluntariosMinimo(),
                emergencia.getCantidadVoluntariosMaximo(),
                emergencia.getInstitucion().getIdInstitucion());
    }
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addEmergencia(String tipoEmergencia, Double latitud, Double longitud, String condicionFisica,
                              String cantidadVoluntariosMinimo, String cantidadVoluntariosMaximo, InstitucionEntity institucion) {

        // Convierte las coordenadas a un formato adecuado para PostgreSQL, como WKT
        DecimalFormat df = new DecimalFormat("#.######", new DecimalFormatSymbols(Locale.US));
        String zonaViviendaWKT = String.format("POINT(%s %s)", df.format(longitud), df.format(latitud));

        // Ejecuta la consulta SQL parametrizada para insertar el nuevo voluntario
        jdbcTemplate.update("INSERT INTO voluntario (,tipo_emergencia, zona_emergencia, "
                        + "condicion_fisica, cantidad_voluntarios_minimo, cantidad_voluntarios_maximo, "
                        + "institucion) VALUES (?, ST_GeomFromText(?), ?, ?, ?, ?)", tipoEmergencia,
                zonaViviendaWKT, condicionFisica, cantidadVoluntariosMinimo, cantidadVoluntariosMaximo,
                institucion);
    }

}
