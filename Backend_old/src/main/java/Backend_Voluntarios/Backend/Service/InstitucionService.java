package Backend_Voluntarios.Backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Backend_Voluntarios.Backend.Repository.InstitucionRepository;
import Backend_Voluntarios.Backend.Entity.InstitucionEntity;

@Service
public class InstitucionService {
    @Autowired
    private InstitucionRepository institucionRepository;

    public InstitucionEntity getInstitucionById(Long id) {
        return institucionRepository.findInstitucionById(id);
    }

    public List<InstitucionEntity> getAllInstituciones() {
        return institucionRepository.findAllInstituciones();
    }

    public void addInstitucion(InstitucionEntity institucion) {
        institucionRepository.saveInstitucion(institucion.getNombreInstitucion());
    }

    public List<InstitucionEntity> listaFiltro(String palabraClave) {
        return institucionRepository.findAll(palabraClave);
    }

    // public InstitucionEntity deleteInstitucion(InstitucionEntity institucion) {
    // return
    // institucionRepository.deleteInstitucion(institucion.getIdInstitucion());
    // }
}
