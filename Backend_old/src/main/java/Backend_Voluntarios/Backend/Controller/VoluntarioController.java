package Backend_Voluntarios.Backend.Controller;

import Backend_Voluntarios.Backend.Entity.AuthenticationResponse;
import Backend_Voluntarios.Backend.Entity.LoginRequest;
import Backend_Voluntarios.Backend.Entity.VoluntarioEntity;
import Backend_Voluntarios.Backend.Service.AuditoriaService;
import Backend_Voluntarios.Backend.Service.VoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import Backend_Voluntarios.Backend.Service.AuthService;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
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
    public List<?> tabla() {
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
    public ResponseEntity<List<?>> buscarId(@PathVariable Long idVoluntario) {
        if (idVoluntario == null) {
            return ResponseEntity.badRequest().build();
        }
        List<?> idVoluntariosEncontrados = voluntarioService.tablaId(idVoluntario);
        if (idVoluntariosEncontrados.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(idVoluntariosEncontrados);
    }

    @GetMapping("/zona")
    public String zona(@RequestBody Map<String, String> body){
        Long idVoluntario = Long.parseLong(body.get("idVoluntario"));
        List<?> idVoluntariosEncontrados = voluntarioService.tablaId(idVoluntario);
        //VoluntarioEntity voluntario = (VoluntarioEntity) idVoluntariosEncontrados.get(0);
        Object[] voluntario = (Object[]) idVoluntariosEncontrados.get(0);
        String text = bytesToString((byte[]) voluntario[6]);
        assert text != null;

        double[] latLong = wkbToLatLong(hexStringToByteArray(text));

        // Imprime las coordenadas x e y
        return("Latitud: " + latLong[1] + ", Longitud: " + latLong[0]);
    }

    //lista de 20 voluntarios mas cercanos a la emergencia
    @GetMapping("/voluntariosMasCercanos/{latitud}{longitud}")
    public void voluntariosMasCercanos(@PathVariable Double latitud, @PathVariable Double longitud){
        List<?> voluntarios = voluntarioService.tablaCompleta();
        int largo = voluntarios.size();
        List <Long> voluntariosCercanos = null;
        List <Double> voluntariosCercanosDistancia = null;
        for (int i = 0; i< largo; i++){
            Object[] voluntario = (Object[]) voluntarios.get(i);
            String text = bytesToString((byte[]) voluntario[6]);
            Long id = ((Long) voluntario[0]);
            assert text != null;
            double[] latLong = wkbToLatLong(hexStringToByteArray(text));
            double latitudVoluntario = latLong[1];
            double longiotudVoluntario = latLong[0];
            double distancia = distanciaEntrePuntos(latitud, longitud, latitudVoluntario, longiotudVoluntario );
            if(voluntariosCercanos.size() == 20){
                double auxiliar = voluntariosCercanosDistancia.get(0);
                int idAuxiliar = 0;
                for (int n =1; n<20; n++){
                    double distanciaEnLista = voluntariosCercanosDistancia.get(n);
                    if(auxiliar <= distanciaEnLista){
                        auxiliar = distanciaEnLista;
                        idAuxiliar = n;
                    }
                }
                if(distancia <= auxiliar){
                    voluntariosCercanosDistancia.set(idAuxiliar, auxiliar);
                    voluntariosCercanos.set(idAuxiliar, id);
                }
            }
            else {
                voluntariosCercanos.add(id);
                voluntariosCercanosDistancia.add(distancia);
            }
        }
    }

    private static double distanciaEntrePuntos(double latitudPunto1, double longitudPunto1, double latitudPunto2, double longitudPunto2) {
        // Radio de la Tierra en metros
        final double radioTierra = 6371000;

        // Convertir las coordenadas de grados a radianes
        double latitudPunto1Rad = Math.toRadians(latitudPunto1);
        double longitudPunto1Rad = Math.toRadians(longitudPunto1);
        double latitudPunto2Rad = Math.toRadians(latitudPunto2);
        double longitudPunto2Rad = Math.toRadians(longitudPunto2);

        // Calcular la diferencia entre las coordenadas
        double diferenciaLatitud = latitudPunto2Rad - latitudPunto1Rad;
        double diferenciaLongitud = longitudPunto2Rad - longitudPunto1Rad;

        // Calcular la distancia utilizando la fórmula del haversine
        double a = Math.pow(Math.sin(diferenciaLatitud / 2), 2) +
                Math.cos(latitudPunto1Rad) * Math.cos(latitudPunto2Rad) *
                        Math.pow(Math.sin(diferenciaLongitud / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distancia = radioTierra * c;

        return distancia;
    }

    static String bytesToString(byte[] bytes) {
        try {
            return new String(bytes, "UTF-8"); // Convertir los bytes a una cadena usando el charset especificado
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    static double[] wkbToLatLong(byte[] wkbBytes) {
        ByteBuffer buffer = ByteBuffer.wrap(wkbBytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN); // Orden de bytes para interpretar como números de punto flotante
        buffer.position(9); // Saltar los primeros nueve bytes (tipo de geometría y orden de bytes)
        double longitude = buffer.getDouble(); // Coordenada X (longitud)
        double latitude = buffer.getDouble(); // Coordenada Y (latitud)
        return new double[] {longitude, latitude};
    }

    private static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
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


    @DeleteMapping("/delete/{idVoluntario}")
    public void eliminar(@PathVariable Long idVoluntario) {
        VoluntarioEntity voluntarioBorrado = voluntarioService.buscarId(idVoluntario);
        Long idUsuario = 2L;//metodo para obtener id de usuario ya listo, esperar a
        // pablo
        //auditoriaService.registrarCambio(idUsuario, "Delete", "elimino unvoluntario");
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
