package bdabackend.bda.Controller;

import bdabackend.bda.Entity.EmergenciaEntity;
import bdabackend.bda.Entity.InstitucionEntity;
import bdabackend.bda.Service.AuditoriaService;
import bdabackend.bda.Service.EmergenciaService;
import bdabackend.bda.Service.InstitucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
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
    private InstitucionService institucionService;

    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping("/{id}")
    public EmergenciaEntity getEmergenciaById(@PathVariable Long id) {
        return emergenciaService.buscarEmergenciaPorId(id);

    }

    @GetMapping("/all")
    public List<EmergenciaEntity> getAllEmergencias() {
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

        InstitucionEntity institucion = institucionService.buscarInstitucionPorId(idInstitucion);

        Point zona =  new Point(latitud, longitud);

        Long idUsuario = 1L;
        //auditoriaService.registrarCambio(idUsuario, "Add", "añadio una emergencia");
        emergenciaService.insertarEmergencia(tipoEmergencia, zona, condicionFisica,
                cantidadVoluntariosMinimo2, cantidadVoluntariosMaximo2, idInstitucion);

        // Long idUsuario = //metodo para obtener id de usuario ya listo, esperar a
        // pablo
        // auditoriaService.registrarCambio(idUsuario, "Add", "añadio una emergencia");
// ! Se debe cambiar al terminar el front por seguridad de que no devuelva
        // ! datos, solo debe devolver una respuesta de que se guardo correctamente
    }
    
}
