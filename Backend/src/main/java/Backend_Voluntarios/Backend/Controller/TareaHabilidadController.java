package Backend_Voluntarios.Backend.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import Backend_Voluntarios.Backend.Entity.EmeHabilidadEntity;
import Backend_Voluntarios.Backend.Entity.HabilidadEntity;
import Backend_Voluntarios.Backend.Entity.TareaEntity;
import Backend_Voluntarios.Backend.Service.AuditoriaService;
import Backend_Voluntarios.Backend.Service.EmeHabilidadService;
import Backend_Voluntarios.Backend.Service.HabilidadService;
import Backend_Voluntarios.Backend.Service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Backend_Voluntarios.Backend.Service.TareaHabilidadService;
import Backend_Voluntarios.Backend.Entity.TareaHabilidadEntity;

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


        TareaEntity tareaNew = tareaService.getTareaById(idTarea);
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