package bdabackend.bda.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bdabackend.bda.Repository.InstitucionRepository;

@Service
public class InstitucionService {
    @Autowired
    private InstitucionRepository institucionRepository;

    public void insertarInstitucion(String nombre) {
        institucionRepository.insertarInstitucion(nombre);
    }

    public void eliminarInstitucionPorId(Long id) {
        institucionRepository.eliminarInstitucionPorId(id);
    }

    public List<?> buscarInstitucionPorId(Long id) {
        return institucionRepository.buscarInstitucionPorId(id);
    }

    public List<?> listaInstitucion() {
        return institucionRepository.listaInstitucion();
    }
}