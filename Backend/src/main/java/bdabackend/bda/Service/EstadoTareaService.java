package bdabackend.bda.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bdabackend.bda.Entity.EstadoTareaEntity;
import bdabackend.bda.Repository.EstadoTareaRepository;

@Service
public class EstadoTareaService {
    @Autowired
    private EstadoTareaRepository estadoTareaRepository;

    public void insertarEstadoTarea(Long idTarea, String estadoTarea) {
        estadoTareaRepository.insertarEstadoTarea(idTarea, estadoTarea);
    }

    public void eliminarEstadoTareaPorId(Long id) {
        estadoTareaRepository.eliminarEstadoTareaPorId(id);
    }

    public EstadoTareaEntity buscarEstadoTareaPorId(Long id) {
        return estadoTareaRepository.buscarEstadoTareaPorId(id);
    }
}
