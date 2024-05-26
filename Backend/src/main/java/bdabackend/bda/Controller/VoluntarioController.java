package bdabackend.bda.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import bdabackend.bda.Entity.LoginRequest;
import bdabackend.bda.Entity.VoluntarioEntity;
import bdabackend.bda.Service.AuthService;
import bdabackend.bda.Service.VoluntarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import bdabackend.bda.Entity.AuthenticationResponse;
import bdabackend.bda.Entity.AuthenticationResponse;
import bdabackend.bda.Entity.VoluntarioEntity;
import bdabackend.bda.Entity.LoginRequest;
import bdabackend.bda.Service.AuditoriaService;
import bdabackend.bda.Service.AuthService;
import bdabackend.bda.Service.VoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/voluntario")
@CrossOrigin("*")
public class VoluntarioController {
    @Autowired
    private VoluntarioService voluntarioService;

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/all")
    public List<?> tabla() {
        return voluntarioService.listaVoluntario();
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
    public List<?> buscarId(@PathVariable Long idVoluntario) {
        return voluntarioService.listaVoluntarioId(idVoluntario);
    }

    @DeleteMapping("/delete/{idVoluntario}")
    public void eliminar(@PathVariable Long idVoluntario) {
        voluntarioService.eliminarVoluntarioPorId(idVoluntario);
    }
    @PostMapping("/add")
    public void crearVoluntario(@RequestBody Map<String, String> body) {
        String nombreVoluntario = body.get("nombreVoluntario");
        String contrasenaVoluntario = body.get("contrasenaVoluntario");
        String correoVoluntario = body.get("correoVoluntario");
        String numeroDocumentoVoluntario = body.get("numeroDocumentoVoluntario");
        String equipamientoVoluntario = body.get("equipamientoVoluntario");
        Double latitud = Double.parseDouble(body.get("latitud"));
        Double longitud = Double.parseDouble(body.get("longitud"));
        // Llama al servicio para crear un nuevo voluntario
        voluntarioService.crearVoluntario(nombreVoluntario, correoVoluntario, numeroDocumentoVoluntario,
                latitud, longitud, passwordEncoder.encode(contrasenaVoluntario), equipamientoVoluntario);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody Map<String, String> body) {
        // Se recibe el correo y la contraseña
        String correo = body.get("correo");
        String contrasena = body.get("contrasena");
        // Se crea un LoginRequest con el correo y la contraseña
        LoginRequest loginRequest = new LoginRequest(correo, contrasena);
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/register")
    public VoluntarioEntity register(@RequestBody Map<String, String> body) {
        // Se reciben los parametros de nombre, contraseña correo y numero de documento
        String nombre = body.get("nombre");
        String correo = body.get("correo");
        String numeroDocumento = body.get("numeroDocumento");
        // TODO: Cambiar el tipo de dato de zonaVivienda a Point, agregar la logica
        Point zonaVivienda = new Point(0, 0); // TODO: se debe cambiar por el parametro de entrada
        String contrasena = body.get("contrasena");
        String equipamiento = body.get("equipamiento");

        // Se crea un VoluntarioEntity con los parametros recibidos
        VoluntarioEntity voluntario = new VoluntarioEntity(nombre, correo, numeroDocumento, zonaVivienda,
                passwordEncoder.encode(contrasena),
                equipamiento);

        // Se guarda el voluntario en la base de datos
        voluntarioService.insertarVoluntario(voluntario);
        return voluntario;
    }
}