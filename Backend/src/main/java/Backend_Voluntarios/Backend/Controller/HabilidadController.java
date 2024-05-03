package Backend_Voluntarios.Backend.Controller;


import Backend_Voluntarios.Backend.Entity.HabilidadEntity;
import Backend_Voluntarios.Backend.Service.AuditoriaService;
import Backend_Voluntarios.Backend.Service.HabilidadService;
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
        return habilidadService.findByIds(id);
    }

   @GetMapping("/all")
    public List<HabilidadEntity>getAllHabilidad() {return habilidadService.getAllHabilidades();}

    @GetMapping("/palabra/{PalabraClave}")
    public ResponseEntity<List<HabilidadEntity>> BuscarHabilidades(@PathVariable String PalabraClave){
        List<HabilidadEntity> habilidadEncontradas =  habilidadService.listaHabilidades(PalabraClave);
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
        auditoriaService.registrarCambio(idUsuario, "Add", "añadio una Habilidad");
        habilidadService.crearHabilidad(habilidad);
        // Long idUsuario = //metodo para obtener id de usuario, esperar a pablo
        //         auditoriaService.registrarCambio(idUsuario, "Add", "añadio una Habilidad");
        return habilidad;
    }
    @DeleteMapping("/delete/{id}")
    public void Eliminar(@PathVariable Long id){
        HabilidadEntity habilidadEliminada = habilidadService.findByIds(id);
        Long idUsuario = 1L;//metodo para obtener id de usuario ya listo, esperar a pablo
        auditoriaService.registrarCambio(idUsuario, "Delete", "borro una Habilidad");
        habilidadService.borrarHabilidad(habilidadEliminada);

    }

}
