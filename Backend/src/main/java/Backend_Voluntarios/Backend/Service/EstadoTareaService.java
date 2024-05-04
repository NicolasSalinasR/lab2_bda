package Backend_Voluntarios.Backend.Service;

import Backend_Voluntarios.Backend.Entity.EstadoTareaEntity;
import Backend_Voluntarios.Backend.Repository.EstadoTareaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class EstadoTareaService {
    @Autowired
    private EstadoTareaRepository estadoTareaRepository;

    public List<EstadoTareaEntity> listAll(String palabraClave) {

        return estadoTareaRepository.listAll(palabraClave);

    }

    public EstadoTareaEntity findByIdTarea(Long idTarea) {
        return estadoTareaRepository.findByIdTarea(idTarea);
    }

    public EstadoTareaEntity findById(Long idEstadoTarea) {
        return estadoTareaRepository.encontrarId(idEstadoTarea);
    }

    public List<Long> getTareasSinTerminar() {
        return estadoTareaRepository.findTareasSinTerminar();
    }

    public List<Long> getTareasTerminadas() {
        return estadoTareaRepository.findTareasTerminadas();
    }

    public void eliminarEstadoTarea(Long idEstadoTarea) {
        estadoTareaRepository.borrarEstadoTarea(idEstadoTarea);
    }
    @Transactional
    public void guardarEstadoTarea(EstadoTareaEntity estadoTarea) {
        estadoTareaRepository.guardarEstadoTarea(estadoTarea.getIdTarea(),
                estadoTarea.isEstadoTarea());
    }

    public List<EstadoTareaEntity> getAllEstadoHabilidad() {
        return estadoTareaRepository.findAlls();
    }
}
