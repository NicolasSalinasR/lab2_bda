package bdabackend.bda.Controller;

import bdabackend.bda.Service.AuditoriaService;
import bdabackend.bda.Service.InstitucionService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private AuditoriaService auditoriaService;

    @GetMapping("/{id}")
    public List<?> getInstitucionById(@PathVariable Long id) {
        return institucionService.buscarInstitucionPorId(id);
    }

    @GetMapping("/all")
    public List<?> getAllInstituciones() {
        return institucionService.listaInstitucion();
    }

    @PostMapping("/add")
    public void addInstitucion(@RequestBody Map<String, String> body) {
        String nombreInstitucion = body.get("nombreInstitucion");
        // InstitucionEntity institucion = new InstitucionEntity(nombreInstitucion);
        // Long idUsuario = 1L;
        // metodo para obtener id de usuario ya listo, esperar a
        // pablo
        // auditoriaService.registrarCambio(idUsuario, "Add", "añadio una institucion");
        institucionService.insertarInstitucion(nombreInstitucion);
    }

    @DeleteMapping("/delete/{id}")
    public void Eliminar(@PathVariable Long id) {
        institucionService.eliminarInstitucionPorId(id);
    }
}
