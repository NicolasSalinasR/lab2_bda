package bdabackend.bda.Entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "emergencia_habilidad")
public class EmergenciaHabilidadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_emergencia")
    private EmergenciaEntity emergencia;

    @ManyToOne
    @JoinColumn(name = "id_habilidad")
    private HabilidadEntity habilidad;

    @OneToMany(mappedBy = "emergenciaHabilidad")
    private Set<TareaHabilidadEntity> tareaHabilidad = new HashSet<>();

    // Constructores
    public EmergenciaHabilidadEntity() {
    }

    public EmergenciaHabilidadEntity(EmergenciaEntity emergencia, HabilidadEntity habilidad) {
        this.emergencia = emergencia;
        this.habilidad = habilidad;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public EmergenciaEntity getEmergencia() {
        return emergencia;
    }

    public HabilidadEntity getHabilidad() {
        return habilidad;
    }

    public Set<TareaHabilidadEntity> getTareaHabilidad() {
        return tareaHabilidad;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmergencia(EmergenciaEntity emergencia) {
        this.emergencia = emergencia;
    }

    public void setHabilidad(HabilidadEntity habilidad) {
        this.habilidad = habilidad;
    }

    public void setTareaHabilidad(Set<TareaHabilidadEntity> tareaHabilidad) {
        this.tareaHabilidad = tareaHabilidad;
    }

    public void addTareaHabilidad(TareaHabilidadEntity tareaHabilidad) {
        this.tareaHabilidad.add(tareaHabilidad);
    }

    public void removeTareaHabilidad(TareaHabilidadEntity tareaHabilidad) {
        this.tareaHabilidad.remove(tareaHabilidad);
    }
}
