package Backend_Voluntarios.Backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Backend_Voluntarios.Backend.Repository.EmergenciaRepository;
import Backend_Voluntarios.Backend.Entity.EmergenciaEntity;

@Service
public class EmergenciaService {
    @Autowired
    private EmergenciaRepository emergenciaRepository;

    public EmergenciaEntity getEmergenciaById(Long id) {
        return emergenciaRepository.findEmergenciaById(id);
    }

    public List<EmergenciaEntity> getAllEmergencias() {
        return emergenciaRepository.findAllEmergencias();
    }

    public void addEmergencia(EmergenciaEntity emergencia) {
        emergenciaRepository.saveEmergencia(emergencia.getTipoEmergencia(),
                emergencia.getZonaEmergencia(),
                emergencia.getCondicionFisica(),
                emergencia.getCantidadVoluntariosMinimo(),
                emergencia.getCantidadVoluntariosMaximo(),
                emergencia.getInstitucion().getIdInstitucion());
    }

}
