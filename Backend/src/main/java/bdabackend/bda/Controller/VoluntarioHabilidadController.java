package bdabackend.bda.Controller;

import bdabackend.bda.Entity.HabilidadEntity;
import bdabackend.bda.Entity.VoluntarioEntity;
import bdabackend.bda.Entity.VoluntarioHabilidadEntity;
import bdabackend.bda.Service.AuditoriaService;
import bdabackend.bda.Service.HabilidadService;
import bdabackend.bda.Service.VoluntarioHabilidadService;
import bdabackend.bda.Service.VoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/voluntarioHabilidad")
public class VoluntarioHabilidadController {
    @Autowired
    private VoluntarioHabilidadService voluntarioHabilidadService;

    @Autowired
    private VoluntarioService voluntarioService;

    @Autowired
    private HabilidadService habilidadService;

    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping("{id}")
    public VoluntarioHabilidadEntity getVoluntarioHabilidadById(Long id) {
        return voluntarioHabilidadService.buscarVoluntarioHabilidadPorId(id);
    }

    @GetMapping("/all")
    public List<VoluntarioHabilidadEntity> getAllVoluntarioHabilidades() {
        return voluntarioHabilidadService.getAllVoluntarioHabilidades();
    }

    @PostMapping("/add")
    public VoluntarioHabilidadEntity addVoluntarioHabilidad(@RequestBody Map<String, String> body) {
        Long idVoluntario = Long.parseLong(body.get("voluntario"));
        Long idHabilidad = Long.parseLong(body.get("habilidad"));

        HabilidadEntity habilidadNew = habilidadService.buscarHabilidadPorId(idVoluntario);
        VoluntarioEntity voluntarioNew = voluntarioService.buscarVoluntarioPorId(idHabilidad);

        VoluntarioHabilidadEntity voluntarioHabilidad = new VoluntarioHabilidadEntity(voluntarioNew, habilidadNew);
        Long idUsuario = 2L;
        //auditoriaService.registrarCambio(idUsuario, "Add", "añadio una voluntario Habilidad");
        voluntarioHabilidadService.insertarVoluntarioHabilidad(idHabilidad,idVoluntario);

        // Long idUsuario = //metodo para obtener id de usuario ya listo, esperar a
        // pablo
        // auditoriaService.registrarCambio(idUsuario, "Add", "añadio una voluntario
        // Habilidad");
        return voluntarioHabilidad;
    }

}
