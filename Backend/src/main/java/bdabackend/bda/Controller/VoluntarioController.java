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
        String zonaVivienda = body.get("zonaVivienda");
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