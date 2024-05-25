package bdabackend.bda.Controller;

import bdabackend.bda.Entity.EmergenciaEntity;
import bdabackend.bda.Entity.EmergenciaHabilidadEntity;
import bdabackend.bda.Entity.HabilidadEntity;
import bdabackend.bda.Service.AuditoriaService;
import bdabackend.bda.Service.EmergenciaHabilidadSevice;
import bdabackend.bda.Service.EmergenciaService;
import bdabackend.bda.Service.HabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private EmergenciaService emergenciaService;

    @Autowired
    private HabilidadService habilidadService;

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

 //   @GetMapping("/palabra/{PalabraClave}")
 //   public ResponseEntity<List<EmergenciaHabilidadEntity>> Buscar_rankings(@PathVariable String PalabraClave) {
 //       List<EmergenciaHabilidadEntity> rankings_encontrados = emeHabilidadService.listaFiltro(PalabraClave);
 //       if (rankings_encontrados.isEmpty()) {
  //          return ResponseEntity.notFound().build();
  //      }
  //      return ResponseEntity.ok(rankings_encontrados);
  //  }

    @PostMapping("/add")
    public EmergenciaHabilidadEntity addEmeHabilidad(@RequestBody Map<String, String> body) {
        Long idEmergencia = Long.parseLong(body.get("emergencia"));
        Long idHabilidad = Long.parseLong(body.get("habilidad"));
        EmergenciaEntity emergenciaNew = emergenciaService.buscarEmergenciaPorId(idEmergencia);
        HabilidadEntity habilidadNew = habilidadService. buscarHabilidadPorId(idHabilidad);
        EmergenciaHabilidadEntity emeHabilidad = new EmergenciaHabilidadEntity(emergenciaNew, habilidadNew);
        Long idUsuario = 1L;
        //auditoriaService.registrarCambio(idUsuario, "Add", "añadio una emergencia Habilidad");
        emeHabilidadService.insertarEmergenciaHabilidad(idHabilidad, idEmergencia);
        // Long idUsuario = //metodo para obtener id de usuario ya listo, esperar a
        // pablo
        // auditoriaService.registrarCambio(idUsuario, "Add", "añadio una emergencia
        // Habilidad");
        return emeHabilidad;
    }

    // @DeleteMapping("delete/{id}")
    // public void Eliminar(@PathVariable Long id) {
    // EmeHabilidadEntity emeHabilidadEliminada =
    // emeHabilidadService.getEmeHabilidadById(id);
    // emeHabilidadService.deleteEmeHabilidad(emeHabilidadEliminada);
    // // Long idUsuario = //metodo para obtener id de usuario ya listo, esperar a
    // pablo
    // // auditoriaService.registrarCambio(idUsuario, "delete", "borro una
    // emergencia Habilidad");
    // }


}
