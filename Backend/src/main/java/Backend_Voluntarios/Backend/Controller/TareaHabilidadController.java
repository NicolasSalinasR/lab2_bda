package Backend_Voluntarios.Backend.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import Backend_Voluntarios.Backend.Entity.*;
import Backend_Voluntarios.Backend.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static Backend_Voluntarios.Backend.Controller.VoluntarioController.bytesToString;
import static Backend_Voluntarios.Backend.Controller.VoluntarioController.wkbToLatLong;
import static ch.qos.logback.core.encoder.ByteArrayUtil.hexStringToByteArray;

@RestController
@RequestMapping("/tareaHabilidad")
@CrossOrigin(origins = "*")
public class TareaHabilidadController {
    @Autowired
    private TareaHabilidadService tareaHabilidadService;
    @Autowired
    private TareaService tareaService;
    @Autowired
    private EmeHabilidadService emeHabilidadService;

    @Autowired
    private AuditoriaService auditoriaService;

    @Autowired
    private EmergenciaService emergenciaservice;

    @GetMapping("/{id}")
    public TareaHabilidadEntity getTareaHabilidadById(@PathVariable Long id) {
        return tareaHabilidadService.getTareaHabilidadById(id);
    }

    @GetMapping("/all")
    public List<TareaHabilidadEntity> getAllTareaHabilidad() {
        return tareaHabilidadService.getAllTareaHabilidades();
    }

    @PostMapping("/add")
    public TareaHabilidadEntity addTareaHabilidad(@RequestBody Map<String, String> body) {
        Long idTarea = Long.parseLong(body.get("tarea"));


        Long idEmeHabilidad = Long.parseLong(body.get("emeHabilidad"));
        
        String habilidadRequerida = body.get("habilidadRequerida");

        List<?> tareas = tareaService.getTareaById(idTarea);
        Object[] tareasDos = (Object[]) tareas.get(0);
        Long id = ((Long) tareasDos[0]);
        String nombre = ((String) tareasDos[1]);
        String descripcion = ((String) tareasDos[2]);
        String tipo = ((String) tareasDos[3]);
        String zona = bytesToString((byte[]) tareasDos[4]);

        double[] latLong = wkbToLatLong(hexStringToByteArray(zona));
        double latitudVoluntario = latLong[1];
        double longiotudVoluntario = latLong[0];

         Point zonaTarea = new Point(longiotudVoluntario, latitudVoluntario);


        Long idEmergencia = ((Long) tareasDos[5]);

        EmergenciaEntity emergencia = emergenciaservice.getEmergenciaById(idEmergencia);
        TareaEntity tareaNew = new TareaEntity(id,nombre,descripcion,tipo,emergencia, zonaTarea);


        EmeHabilidadEntity emeHabilidadNew = emeHabilidadService.getEmeHabilidadById(idEmeHabilidad);

        TareaHabilidadEntity tareaHabilidad = new TareaHabilidadEntity(tareaNew, emeHabilidadNew, habilidadRequerida);
        Long idUsuario = 1L;
        //auditoriaService.registrarCambio(idUsuario, "Add", "añadio una tarea Habilidad");
        tareaHabilidadService.addTareaHabilidad(tareaHabilidad);

        // Long idUsuario = //metodo para obtener id de usuario ya listo, esperar a
        // pablo
        // auditoriaService.registrarCambio(idUsuario, "Add", "añadio una tarea
        // Habilidad");

        return tareaHabilidad;
    }
}