package Backend_Voluntarios.Backend.Entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "habilidad")
public class HabilidadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idHabilidad;

    private String nombreHabilidad;

    @OneToMany(mappedBy = "habilidad")
    private Set<VoluntarioHabilidadEntity> VoluntarioHabilidad = new HashSet<>();

    @OneToMany(mappedBy = "habilidad")
    private Set<EmeHabilidadEntity> EmergenciaHabilidad = new HashSet<>();

    public HabilidadEntity(String nombreHabilidad) {
        this.nombreHabilidad = nombreHabilidad;
    }

    // constructor vacio
    public HabilidadEntity() {
        super();
    }

    // geters
    public Long getIdhabilidad() {
        return idHabilidad;
    }

    public String getNombreHabilidad() {
        return nombreHabilidad;
    }

    // seter
    public void setNombreHabilidad(String nombreHabilidad) {
        this.nombreHabilidad = nombreHabilidad;
    }
}
