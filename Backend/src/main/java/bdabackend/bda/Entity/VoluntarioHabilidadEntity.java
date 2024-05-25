package bdabackend.bda.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "voluntario_habilidad")
public class VoluntarioHabilidadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_voluntario")
    private VoluntarioEntity voluntario;

    @ManyToOne
    @JoinColumn(name = "id_habilidad")
    private HabilidadEntity habilidad;

    // Constructor
    public VoluntarioHabilidadEntity() {
    }

    public VoluntarioHabilidadEntity(VoluntarioEntity voluntario, HabilidadEntity habilidad) {
        this.voluntario = voluntario;
        this.habilidad = habilidad;
    }

    // Getters and Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VoluntarioEntity getVoluntario() {
        return this.voluntario;
    }

    public void setVoluntario(VoluntarioEntity voluntario) {
        this.voluntario = voluntario;
    }

    public HabilidadEntity getHabilidad() {
        return this.habilidad;
    }

    public void setHabilidad(HabilidadEntity habilidad) {
        this.habilidad = habilidad;
    }
}
