package bdabackend.bda.Service;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import bdabackend.bda.Entity.EmergenciaHabilidadEntity;
import bdabackend.bda.Repository.EmergenciaHabilidadRepository;

@Service
public class EmergenciaHabilidadSevice {
    @Autowired
    private EmergenciaHabilidadRepository emergenciaHabilidadRepository;

    public void insertarEmergenciaHabilidad(Long id_habilidad, Long id_emergencia) {
        emergenciaHabilidadRepository.insertarEmergenciaHabilidad(id_habilidad, id_emergencia);
    }

    public void eliminarEmergenciaHabilidadPorId(Long id) {
        emergenciaHabilidadRepository.eliminarEmergenciaHabilidadPorId(id);
    }

    public EmergenciaHabilidadEntity buscarEmergenciaHabilidadPorId(Long id) {
        return emergenciaHabilidadRepository.buscarEmergenciaHabilidadPorId(id);
    }

    public List<EmergenciaHabilidadEntity> listaEmergenciaHabilidad() {
        return emergenciaHabilidadRepository.listaEmergenciaHabilidad();
    }
}
