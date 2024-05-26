package bdabackend.bda.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import bdabackend.bda.Entity.EmergenciaEntity;
import bdabackend.bda.Entity.VoluntarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import bdabackend.bda.Entity.TareaEntity;
import bdabackend.bda.Repository.TareaRepository;

@Service
public class TareaService {
    @Autowired
    private TareaRepository tareaRepository;

    public void insertarTarea(String nombre, String descripcion, String tipo, Point zona, Long idEmergencia) {
        tareaRepository.insertarTarea(nombre, descripcion, tipo, zona, idEmergencia);
    }

    public void insertarTareaSinEmergencia(String nombre, String descripcion, String tipo, Point zona) {
        tareaRepository.insertarTareaSinEmergencia(nombre, descripcion, tipo, zona);
    }

    public void eliminarTareaPorId(Long id) {
        tareaRepository.eliminarTareaPorId(id);
    }

    public List<?> buscarTareaPorId(Long id) {
        return tareaRepository.buscarTareaPorId(id);
    }

    public List<?> listaTarea() {
        return tareaRepository.listaTarea();
    }

    public List<TareaEntity> getRankingTarea(String nombreTarea) {
        return tareaRepository.listRankingTarea(nombreTarea);
    }

    public List<TareaEntity> tablaTareas(Long id) {
        return tareaRepository.tablaTareas(id);
    }

    public List<TareaEntity> getRankingTarea(String nombreTarea) {
        return tareaRepository.listRankingTarea(nombreTarea);
    }
    public List<TareaEntity> listaFiltro(String palabraClave) {
        return tareaRepository.findAll(palabraClave);
    }
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void crearTarea(String nombre, String descripcion, String tipo,
                                Double latitud, Double longitud, Long idEmergencia) {

        // Convierte las coordenadas a un formato adecuado para PostgreSQL, como WKT
        DecimalFormat df = new DecimalFormat("#.######", new DecimalFormatSymbols(Locale.US));
        String zonaViviendaWKT = String.format("POINT(%s %s)", df.format(longitud), df.format(latitud));

        // Ejecuta la consulta SQL parametrizada para insertar el nuevo voluntario
        jdbcTemplate.update("INSERT INTO tarea (nombre, descripcion, "
                        + "tipo, zona, id_emergencia) VALUES (?, ?, ?, ST_GeomFromText(?), ?)", nombre,
                descripcion, tipo, zonaViviendaWKT, idEmergencia);
    }

    public List<?> tareaEmerg (Long id){
        return tareaRepository.tareasPorEmergencia(id);
    }

    public String nombre(Long id){
        return tareaRepository.nombre(id);
    }

    public List<?> tareasPorRegion(String nombreRegion){
        return tareaRepository.obtenerTareasPorRegion(nombreRegion);
    }
}
