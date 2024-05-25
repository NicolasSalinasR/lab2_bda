package bdabackend.bda.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import bdabackend.bda.Entity.TareaEntity;
import bdabackend.bda.Repository.TareaRepository;

@Service
public class TareaService {
    @Autowired
    private TareaRepository tareaRepository;

    public void insertarTarea(String nombre, String descripcion, String tipo, Point zona, Long idEmergencia) {
        tareaRepository.insertarTarea(nombre, descripcion, tipo, zona, idEmergencia);
    }

    public void insertarTareaSinEmergencia(String nombre, String descripcion, String tipo, Point zona) {
        tareaRepository.insertarTareaSinEmergencia(nombre, descripcion, tipo, zona);
    }

    public void eliminarTareaPorId(Long id) {
        tareaRepository.eliminarTareaPorId(id);
    }

    public TareaEntity buscarTareaPorId(Long id) {
        return tareaRepository.buscarTareaPorId(id);
    }

    public List<TareaEntity> listaTarea() {
        return tareaRepository.listaTarea();
    }

    public List<TareaEntity> getRankingTarea(String nombreTarea) {
        return tareaRepository.listRankingTarea(nombreTarea);
    }

}
