package bdabackend.bda.Controller;

import bdabackend.bda.Entity.RankingEntity;
import bdabackend.bda.Service.AuditoriaService;
import bdabackend.bda.Service.RankingService;
import bdabackend.bda.Service.TareaService;
import bdabackend.bda.Service.VoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @DeleteMapping("/delete/{idRanking}")
    public void eliminar(@PathVariable Long idRanking) {
        //RankingEntity rankingEntity = rankingService.buscarRankingPorId(idRanking);
        //Long idUsuario = 1L;// metodo para obtener id de usuario ya listo, esperar a
        // pablo
        // auditoriaService.registrarCambio(idUsuario, "Delete", "elimino un ranking");
        rankingService.eliminarRankingPorId(idRanking);

    }
}
