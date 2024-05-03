package Backend_Voluntarios.Backend.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "voluntario_habilidad")
public class VoluntarioHabilidadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVoluntarioHabilidad;

    @ManyToOne
    @JoinColumn(name = "idVoluntario")
    private VoluntarioEntity voluntario;

    @ManyToOne
    @JoinColumn(name = "idHabilidad")
    private HabilidadEntity habilidad;

    public VoluntarioHabilidadEntity(VoluntarioEntity idVoluntario, HabilidadEntity idHabilidad) {
        super();
        this.voluntario = idVoluntario;
        this.habilidad = idHabilidad;
    }

    public VoluntarioHabilidadEntity() {
        super();
    }

    public Long getIdVoluntarioHabilidad() {
        return idVoluntarioHabilidad;
    }

    public Long getIdVoluntario() {
        return voluntario.getIdVoluntario();
    }

    public Long getIdHabilidad() {
        return habilidad.getIdhabilidad();
    }

    public void setIdVoluntario(VoluntarioEntity idVoluntario) {
        this.voluntario = idVoluntario;
    }

    public void setIdHabilidad(HabilidadEntity idHabilidad) {
        this.habilidad = idHabilidad;
    }

    public VoluntarioEntity getVoluntario() {
        return this.voluntario;
    }

    public HabilidadEntity getHabilidad() {
        return this.habilidad;
    }
}