package bdabackend.bda.Controller;

import bdabackend.bda.Entity.InstitucionEntity;
import bdabackend.bda.Service.AuditoriaService;
import bdabackend.bda.Service.EmergenciaService;
import bdabackend.bda.Service.InstitucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/institucion")
@CrossOrigin(origins = "*")
public class InstutucionController {

    @Autowired
    private InstitucionService institucionService;

    @Autowired
    private EmergenciaService emergenciaService;

    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping("/{id}")
    public InstitucionEntity getInstitucionById(@PathVariable Long id) {
        return institucionService.buscarInstitucionPorId(id);
    }

    @GetMapping("/all")
    public List<InstitucionEntity> getAllInstituciones() {
        return institucionService.listaInstitucion();
    }

    @GetMapping("/palabra/{PalabraClave}")
    public ResponseEntity<List<InstitucionEntity>> Buscar_rankings(@PathVariable String PalabraClave) {
        List<InstitucionEntity> rankings_encontrados = institucionService.listaInstitucion();
        if (rankings_encontrados.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rankings_encontrados);
    }

    @PostMapping("/add")
    public InstitucionEntity addInstitucion(@RequestBody Map<String, String> body) {
        String nombreInstitucion = body.get("nombreInstitucion");

        InstitucionEntity institucion = new InstitucionEntity(nombreInstitucion);
        Long idUsuario = 1L;
        //metodo para obtener id de usuario ya listo, esperar a
        // pablo
        //auditoriaService.registrarCambio(idUsuario, "Add", "añadio una institucion");
        institucionService.insertarInstitucion(nombreInstitucion);


        return institucion;
    }

    @DeleteMapping("/delete/{id}")
    public void Eliminar(@PathVariable Long id) {
        // InstitucionEntity institucionEliminada =
        // institucionService.getInstitucionById(id);
        institucionService.eliminarInstitucionPorId(id);

        // // Long idUsuario = //metodo para obtener id de usuario ya listo, esperar a
        // // pablo
        // // auditoriaService.registrarCambio(idUsuario, "Delete", "eliminio una
        // // institucion");
        // }

    }
}
