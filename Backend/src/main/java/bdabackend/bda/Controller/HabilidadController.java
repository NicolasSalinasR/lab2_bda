package bdabackend.bda.Controller;

import bdabackend.bda.Entity.HabilidadEntity;
import bdabackend.bda.Service.AuditoriaService;
import bdabackend.bda.Service.HabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Habilidad")
@CrossOrigin(origins = "*")
public class HabilidadController {
    @Autowired
    private HabilidadService habilidadService;

    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping("/{id}")
    public HabilidadEntity getHabilidadById(@PathVariable Long id){
        return habilidadService.buscarHabilidadPorId(id);
    }

    @GetMapping("/all")
    public List<HabilidadEntity> getAllHabilidad() {return habilidadService.listaHabilidad();}

    @GetMapping("/palabra/{PalabraClave}")
    public ResponseEntity<List<HabilidadEntity>> BuscarHabilidades(@PathVariable String PalabraClave){
        List<HabilidadEntity> habilidadEncontradas =  habilidadService.listaHabilidad(); //arreglar
        if (habilidadEncontradas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(habilidadEncontradas);
    }


    @PostMapping("/add")
    public HabilidadEntity addHabilidad(@RequestBody Map<String, String> body){
        String nombreHabilidad = body.get("nombreHabilidad");

        HabilidadEntity habilidad = new HabilidadEntity(nombreHabilidad);
        Long idUsuario = 1L;
        //auditoriaService.registrarCambio(idUsuario, "Add", "añadio una Habilidad");
        habilidadService.insertarHabilidad(nombreHabilidad);
        // Long idUsuario = //metodo para obtener id de usuario, esperar a pablo
        //         auditoriaService.registrarCambio(idUsuario, "Add", "añadio una Habilidad");
        return habilidad;
    }
    @DeleteMapping("/delete/{id}")
    public void Eliminar(@PathVariable Long id){
        Long idUsuario = 1L;//metodo para obtener id de usuario ya listo, esperar a pablo
        //auditoriaService.registrarCambio(idUsuario, "Delete", "borro una Habilidad");
        habilidadService.eliminarHabilidadPorId(id);

    }

}
