package bdabackend.bda.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import bdabackend.bda.Entity.VoluntarioEntity;
import bdabackend.bda.Repository.VoluntarioRepository;

import java.util.List;

@Service
public class VoluntarioService {
    @Autowired
    private VoluntarioRepository voluntarioRepository;

    public void insertarVoluntario(VoluntarioEntity voluntario) {
        voluntarioRepository.insertarVoluntario(voluntario.getNombre(), voluntario.getCorreo(),
                voluntario.getNumeroDocumento(), voluntario.getZonaVivienda(), voluntario.getContrasena(),
                voluntario.getEquipamiento());
    }

    public void eliminarVoluntarioPorId(Long id) {
        voluntarioRepository.eliminarVoluntarioPorId(id);
    }

    public VoluntarioEntity buscarVoluntarioPorId(Long id) {
        return voluntarioRepository.buscarVoluntarioPorId(id);
    }

    public VoluntarioEntity buscarPorCorreo(String correo) {
        return voluntarioRepository.buscarPorCorreo(correo);
    }

    public List<VoluntarioEntity> listaVoluntario() {
        return voluntarioRepository.listaVoluntario();
    }
}
