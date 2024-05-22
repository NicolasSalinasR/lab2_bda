package bdabackend.bda.Entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "habilidad")
public class HabilidadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "habilidad")
    private Set<VoluntarioHabilidadEntity> voluntarioHabilidad = new HashSet<>();

    @OneToMany(mappedBy = "habilidad")
    private Set<EmergenciaHabilidadEntity> emergenciaHabilidad = new HashSet<>();

    // Constructores
    public HabilidadEntity() {
    }

    public HabilidadEntity(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<VoluntarioHabilidadEntity> getVoluntarioHabilidad() {
        return voluntarioHabilidad;
    }

    public void setVoluntarioHabilidad(Set<VoluntarioHabilidadEntity> voluntarioHabilidad) {
        this.voluntarioHabilidad = voluntarioHabilidad;
    }

    public Set<EmergenciaHabilidadEntity> getEmergenciaHabilidad() {
        return emergenciaHabilidad;
    }

    public void setEmergenciaHabilidad(Set<EmergenciaHabilidadEntity> emergenciaHabilidad) {
        this.emergenciaHabilidad = emergenciaHabilidad;
    }

    public void addVoluntarioHabilidad(VoluntarioHabilidadEntity voluntarioHabilidad) {
        this.voluntarioHabilidad.add(voluntarioHabilidad);
    }

    public void addEmergenciaHabilidad(EmergenciaHabilidadEntity emergenciaHabilidad) {
        this.emergenciaHabilidad.add(emergenciaHabilidad);
    }

    public void removeVoluntarioHabilidad(VoluntarioHabilidadEntity voluntarioHabilidad) {
        this.voluntarioHabilidad.remove(voluntarioHabilidad);
    }

    public void removeEmergenciaHabilidad(EmergenciaHabilidadEntity emergenciaHabilidad) {
        this.emergenciaHabilidad.remove(emergenciaHabilidad);
    }
}
