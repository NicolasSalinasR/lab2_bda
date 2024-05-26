package bdabackend.bda.Service;

import bdabackend.bda.Entity.InstitucionEntity;
import bdabackend.bda.Entity.TareaEntity;
import org.springframework.data.geo.Point;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import bdabackend.bda.Entity.EmergenciaEntity;
import bdabackend.bda.Repository.EmergenciaRepository;

@Service
public class EmergenciaService {
    @Autowired
    private EmergenciaRepository emergenciaRepository;

    public void insertarEmergencia(String tipoEmergencia, Point zonaEmergencia, String condicionFisica,
                                   int cantidadVoluntariosMin, int cantidadVoluntariosMax, Long institucion) {
        emergenciaRepository.insertarEmergencia(tipoEmergencia, zonaEmergencia, condicionFisica, cantidadVoluntariosMin,
                cantidadVoluntariosMax, institucion);
    }

    public void eliminarEmergenciaPorId(Long id) {
        emergenciaRepository.eliminarEmergenciaPorId(id);
    }

    public List<?> buscarEmergenciaPorId(Long id) {
        return emergenciaRepository.buscarEmergenciaPorId(id);
    }

    public List<?> listaEmergencia() {
        return emergenciaRepository.listaEmergencia();
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void crearEmergencia(String tipoEmergencia, Double latitud, Double longitud, String condicionFisica, int cantidadVolMin, int cantidadVolMax, Long institucion) {

        // Convierte las coordenadas a un formato adecuado para PostgreSQL, como WKT
        DecimalFormat df = new DecimalFormat("#.######", new DecimalFormatSymbols(Locale.US));
        String zonaViviendaWKT = String.format("POINT(%s %s)", df.format(longitud), df.format(latitud));

        // Ejecuta la consulta SQL parametrizada para insertar el nuevo voluntario
        jdbcTemplate.update("INSERT INTO emergencia (tipo_emergencia, zona_emergencia, "
                        + "condicion_fisica, cantidad_voluntarios_min, cantidad_voluntarios_max, "
                        + "id_institucion) VALUES (?, ST_GeomFromText(?),?, ?, ?, ?)", tipoEmergencia,
                zonaViviendaWKT, condicionFisica, cantidadVolMin,
                cantidadVolMax, institucion);
    }

    public List<EmergenciaEntity> listaFiltro(String palabraClave) {
        return emergenciaRepository.findAll(palabraClave);
    }
}
