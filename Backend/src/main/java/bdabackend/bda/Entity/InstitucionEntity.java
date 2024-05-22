package bdabackend.bda.Entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "institucion")
public class InstitucionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "institucion")
    private Set<EmergenciaEntity> emergencia = new HashSet<>();

    // Constructores
    public InstitucionEntity() {
    }

    public InstitucionEntity(String nombre) {
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

    public Set<EmergenciaEntity> getEmergencia() {
        return emergencia;
    }

    public void setEmergencia(Set<EmergenciaEntity> emergencia) {
        this.emergencia = emergencia;
    }

    public void addEmergencia(EmergenciaEntity emergencia) {
        this.emergencia.add(emergencia);
    }

    public void removeEmergencia(EmergenciaEntity emergencia) {
        this.emergencia.remove(emergencia);
    }

    public void clearEmergencia() {
        this.emergencia.clear();
    }
}
