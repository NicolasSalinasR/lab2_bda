package bdabackend.bda.Controller;

import bdabackend.bda.Entity.EmergenciaEntity;
import bdabackend.bda.Entity.EmergenciaHabilidadEntity;
import bdabackend.bda.Entity.TareaEntity;
import bdabackend.bda.Entity.TareaHabilidadEntity;
import bdabackend.bda.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    private EmergenciaHabilidadSevice emeHabilidadService;

    @Autowired
    private AuditoriaService auditoriaService;

    @Autowired
    private EmergenciaService emergenciaservice;

    @GetMapping("/{id}")
    public TareaHabilidadEntity getTareaHabilidadById(@PathVariable Long id) {
        return tareaHabilidadService.buscarTareaHabilidadPorId(id);
    }

    @GetMapping("/all")
    public List<TareaHabilidadEntity> getAllTareaHabilidad() {
        return tareaHabilidadService.getAllTareaHabilidades();
    }

    @PostMapping("/add")
    public void addTareaHabilidad(@RequestBody Map<String, String> body) {
       Long idTarea = Long.parseLong(body.get("tarea"));


        Long idEmeHabilidad = Long.parseLong(body.get("emeHabilidad"));

        String habilidadRequerida = body.get("habilidadRequerida");

  //      TareaEntity tareas = tareaService.getTareaById(idTarea);
  //      String tareasDos = tareas.getNombreTarea();
  //      Long id = ((Long) tareasDos[0]);
  //      String nombre = ((String) tareasDos[1]);
 //       String descripcion = ((String) tareasDos[2]);
 //       String tipo = ((String) tareasDos[3]);
 //       String zona = bytesToString((byte[]) tareasDos[4]);

 //       double[] latLong = wkbToLatLong(hexStringToByteArray(zona));
 //       double latitudVoluntario = latLong[1];
//        double longiotudVoluntario = latLong[0];

       // Point zonaTarea = new Point(longiotudVoluntario, latitudVoluntario);


      //  Long idEmergencia = ((Long) tareasDos[5]);

      //  EmergenciaEntity emergencia = emergenciaservice.getEmergenciaById(idEmergencia);
      //  TareaEntity tareaNew = new TareaEntity(id,nombre,descripcion,tipo,emergencia, zonaTarea);


     //   EmergenciaHabilidadEntity emeHabilidadNew = emeHabilidadService.getEmeHabilidadById(idEmeHabilidad);

     //   TareaHabilidadEntity tareaHabilidad = new TareaHabilidadEntity(tareaNew, emeHabilidadNew, habilidadRequerida);
     //   Long idUsuario = 1L;
        //auditoriaService.registrarCambio(idUsuario, "Add", "añadio una tarea Habilidad");
        tareaHabilidadService.insertarTareaHabilidad(habilidadRequerida,idTarea, idEmeHabilidad);

        // Long idUsuario = //metodo para obtener id de usuario ya listo, esperar a
        // pablo
        // auditoriaService.registrarCambio(idUsuario, "Add", "añadio una tarea
        // Habilidad");


    }
}
