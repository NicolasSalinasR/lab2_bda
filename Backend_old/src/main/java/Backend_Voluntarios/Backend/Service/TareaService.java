package Backend_Voluntarios.Backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Backend_Voluntarios.Backend.Repository.TareaRepository;
import Backend_Voluntarios.Backend.Entity.TareaEntity;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TareaService {
    @Autowired
    private TareaRepository tareaRepository;

    public TareaEntity getTareaById(Long id) {
        TareaEntity tarea = new TareaEntity();
        List<?> tarea2 = tareaRepository.findTareaById(id);
        tarea.setIdTarea(id);
        tarea.setNombreTarea((String) tarea2.get(0));
        tarea.setDescripcionTarea((String) tarea2.get(1));
        tarea.setTipoTarea((String) tarea2.get(2));
        System.out.println("Tarea: " + tarea);
        return tarea;
    }

    public List<?> getAllTareas() {
        return tareaRepository.findAllTareas();
    }

    public List<TareaEntity> tablaIds(Long idEmergencia) {
        return tareaRepository.buscarIdTarea(idEmergencia);
    }

    public List<TareaEntity> getRankingTarea(String nombreTarea) {
        return tareaRepository.listRankingTarea(nombreTarea);
    }

    @Transactional
    public void addTarea(TareaEntity tarea) {
        tareaRepository.saveTarea(tarea.getNombreTarea(), tarea.getDescripcionTarea(), tarea.getTipoTarea(),
                tarea.getIdEmergencia());
    }
}