package bdabackend.bda.Service;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import bdabackend.bda.Entity.CoordinadorEntity;
import bdabackend.bda.Repository.CoordinadorRepository;

@Service
public class CoordinadorService {
    @Autowired
    private CoordinadorRepository coordinadorRepository;

    public void insertarCoordinador(CoordinadorEntity coordinador) {
        coordinadorRepository.insertarCoordinador(coordinador.getNombre(), coordinador.getContrasena(),
                coordinador.getCorreoCoordinador(), coordinador.getNumeroDocumento());
    }

    public void eliminarCoordinadorPorId(Long id) {
        coordinadorRepository.eliminarCoordinadorPorId(id);
    }

    public CoordinadorEntity buscarCoordinadorPorId(Long id) {
        return coordinadorRepository.buscarCoordinadorPorId(id);
    }

    public CoordinadorEntity buscarCoordinadorPorCorreo(String correo) {
        return coordinadorRepository.buscarCoordinadorPorCorreo(correo);
    }

    public List<CoordinadorEntity> listaCoordinadores() {
        return coordinadorRepository.listaCoordinadores();
    }

    public List<CoordinadorEntity> listaPalabraClave(String palabra) {
        return coordinadorRepository.listaPalabraClave(palabra);
    }
}
