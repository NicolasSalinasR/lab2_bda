package bdabackend.bda.Controller;

import bdabackend.bda.Entity.EstadoTareaEntity;
import bdabackend.bda.Service.AuditoriaService;
import bdabackend.bda.Service.EstadoTareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/EstadoTarea")
@CrossOrigin(origins = "*")
public class EstadoTareaController {

    @Autowired
    private EstadoTareaService estadoTareaService;
    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping("/{id}")
    public EstadoTareaEntity getEstadoTareaById(@PathVariable Long id) {
        return estadoTareaService.buscarEstadoTareaPorId(id);
    }

    @GetMapping("/all")
    public List<EstadoTareaEntity> getAllEstadoTarea() {
        return estadoTareaService.listaHabilidad();
    }

    @GetMapping("/Tarea/{id}")
    public EstadoTareaEntity getEstadoTareaIdTarea(@PathVariable Long id) {
        return estadoTareaService.buscarEstadoTareaPorId(id);
    }

    @PostMapping("/add")
    public void addEstadoTarea(@RequestBody Map<String, String> body) {
        Long idTarea = Long.parseLong(body.get("tarea"));
        Boolean estadoTarea = Boolean.parseBoolean(body.get("estadoTarea"));
        //TareaEntity tareaNew = tareaService.buscarTareaPorId(idTarea);
        //EstadoTareaEntity estadoTareaNew = new EstadoTareaEntity(estadoTarea, tareaNew);
        //Long idUsuario = 1L;
        //auditoriaService.registrarCambio(idUsuario, "Add", "añadio un estado tarea");
        estadoTareaService.insertarEstadoTarea(idTarea,estadoTarea);
        // Long idUsuario = //metodo para obtener id de usuario ya listo, esperar a
        // pablo
        // auditoriaService.registrarCambio(idUsuario, "Add", "añadio un estado tarea");
    }

    @DeleteMapping("/delete/{id}")
    public void Eliminar(@PathVariable Long id) {
        //Long idUsuario = 1L;//metodo para obtener id de usuario ya listo, esperar a
        //auditoriaService.registrarCambio(idUsuario, "delete", "elimino un estadotarea");
        estadoTareaService.eliminarEstadoTareaPorId(id);
        // Long idUsuario = //metodo para obtener id de usuario ya listo, esperar a
        // pablo
        // auditoriaService.registrarCambio(idUsuario, "delete", "elimino un estado
        // tarea");
    }
}
