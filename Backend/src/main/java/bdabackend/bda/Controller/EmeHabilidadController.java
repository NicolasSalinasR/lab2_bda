package bdabackend.bda.Controller;

import bdabackend.bda.Entity.EmergenciaHabilidadEntity;
import bdabackend.bda.Service.AuditoriaService;
import bdabackend.bda.Service.EmergenciaHabilidadSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emergenciaHabilidad")
@CrossOrigin(origins = "*")
public class EmeHabilidadController {
    @Autowired
    private EmergenciaHabilidadSevice emeHabilidadService;
    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping("/{id}")
    public EmergenciaHabilidadEntity getEmeHabilidadById(@PathVariable Long id) {
        return emeHabilidadService.buscarEmergenciaHabilidadPorId(id);
    }

    @GetMapping("/all")
    public List<EmergenciaHabilidadEntity> getAllEmeHabilidades() {
        return emeHabilidadService.listaEmergenciaHabilidad();
    }

    @PostMapping("/add")
    public void addEmeHabilidad(@RequestBody Map<String, String> body) {
        Long idEmergencia = Long.parseLong(body.get("emergencia"));
        Long idHabilidad = Long.parseLong(body.get("habilidad"));
        //EmergenciaEntity emergenciaNew = emergenciaService.buscarEmergenciaPorId(idEmergencia);
        //HabilidadEntity habilidadNew = habilidadService. buscarHabilidadPorId(idHabilidad);
        //EmergenciaHabilidadEntity emeHabilidad = new EmergenciaHabilidadEntity(emergenciaNew, habilidadNew);
        //Long idUsuario = 1L;
        //auditoriaService.registrarCambio(idUsuario, "Add", "añadio una emergencia Habilidad");
        emeHabilidadService.insertarEmergenciaHabilidad(idHabilidad, idEmergencia);
        // Long idUsuario = //metodo para obtener id de usuario ya listo, esperar a
        // pablo
        // auditoriaService.registrarCambio(idUsuario, "Add", "añadio una emergencia
        // Habilidad");
    }

    @DeleteMapping("/delete/{id}")
    public void eliminar(@PathVariable Long id) {
        //Long idUsuario = 2L;//metodo para obtener id de usuario ya listo, esperar a
        // pablo
        //auditoriaService.registrarCambio(idUsuario, "Delete", "elimino unvoluntario");
        emeHabilidadService.eliminarEmergenciaHabilidadPorId(id);
    }
}