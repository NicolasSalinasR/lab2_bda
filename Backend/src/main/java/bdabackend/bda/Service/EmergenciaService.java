package bdabackend.bda.Service;

import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import bdabackend.bda.Entity.EmergenciaEntity;
import bdabackend.bda.Repository.EmergenciaRepository;

@Service
public class EmergenciaService {
    @Autowired
    private EmergenciaRepository emergenciaRepository;

    public void insertarEmergencia(String tipoEmergencia, Point zonaEmergencia, String condicionFisica,
                                   int cantidadVoluntariosMin, int cantidadVoluntariosMax, Long institucion) {
        emergenciaRepository.insertarEmergencia(tipoEmergencia, zonaEmergencia, condicionFisica, cantidadVoluntariosMin,
                cantidadVoluntariosMax, institucion);
    }

    public void eliminarEmergenciaPorId(Long id) {
        emergenciaRepository.eliminarEmergenciaPorId(id);
    }

    public EmergenciaEntity buscarEmergenciaPorId(Long id) {
        return emergenciaRepository.buscarEmergenciaPorId(id);
    }

    public List<EmergenciaEntity> listaEmergencia() {
        return emergenciaRepository.listaEmergencia();
    }
}
