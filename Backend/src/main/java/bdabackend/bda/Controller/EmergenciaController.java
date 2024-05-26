package bdabackend.bda.Controller;

import bdabackend.bda.Entity.EmergenciaEntity;
import bdabackend.bda.Service.AuditoriaService;
import bdabackend.bda.Service.EmergenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emergencia")
@CrossOrigin(origins = "*")
public class EmergenciaController {
    @Autowired
    private EmergenciaService emergenciaService;

    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping("/palabra/{palabraClave}")
    public ResponseEntity<List<EmergenciaEntity>> buscarVoluntarios(@PathVariable String palabraClave) {
        List<EmergenciaEntity> emergenciaEntities = emergenciaService.listaFiltro(palabraClave);
        if (emergenciaEntities.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(emergenciaEntities);
    }

    @GetMapping("/{id}")
    public List<?> getEmergenciaById(@PathVariable Long id) {
        return emergenciaService.buscarEmergenciaPorId(id);
    }

    @GetMapping("/all")
    public List<?> getAllEmergencias() {
        return emergenciaService.listaEmergencia();
    }

    @PostMapping("/add")
    public void addEmergencia(@RequestBody Map<String, String> body) {
        String tipoEmergencia = body.get("tipoEmergencia");
        String condicionFisica = body.get("condicionFisica");
        String cantidadVoluntariosMinimo = body.get("cantidadVoluntariosMinimo");
        String cantidadVoluntariosMaximo = body.get("cantidadVoluntariosMaximo");
        Long idInstitucion = Long.parseLong(body.get("idInstitucion"));
        Double latitud = Double.parseDouble(body.get("latitud"));
        Double longitud = Double.parseDouble(body.get("longitud"));
        int cantidadVoluntariosMinimo2 = Integer.parseInt( cantidadVoluntariosMinimo);
        int cantidadVoluntariosMaximo2 = Integer.parseInt(cantidadVoluntariosMaximo );
        //InstitucionEntity institucion = institucionService.buscarInstitucionPorId(idInstitucion);
        //Long idUsuario = 1L;
        //auditoriaService.registrarCambio(idUsuario, "Add", "añadio una emergencia");
        emergenciaService.crearEmergencia(tipoEmergencia, latitud, longitud, condicionFisica,
                cantidadVoluntariosMinimo2, cantidadVoluntariosMaximo2, idInstitucion);
        // Long idUsuario = //metodo para obtener id de usuario ya listo, esperar a
        // pablo
        // auditoriaService.registrarCambio(idUsuario, "Add", "añadio una emergencia");
        // ! Se debe cambiar al terminar el front por seguridad de que no devuelva
        // ! datos, solo debe devolver una respuesta de que se guardo correctamente
    }

    @DeleteMapping("delete/{id}")
    public void Eliminar(@PathVariable Long id){
        emergenciaService.eliminarEmergenciaPorId(id);
    }

}
