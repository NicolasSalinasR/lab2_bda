package Backend_Voluntarios.Backend.Controller;

import Backend_Voluntarios.Backend.Entity.AuthenticationResponse;
import Backend_Voluntarios.Backend.Entity.CoordinadorEntity;
import Backend_Voluntarios.Backend.Entity.LoginRequest;
import Backend_Voluntarios.Backend.Service.AuthService;
import Backend_Voluntarios.Backend.Service.CoordinadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/coordinador")
public class CoordinadorController {
    @Autowired
    private CoordinadorService coordinadorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    @GetMapping()
    public String conectado() {
        return "CONECTADO";
    }

    @GetMapping("/all")
    public List<CoordinadorEntity> tabla() {
        return coordinadorService.tablaCompleta();
    }

    @GetMapping("/palabra/{palabraClave}")
    public ResponseEntity<List<CoordinadorEntity>> buscarCoordinador(@PathVariable String palabraClave) {
        List<CoordinadorEntity> coordinadoresEncontrados = coordinadorService.listaFiltro(palabraClave);
        if (coordinadoresEncontrados.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(coordinadoresEncontrados);
    }

    @GetMapping("/{idCoordinador}")
    public ResponseEntity<List<CoordinadorEntity>> buscarId(@PathVariable Long idCoordinador) {
        if (idCoordinador == null) {
            return ResponseEntity.badRequest().build();
        }
        List<CoordinadorEntity> idCoordinadoresEncontrados = coordinadorService.tablaId(idCoordinador);
        if (idCoordinadoresEncontrados.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(idCoordinadoresEncontrados);
    }

    @PostMapping("/add")
    public CoordinadorEntity crearCoordinador(@RequestBody Map<String, String> body) {
        String nombre = body.get("nombre");
        String contrasenaCoordinador = body.get("contrasenaCoordinador");
        String correoCoordinador = body.get("correoCoordinador");
        String numeroDocumentoCoordinador = body.get("numeroDocumentoCoordinador");
        CoordinadorEntity coordinador = new CoordinadorEntity(nombre, passwordEncoder.encode(contrasenaCoordinador),
                correoCoordinador,
                numeroDocumentoCoordinador);
        coordinadorService.nuevoCoordinador(coordinador);
        return coordinador;
    }

    @DeleteMapping("/delete/{idCoordinador}")
    public void eliminar(@PathVariable Long idCoordinador) {
        CoordinadorEntity coordinadorBorrado = coordinadorService.buscarId(idCoordinador);
        coordinadorService.borrarCoordinador(coordinadorBorrado);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody Map<String, String> body) {
        String correoCoordinador = body.get("correoCoordinador");
        String contrasenaCoordinador = body.get("contrasenaCoordinador");

        LoginRequest loginRequest = new LoginRequest(correoCoordinador, contrasenaCoordinador);
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
