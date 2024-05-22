package bdabackend.bda.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bdabackend.bda.Entity.RankingEntity;
import bdabackend.bda.Repository.RankingRepository;

import java.util.List;

@Service
public class RankingService {
    @Autowired
    private RankingRepository rankingRepository;

    public void insertarRanking(String nivel, String tareaRanking, String nombreVoluntario,
            String numeroDocumentoVoluntario, Long idTarea, Long idVoluntario) {
        rankingRepository.insertarRanking(nivel, tareaRanking, nombreVoluntario, numeroDocumentoVoluntario, idTarea,
                idVoluntario);
    }

    public void insertarRankingSinTareaVoluntario(String nivel, String tareaRanking, String nombreVoluntario,
            String numeroDocumentoVoluntario) {
        rankingRepository.insertarRankingSinTareaVoluntario(nivel, tareaRanking, nombreVoluntario,
                numeroDocumentoVoluntario);
    }

    public void eliminarRankingPorId(Long id) {
        rankingRepository.eliminarRankingPorId(id);
    }

    public RankingEntity buscarRankingPorId(Long id) {
        return rankingRepository.buscarRankingPorId(id);
    }

    public List<RankingEntity> listaRanking() {
        return rankingRepository.listaRanking();
    }
}
