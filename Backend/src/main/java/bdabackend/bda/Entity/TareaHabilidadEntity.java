package bdabackend.bda.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tarea_habilidad")
public class TareaHabilidadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hablididad_requerida")
    private String habilidadRequerida;

    @ManyToOne
    @JoinColumn(name = "id_tarea")
    private TareaEntity tarea;

    @ManyToOne
    @JoinColumn(name = "id_emergencia_habilidad")
    private EmergenciaHabilidadEntity emergenciaHabilidad;

    // Constructor
    public TareaHabilidadEntity() {
    }

    public TareaHabilidadEntity(String habilidadRequerida) {
        this.habilidadRequerida = habilidadRequerida;
    }

    // Getters and Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHabilidadRequerida() {
        return this.habilidadRequerida;
    }

    public void setHabilidadRequerida(String habilidadRequerida) {
        this.habilidadRequerida = habilidadRequerida;
    }

    public TareaEntity getTarea() {
        return this.tarea;
    }

    public void setTarea(TareaEntity tarea) {
        this.tarea = tarea;
    }

    public EmergenciaHabilidadEntity getEmergenciaHabilidad() {
        return this.emergenciaHabilidad;
    }

    public void setEmergenciaHabilidad(EmergenciaHabilidadEntity emergenciaHabilidad) {
        this.emergenciaHabilidad = emergenciaHabilidad;
    }

}
