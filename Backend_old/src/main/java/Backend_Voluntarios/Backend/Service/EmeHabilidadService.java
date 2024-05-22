package Backend_Voluntarios.Backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Backend_Voluntarios.Backend.Repository.EmeHabilidadRepository;
import Backend_Voluntarios.Backend.Entity.EmeHabilidadEntity;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmeHabilidadService {

    @Autowired
    private EmeHabilidadRepository emeHabilidadRepository;

    public EmeHabilidadEntity getEmeHabilidadById(Long id) {
        return emeHabilidadRepository.findEmeHabilidadById(id);
    }

    public List<EmeHabilidadEntity> getAllEmeHabilidades() {
        return emeHabilidadRepository.findAllEmeHabilidades();
    }
    @Transactional
    public void addEmeHabilidad(EmeHabilidadEntity emeHabilidad) {
        emeHabilidadRepository.saveEmeHabilidad(emeHabilidad.getIdHabilidad(),
                emeHabilidad.getIdEmergencia());
    }

    public List<EmeHabilidadEntity> listaFiltro(String palabraClave) {
        return emeHabilidadRepository.findAll(palabraClave);
    }

    // public EmeHabilidadEntity deleteEmeHabilidad(EmeHabilidadEntity emeHabilidad)
    // {
    // return
    // emeHabilidadRepository.deleteEmeHabilidad(emeHabilidad.getIdEmergenciaHabilidad());
    // }

}
