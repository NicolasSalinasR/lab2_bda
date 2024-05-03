package Backend_Voluntarios.Backend.Controller;

import Backend_Voluntarios.Backend.Entity.AuthenticationResponse;
import Backend_Voluntarios.Backend.Entity.LoginRequest;
import Backend_Voluntarios.Backend.Entity.VoluntarioEntity;
import Backend_Voluntarios.Backend.Service.AuditoriaService;
import Backend_Voluntarios.Backend.Service.VoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import Backend_Voluntarios.Backend.Service.AuthService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/voluntario")
public class VoluntarioController {

    @Autowired
    private VoluntarioService voluntarioService;

    @Autowired
    private AuthService authService;

    @Autowired
    private AuditoriaService auditoriaService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping()
    public String conectado() {
        return "CONECTADO";
    }

    @GetMapping("/all")
    public List<VoluntarioEntity> tabla() {
        return voluntarioService.tablaCompleta();
    }

    @GetMapping("/palabra/{palabraClave}")
    public ResponseEntity<List<VoluntarioEntity>> buscarVoluntarios(@PathVariable String palabraClave) {
        List<VoluntarioEntity> voluntariosEncontrados = voluntarioService.listaFiltro(palabraClave);
        if (voluntariosEncontrados.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(voluntariosEncontrados);
    }

    @GetMapping("/{idVoluntario}")
    public ResponseEntity<List<VoluntarioEntity>> buscarId(@PathVariable Long idVoluntario) {
        if (idVoluntario == null) {
            return ResponseEntity.badRequest().build();
        }
        List<VoluntarioEntity> idVoluntariosEncontrados = voluntarioService.tablaId(idVoluntario);
        if (idVoluntariosEncontrados.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(idVoluntariosEncontrados);
    }

    @PostMapping("/add")
    public VoluntarioEntity crearVoluntario(@RequestBody Map<String, String> body) {
        String nombreVoluntario = body.get("nombreVoluntario");
        String contrasenaVoluntario = body.get("contrasenaVoluntario");
        String correoVoluntario = body.get("correoVoluntario");
        String numeroDocumentoVoluntario = body.get("numeroDocumentoVoluntario");
        String equipamientoVoluntario = body.get("equipamientoVoluntario");
        String zonaViviendaVoluntario = body.get("zonaViviendaVoluntario");

        VoluntarioEntity voluntario = new VoluntarioEntity(nombreVoluntario, correoVoluntario,
                numeroDocumentoVoluntario, equipamientoVoluntario, zonaViviendaVoluntario,
                passwordEncoder.encode(contrasenaVoluntario));
        Long idUsuario = 2L;
        auditoriaService.registrarCambio(idUsuario, "Add", "añadio un voluntario");
        voluntarioService.nuevoVoluntario(voluntario);

        // Long idUsuario = //metodo para obtener id de usuario ya listo, esperar a
        // pablo
        // auditoriaService.registrarCambio(idUsuario, "Add", "añadio un voluntario");

        return voluntario;
    }

    @DeleteMapping("/delete/{idVoluntario}")
    public void eliminar(@PathVariable Long idVoluntario) {
        VoluntarioEntity voluntarioBorrado = voluntarioService.buscarId(idVoluntario);
        Long idUsuario = 2L;//metodo para obtener id de usuario ya listo, esperar a
        // pablo
        auditoriaService.registrarCambio(idUsuario, "Delete", "elimino unvoluntario");
        voluntarioService.borrarVoluntario(voluntarioBorrado);


    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody Map<String, String> body) {
        // Se confirma que el usuario y contraseña sean correctos, si lo son se genera
        // un JWT y se devuelve
        // Si no son correctos se devuelve un error
        String correoVoluntario = body.get("correoVoluntario");
        String contrasenaVoluntario = body.get("contrasenaVoluntario");

        LoginRequest loginRequest = new LoginRequest(correoVoluntario, contrasenaVoluntario);
        return ResponseEntity.ok(authService.login(loginRequest));

    }
}
