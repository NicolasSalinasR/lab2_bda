package bdabackend.bda.Controller;

import bdabackend.bda.Entity.EstadoTareaEntity;
import bdabackend.bda.Entity.TareaEntity;
import bdabackend.bda.Service.AuditoriaService;
import bdabackend.bda.Service.EstadoTareaService;
import bdabackend.bda.Service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private TareaService tareaService;

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

  //  @GetMapping("/{PalabraClave}")
  //  public ResponseEntity<List<EstadoTareaEntity>> BuscarEstadoTarea(@PathVariable String PalabraClave) {
  //      List<EstadoTareaEntity> estadoTareaEncontradas = estadoTareaService.listAll(PalabraClave);
  //      if (estadoTareaEncontradas.isEmpty()) {
  //          return ResponseEntity.notFound().build();
  //      }
  //      return ResponseEntity.ok(estadoTareaEncontradas);
  //  }

    @PostMapping("/add")
    public EstadoTareaEntity addEstadoTarea(@RequestBody Map<String, String> body) {
        Long idTarea = Long.parseLong(body.get("tarea"));
        Boolean estadoTarea = Boolean.parseBoolean(body.get("estadoTarea"));
        TareaEntity tareaNew = tareaService.buscarTareaPorId(idTarea);

        EstadoTareaEntity estadoTareaNew = new EstadoTareaEntity(estadoTarea, tareaNew);
        //Long idUsuario = 1L;
        //auditoriaService.registrarCambio(idUsuario, "Add", "añadio un estado tarea");
        estadoTareaService.insertarEstadoTarea(idTarea,estadoTarea);
        return estadoTareaNew;

        // Long idUsuario = //metodo para obtener id de usuario ya listo, esperar a
        // pablo
        // auditoriaService.registrarCambio(idUsuario, "Add", "añadio un estado tarea");
    }

    @DeleteMapping("/delete/{id}")
    public void Eliminar(@PathVariable Long id) {
        Long idUsuario = 1L;//metodo para obtener id de usuario ya listo, esperar a
        //auditoriaService.registrarCambio(idUsuario, "delete", "elimino un estadotarea");
        estadoTareaService.eliminarEstadoTareaPorId(id);
        // Long idUsuario = //metodo para obtener id de usuario ya listo, esperar a
        // pablo
        // auditoriaService.registrarCambio(idUsuario, "delete", "elimino un estado
        // tarea");
    }
}
