package Backend_Voluntarios.Backend.Service;

import Backend_Voluntarios.Backend.Entity.CoordinadorEntity;
import Backend_Voluntarios.Backend.Repository.CoordinadorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoordinadorService {
    @Autowired
    private CoordinadorRepository coordinadorRepository;

    public List<CoordinadorEntity> listaFiltro(String palabraClave) {
        return coordinadorRepository.listaPalabraClave(palabraClave);
    }

    public List<CoordinadorEntity> tablaCompleta() {
        return coordinadorRepository.listTodo();
    }

    public List<CoordinadorEntity> tablaId(Long idCoordinador) {
        return coordinadorRepository.buscarIdCoordinador(idCoordinador);
    }

    public void nuevoCoordinador(CoordinadorEntity coordinadorEntity) {
        coordinadorRepository.crearCoordinador(coordinadorEntity.getNombre(),
                coordinadorEntity.getContrasenaCoordinador(),
                coordinadorEntity.getCorreoCoordinador(), coordinadorEntity.getNumeroDocumentoCoordinador());
    }
    @Transactional
    public void borrarCoordinador(CoordinadorEntity coordinadorEntity) {
        coordinadorRepository.borrarCoordinador(coordinadorEntity.getIdCoordinador());
    }

    public CoordinadorEntity buscarId(Long idCoordinador) {
        return coordinadorRepository.idCoordinador(idCoordinador);
    }

    public CoordinadorEntity buscarPorCorreo(String correoCoordinador) {
        return coordinadorRepository.findByCorreo(correoCoordinador);
    }
}
