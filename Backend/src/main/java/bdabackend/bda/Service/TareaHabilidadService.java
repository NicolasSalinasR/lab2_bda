package bdabackend.bda.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bdabackend.bda.Entity.TareaHabilidadEntity;
import bdabackend.bda.Repository.TareaHabilidadRepository;

@Service
public class TareaHabilidadService {
    @Autowired
    private TareaHabilidadRepository tareaHabilidadRepository;

    public void insertarTareaHabilidad(String habilidadRequerida, Long idTarea, Long idEmerrgenciaHabilidad) {
        tareaHabilidadRepository.insertarTareaHabilidad(habilidadRequerida, idTarea, idEmerrgenciaHabilidad);
    }

    public void insertarTareaHabilidadSinTareaEmergencia(String habilidadRequerida) {
        tareaHabilidadRepository.insertarTareaHabilidadSinTareaEmergencia(habilidadRequerida);
    }

    public void eliminarTareaHabilidadPorId(Long id) {
        tareaHabilidadRepository.eliminarTareaHabilidadPorId(id);
    }

    public TareaHabilidadEntity buscarTareaHabilidadPorId(Long id) {
        return tareaHabilidadRepository.buscarTareaHabilidadPorId(id);
    }
}
