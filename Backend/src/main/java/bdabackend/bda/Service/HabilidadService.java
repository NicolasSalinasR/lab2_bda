package bdabackend.bda.Service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import bdabackend.bda.Entity.HabilidadEntity;
import bdabackend.bda.Repository.HabilidadRepository;

import java.util.List;

@Service
public class HabilidadService {
    @Autowired
    private HabilidadRepository habilidadRepository;

    public void insertarHabilidad(String nombre) {
        habilidadRepository.insertarHabilidad(nombre);
    }

    public void eliminarHabilidadPorId(Long id) {
        habilidadRepository.eliminarHabilidadPorId(id);
    }

    public HabilidadEntity buscarHabilidadPorId(Long id) {
        return habilidadRepository.buscarHabilidadPorId(id);
    }

    public List<HabilidadEntity> listaHabilidad() {
        return habilidadRepository.listaHabilidad();
    }
}
