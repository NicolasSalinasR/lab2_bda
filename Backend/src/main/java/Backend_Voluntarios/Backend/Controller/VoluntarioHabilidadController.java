package Backend_Voluntarios.Backend.Controller;

import java.util.List;
import java.util.Map;

import Backend_Voluntarios.Backend.Entity.HabilidadEntity;
import Backend_Voluntarios.Backend.Entity.VoluntarioEntity;
import Backend_Voluntarios.Backend.Service.AuditoriaService;
import Backend_Voluntarios.Backend.Service.HabilidadService;
import Backend_Voluntarios.Backend.Service.VoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Backend_Voluntarios.Backend.Entity.VoluntarioHabilidadEntity;
import Backend_Voluntarios.Backend.Service.VoluntarioHabilidadService;

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
        return voluntarioHabilidadService.getVoluntarioHabilidadById(id);
    }

    @GetMapping("/all")
    public List<VoluntarioHabilidadEntity> getAllVoluntarioHabilidades() {
        return voluntarioHabilidadService.getAllVoluntarioHabilidades();
    }

    @PostMapping("/add")
    public VoluntarioHabilidadEntity addVoluntarioHabilidad(@RequestBody Map<String, String> body) {
        Long idVoluntario = Long.parseLong(body.get("voluntario"));
        Long idHabilidad = Long.parseLong(body.get("habilidad"));

        HabilidadEntity habilidadNew = habilidadService.findByIds(idVoluntario);
        VoluntarioEntity voluntarioNew = voluntarioService.buscarId(idHabilidad);

        VoluntarioHabilidadEntity voluntarioHabilidad = new VoluntarioHabilidadEntity(voluntarioNew, habilidadNew);
        Long idUsuario = 2L;
        //auditoriaService.registrarCambio(idUsuario, "Add", "añadio una voluntario Habilidad");
        voluntarioHabilidadService.addVoluntarioHabilidad(voluntarioHabilidad);

        // Long idUsuario = //metodo para obtener id de usuario ya listo, esperar a
        // pablo
        // auditoriaService.registrarCambio(idUsuario, "Add", "añadio una voluntario
        // Habilidad");
        return voluntarioHabilidad;
    }
}
