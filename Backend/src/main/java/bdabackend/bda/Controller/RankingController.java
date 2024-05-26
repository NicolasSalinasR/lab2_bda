package bdabackend.bda.Controller;

import bdabackend.bda.Entity.RankingEntity;
import bdabackend.bda.Entity.TareaEntity;
import bdabackend.bda.Entity.VoluntarioEntity;
import bdabackend.bda.Service.AuditoriaService;
import bdabackend.bda.Service.RankingService;
import bdabackend.bda.Service.TareaService;
import bdabackend.bda.Service.VoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ranking")
public class RankingController {
    @Autowired
    private RankingService rankingService;
    @Autowired
    private VoluntarioService voluntarioService;
    @Autowired
    private TareaService tareaService;

    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping("/palabra/{palabraClave}")
    public ResponseEntity<List<RankingEntity>> buscarVoluntarios(@PathVariable String palabraClave) {
        List<RankingEntity> rankingEntities = rankingService.listaFiltro(palabraClave);
        if (rankingEntities.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rankingEntities);
    }

/*
    @GetMapping("/palabra/{palabraClave}")
    public ResponseEntity<List<RankingEntity>> buscarRankings(@PathVariable String palabraClave) {
        List<RankingEntity> rankingsEncontrados = rankingService.listaFiltro(palabraClave);
        if (rankingsEncontrados.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rankingsEncontrados);
    }
 */

    @GetMapping("/{id}")
    public List<?> getRankingById(@PathVariable Long id) {
        return rankingService.buscarRankingPorId(id);
    }

    @GetMapping("/all")
    public List<?> getAllRankings() {
        return rankingService.listaRanking();
    }

    @PostMapping("/add")
    public void crearRanking(@RequestBody Map<String, String> body){
        Long idVoluntario = Long.parseLong(body.get("idVoluntario"));
        Long idEmergencia = Long.parseLong(body.get("idEmergencia"));

        List<?> emergenciaZona = rankingService.emergenciaZona(idEmergencia);

        Object[] emergencia = (Object[]) emergenciaZona.get(0);
        String text = rankingService.bytesToString((byte[]) emergencia[5]);
        assert text != null;

        double[] latLong = rankingService.wkbToLatLong(rankingService.hexStringToByteArray(text));
        double latitudEmergencia = latLong[1];
        double longitudEmergencia = latLong[0];

        List<?> voluntarioZona = rankingService.voluntarioZona(idVoluntario);
        Object[] voluntario = (Object[]) voluntarioZona.get(0);
        String text1 = rankingService.bytesToString((byte[]) voluntario[6]);
        assert text1 != null;

        double[] latLong1 = rankingService.wkbToLatLong(rankingService.hexStringToByteArray(text1));
        double latitudVoluntario = latLong1[1];
        double longitudVoluntario = latLong1[0];

        double distancia = rankingService.distanciaEntrePuntos(latitudVoluntario, longitudVoluntario, latitudEmergencia, longitudEmergencia);
        List<?> tareas = tareaService.tareaEmerg(idEmergencia);
        for (Object tarea : tareas) {
            Object[] tarea1 = (Object[]) tarea;
            Long idTarea = (Long) tarea1[0];
            String tareaRanking = tareaService.nombre(idTarea);
            int nivelRanking = rankingService.puntajeRanking(distancia, idVoluntario);
            String nombreVoluntario = voluntarioService.nombrev(idVoluntario);
            String numeroDocumentoVoluntario = voluntarioService.numerov(idVoluntario);
            //RankingEntity ranking = new RankingEntity(nivelRanking, tareaRanking, nombreVoluntario,
            //        numeroDocumentoVoluntario);
            //Long idUsuario = 1L;
            // auditoriaService.registrarCambio(idUsuario, "Add", "añadio un ranking");
            rankingService.insertarRanking(nivelRanking, tareaRanking, nombreVoluntario,
                    numeroDocumentoVoluntario, idTarea,
                    idVoluntario);
            // Long idUsuario = metodo para obtener id de usuario ya listo, esperar a pablo
            // auditoriaService.registrarCambio(idUsuario, "Add", "añadio un ranking");
        }
    }

/*
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
 */
    /*
    @PostMapping("/add/{idVoluntario}/{idEmergencia}")
    public void crearRanking(@PathVariable Long idVoluntario,
                             @PathVariable Long idEmergencia) {
        List<TareaEntity> tareas = tareaService.tablaIds(idEmergencia);
        for (TareaEntity tarea : tareas) {
            Long idTarea = tarea.getId();
            TareaEntity nombreTarea = tareaService.getTareaById(idTarea);
            String tareaRanking = nombreTarea.getNombreTarea();
            VoluntarioEntity buscarVoluntario = voluntarioService.buscarId(idVoluntario);
            Point zona = buscarVoluntario.getZonaViviendaVoluntario();
            int nivelRanking = rankingService.puntajeRanking(zona, idVoluntario,
                    idTarea);
            String nombreVoluntario = buscarVoluntario.getNombreVoluntario();
            String numeroDocumentoVoluntario = buscarVoluntario.getNumeroDocumentoVoluntario();
            RankingEntity ranking = new RankingEntity(nombreTarea, buscarVoluntario,
                    nombreVoluntario,
                    numeroDocumentoVoluntario, nivelRanking, tareaRanking);
            Long idUsuario = 1L;
            // auditoriaService.registrarCambio(idUsuario, "Add", "añadio un ranking");
            rankingService.nuevoRanking(ranking);
            // Long idUsuario = metodo para obtener id de usuario ya listo, esperar a pablo
            // auditoriaService.registrarCambio(idUsuario, "Add", "añadio un ranking");
        }
    }
     */

    @DeleteMapping("/delete/{idRanking}")
    public void eliminar(@PathVariable Long idRanking) {
        //RankingEntity rankingEntity = rankingService.buscarRankingPorId(idRanking);
        //Long idUsuario = 1L;// metodo para obtener id de usuario ya listo, esperar a
        // pablo
        // auditoriaService.registrarCambio(idUsuario, "Delete", "elimino un ranking");
        rankingService.eliminarRankingPorId(idRanking);

    }

    // @PutMapping("editar/{idVoluntario}")
    // public void actualizar(@PathVariable Long idVoluntario) {
    // VoluntarioEntity update = voluntarioService.buscarId(idVoluntario);
    // String zona = update.getZonaViviendaVoluntario();
    // RankingEntity updateUser = rankingService.buscarId(idVoluntario);
    // updateUser.setNivelRanking(rankingService.puntajeRanking(zona,
    // idVoluntario));
    // rankingService.nuevoRanking(updateUser);

    // Long idUsuario = metodo para obtener id de usuario ya listo, esperar a pablo
    // auditoriaService.registrarCambio(idUsuario, "update", "modifico un Ranking");
    // }
}
