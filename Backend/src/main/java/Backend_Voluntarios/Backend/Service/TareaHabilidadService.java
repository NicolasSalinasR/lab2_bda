package Backend_Voluntarios.Backend.Service;

import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Backend_Voluntarios.Backend.Repository.TareaHabilidadRepository;
import Backend_Voluntarios.Backend.Entity.TareaHabilidadEntity;

@Service
public class TareaHabilidadService {
    @Autowired
    private TareaHabilidadRepository tareaHabilidadRepository;

    public TareaHabilidadEntity getTareaHabilidadById(Long id) {
        return tareaHabilidadRepository.findTareaHabilidadById(id);
    }

    public List<TareaHabilidadEntity> getAllTareaHabilidades() {
        return tareaHabilidadRepository.findAllTareaHabilidad();
    }
    @Transactional
    public void addTareaHabilidad(TareaHabilidadEntity tareaHabilidad) {
        tareaHabilidadRepository.saveTareaHabilidad(tareaHabilidad.getIdTarea(), tareaHabilidad.getIdEmeHabilidad(),
                tareaHabilidad.getHabilidadRequerida());
    }
}
