package bdabackend.bda.Controller;

import bdabackend.bda.Entity.EmergenciaEntity;
import bdabackend.bda.Entity.TareaEntity;
import bdabackend.bda.Service.AuditoriaService;
import bdabackend.bda.Service.EmergenciaService;
import bdabackend.bda.Service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tarea")
@CrossOrigin(origins = "*")
public class TareaController {
    @Autowired
    private TareaService tareaService;
    @Autowired
    private EmergenciaService emergenciaService;
    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping("/{id}")
    public TareaEntity getTareaById(@PathVariable Long id) {
        return tareaService.buscarTareaPorId(id);
    }

    @GetMapping("/all")
    public List<?> getAllTareas() {
        return tareaService.listaTarea();
    }

    @GetMapping("/zona")
    public String zona(@RequestBody Map<String, String> body) {
        Long idTarea = Long.parseLong(body.get("idTarea"));
        TareaEntity tarea = tareaService.buscarTareaPorId(idTarea);
        // VoluntarioEntity voluntario = (VoluntarioEntity)
        // idVoluntariosEncontrados.get(0);
        String nombreTarea = tarea.getNombre();
        // String text = bytesToString((byte[]) nombreTarea[6]);
        // assert text != null;

        // double[] latLong = wkbToLatLong(hexStringToByteArray(text));

        // Imprime las coordenadas x e y
        // return ("Latitud: " + latLong[1] + ", Longitud: " + latLong[0]);
        return "hola";
    }

    @GetMapping("/nombre/{nombreTarea}")
    public List<TareaEntity> getRankingTarea(@PathVariable String nombreTarea) {
        return tareaService.getRankingTarea(nombreTarea);
    }

    @PostMapping("/add")
    public TareaEntity addTarea(@RequestBody Map<String, String> body) {
        String nombreTarea = body.get("nombreTarea");
        String descripcionTarea = body.get("descripcionTarea");
        String tipoTarea = body.get("tipoTarea");
        Long emergencia = Long.parseLong(body.get("emergencia"));
        Point zonaTarea = new Point(Double.parseDouble(body.get("latitud")), Double.parseDouble(body.get("longitud")));
        EmergenciaEntity emergencia1 = emergenciaService.buscarEmergenciaPorId(emergencia);
        TareaEntity tarea = new TareaEntity(nombreTarea, descripcionTarea, tipoTarea, zonaTarea);
        Long idUsuario = 1L;
        // auditoriaService.registrarCambio(idUsuario, "Add", "añadio una tarea");
        tareaService.insertarTarea(nombreTarea, descripcionTarea, tipoTarea, zonaTarea, emergencia);

        // Long idUsuario = //metodo para obtener id de usuario ya listo, esperar a
        // pablo
        // auditoriaService.registrarCambio(idUsuario, "Add", "añadio una tarea");
        return tarea;
    }

    @DeleteMapping("/delete/{id}")
    public void Eliminar(@PathVariable Long id) {
        tareaService.eliminarTareaPorId(id);
    }
}
