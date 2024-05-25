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


    @GetMapping("/all")
    public List<RankingEntity> tabla() {
        return rankingService.listaRanking();
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

    @GetMapping("/listaRanking")
    public List<RankingEntity> listaRanking() {
        return rankingService.listaRanking();
    }

    @GetMapping("/{idRanking}")
    public ResponseEntity<RankingEntity> buscarId(@PathVariable Long idRanking) {
        if (idRanking == null) {
            return ResponseEntity.badRequest().build();
        }

        RankingEntity rankingEncontrado = rankingService.buscarRankingPorId(idRanking);

        if (rankingEncontrado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(rankingEncontrado);
    }
    @PostMapping("/add/{idVoluntario}/{idEmergencia}")
    public void crearRanking(@PathVariable Long idVoluntario,
                             @PathVariable Long idEmergencia) {
        List<?> emergenciaZona = rankingService.emergenciaZona(idEmergencia);
        Object[] emergencia = (Object[]) emergenciaZona.get(0);
        String text = rankingService.bytesToString((byte[]) emergencia[2]);
        assert text != null;

        double[] latLong = rankingService.wkbToLatLong(rankingService.hexStringToByteArray(text));
        Double latitudEmergencia = latLong[1];
        Double longitudEmergencia = latLong[0];

        List<?> voluntarioZona = rankingService.voluntarioZona(idVoluntario);
        Object[] voluntario = (Object[]) voluntarioZona.get(0);
        String text1 = rankingService.bytesToString((byte[]) voluntario[2]);
        assert text1 != null;

        double[] latLong1 = rankingService.wkbToLatLong(rankingService.hexStringToByteArray(text1));
        Double latitudVoluntario = latLong1[1];
        Double longitudVoluntario = latLong1[0];

        Double distancia = rankingService.distanciaEntrePuntos(latitudVoluntario, longitudVoluntario, latitudEmergencia, longitudEmergencia);
        List<TareaEntity> tareas = tareaService.tablaTareas(idEmergencia);
        for (TareaEntity tarea : tareas) {
            Long idTarea = tarea.getId();
            TareaEntity nombreTarea = tareaService.buscarTareaPorId(idTarea);
            String tareaRanking = nombreTarea.getNombre();
            VoluntarioEntity buscarVoluntario = voluntarioService.buscarVoluntarioPorId(idVoluntario);
            int nivelRanking = rankingService.puntajeRanking(distancia, idVoluntario);
            String nombreVoluntario = buscarVoluntario.getNombre();
            String numeroDocumentoVoluntario = buscarVoluntario.getNumeroDocumento();
            RankingEntity ranking = new RankingEntity(nivelRanking, tareaRanking, nombreVoluntario,
                    numeroDocumentoVoluntario);
            Long idUsuario = 1L;
            // auditoriaService.registrarCambio(idUsuario, "Add", "añadio un ranking");
            rankingService.insertarRanking(nivelRanking, tareaRanking, nombreVoluntario,
                    numeroDocumentoVoluntario, idTarea,
                    idVoluntario);
            // Long idUsuario = metodo para obtener id de usuario ya listo, esperar a pablo
            // auditoriaService.registrarCambio(idUsuario, "Add", "añadio un ranking");
        }
    }


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
        RankingEntity rankingEntity = rankingService.buscarRankingPorId(idRanking);
        Long idUsuario = 1L;// metodo para obtener id de usuario ya listo, esperar a
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
