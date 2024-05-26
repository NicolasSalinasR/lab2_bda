package bdabackend.bda.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import bdabackend.bda.Entity.AuthenticationResponse;
import bdabackend.bda.Entity.CoordinadorEntity;
import bdabackend.bda.Entity.LoginRequest;
import bdabackend.bda.Service.AuthService;
import bdabackend.bda.Service.CoordinadorService;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/coordinador")
@CrossOrigin(origins = "*")
public class CoordinadorController {
    @Autowired
    AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CoordinadorService coordinadorService;

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
    public CoordinadorEntity register(@RequestBody Map<String, String> body) {
        // Se reciben los parametros de nombre, contraseña correo y numero de documento
        String nombre = body.get("nombre");
        String contrasena = body.get("contrasena");
        String correo = body.get("correo");
        String numeroDocumento = body.get("numeroDocumento");
        // Se crea un CoordinadorEntity con los parametros recibidos
        CoordinadorEntity coordinador = new CoordinadorEntity(nombre, correo, passwordEncoder.encode(contrasena),
                numeroDocumento);
        // Se guarda el coordinador en la base de datos
        coordinadorService.insertarCoordinador(coordinador);
        return coordinador;
    }
    @DeleteMapping("/delete/{id}")
    public void eliminar(@PathVariable Long id) {
        //Long idUsuario = 2L;//metodo para obtener id de usuario ya listo, esperar a
        // pablo
        //auditoriaService.registrarCambio(idUsuario, "Delete", "elimino unvoluntario");
        coordinadorService.eliminarCoordinadorPorId(id);
    }
}
