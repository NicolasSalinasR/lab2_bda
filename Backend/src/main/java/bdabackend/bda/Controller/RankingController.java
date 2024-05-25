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

    @GetMapping("/palabra/{palabraClave}")
    public ResponseEntity<List<RankingEntity>> buscarRankings(@PathVariable String palabraClave) {
        List<RankingEntity> rankingsEncontrados = rankingService.listaFiltro(palabraClave);
        if (rankingsEncontrados.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rankingsEncontrados);
    }

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
