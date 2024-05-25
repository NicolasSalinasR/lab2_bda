package Backend_Voluntarios.Backend.Service;

import Backend_Voluntarios.Backend.Entity.RankingEntity;
import Backend_Voluntarios.Backend.Entity.VoluntarioEntity;
import Backend_Voluntarios.Backend.Repository.EmergenciaRepository;
import Backend_Voluntarios.Backend.Repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RankingService {

    @Autowired
    private RankingRepository repositoryRanking;
    @Autowired
    private VoluntarioService voluntarioService;

    public List<RankingEntity> listaFiltro(String palabraClave) {
        return repositoryRanking.findAll(palabraClave);
    }

    public List<RankingEntity> tablaCompleta() {
        return repositoryRanking.listAll();
    }

    public List<RankingEntity> listaRanking() {
        return repositoryRanking.listRanking();
    }

    public List<RankingEntity> tablaId(Long idVoluntario) {
        return repositoryRanking.buscarIdRanking(idVoluntario);
    }

    public void nuevoRanking(RankingEntity rankingEntity) {
        repositoryRanking.crearRanking(rankingEntity.getIdTarea(),
                rankingEntity.getIdVoluntario(),
                rankingEntity.getNombreVoluntario(),
                rankingEntity.getNumeroDocumentoVoluntario(),
                rankingEntity.getNivelRanking(),
                rankingEntity.getTareaRanking());
    }

    public void borrarRanking(RankingEntity rankingEntity) {
        repositoryRanking.borrarRanking(rankingEntity.getIdRanking());
    }

    public RankingEntity buscarId(Long idRanking) {
        return repositoryRanking.idRanking(idRanking);
    }

    public int puntajeRanking(Point zona, Long idVoluntario, Long idTarea) {
        int contador = 0;
        VoluntarioEntity voluntarioEntity = voluntarioService.buscarId(idVoluntario);
        String equipo = voluntarioEntity.getEquipamientoVoluntario();
        String[] elementos = equipo.split("\\s*,\\s*");
        for (String elemento : elementos) {
            List<String> resultadoFuncion = repositoryRanking.matchEquipo(elemento);
            if (!resultadoFuncion.isEmpty()) {
                contador = contador + 1;
            }
        }
        contador = contador + repositoryRanking.matchHabilidad(idVoluntario);
        if (!repositoryRanking.matchZona(zona).isEmpty()) {
            contador = contador + 1;
        }
        return contador;
    }

    public List<Object[]> obtenerVoluntariosPorEmergencia() {
        return repositoryRanking.obtenerVoluntariosPorEmergencia();
    }
}
