package bdabackend.bda.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bdabackend.bda.Entity.VoluntarioHabilidadEntity;
import bdabackend.bda.Repository.VoluntarioHabilidadRepository;

import java.util.List;

@Service
public class VoluntarioHabilidadService {
    @Autowired
    private VoluntarioHabilidadRepository voluntarioHabilidadRepository;

    public void insertarVoluntarioHabilidad(Long id_habilidad, Long id_voluntario) {
        voluntarioHabilidadRepository.insertarVoluntarioHabilidad(id_habilidad, id_voluntario);
    }

    public void eliminarVoluntarioHabilidadPorId(Long id) {
        voluntarioHabilidadRepository.eliminarVoluntarioHabilidadPorId(id);
    }

    public VoluntarioHabilidadEntity buscarVoluntarioHabilidadPorId(Long id) {
        return voluntarioHabilidadRepository.buscarVoluntarioHabilidadPorId(id);
    }

    public List<VoluntarioHabilidadEntity> listaVoluntarioHabilidad() {
        return voluntarioHabilidadRepository.listaVoluntarioHabilidad();
    }
}
