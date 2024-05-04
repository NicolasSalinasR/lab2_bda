package Backend_Voluntarios.Backend.Entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "eme_habilidad")
public class EmeHabilidadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idEmergenciaHabilidad;

    @ManyToOne
    @JoinColumn(name = "idEmergencia")
    private EmergenciaEntity emergencia;

    @ManyToOne
    @JoinColumn(name = "idHabilidad")
    private HabilidadEntity habilidad;

    @OneToMany(mappedBy = "emeHabilidad")
    private Set<TareaHabilidadEntity> TareaHabilidad = new HashSet<>();

    // Constructor all

    public EmeHabilidadEntity(EmergenciaEntity idEmergencia, HabilidadEntity idHabilidad) {
        this.emergencia = idEmergencia;
        this.habilidad = idHabilidad;

    }

    // Constructor empty
    public EmeHabilidadEntity() {
        super();
    }

    // Getters
    public Long getIdEmergenciaHabilidad() {
        return idEmergenciaHabilidad;
    }

    public void setIdEmergenciaHabilidad(Long idEmergenciaHabilidad) {
        this.idEmergenciaHabilidad = idEmergenciaHabilidad;
    }

    public Long getIdHabilidad() {
        return habilidad.getIdhabilidad();
    }

    public Long getIdEmergencia() {
        return emergencia.getIdEmergencia();
    }

    public EmergenciaEntity getEmergencia() {
        return emergencia;
    }

    public HabilidadEntity getHabilidad() {
        return habilidad;
    }

    // Setters

    public void setIdHabilidad(HabilidadEntity idHabilidad) {
        this.habilidad = idHabilidad;
    }

    public void setIdEmergencia(EmergenciaEntity idEmergencia) {
        this.emergencia = idEmergencia;
    }

}
