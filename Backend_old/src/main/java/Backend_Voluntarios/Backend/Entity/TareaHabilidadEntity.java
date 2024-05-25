package Backend_Voluntarios.Backend.Entity;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "tarea_habilidad")

public class TareaHabilidadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idTareaHabilidad;

    @ManyToOne
    @JoinColumn(name = "idTarea")
    private TareaEntity tarea;

    @ManyToOne
    @JoinColumn(name = "idEmeHabilidad")
    private EmeHabilidadEntity emeHabilidad;

    @Column(nullable = false)
    private String habilidadRequerida;

    // Constructor
    public TareaHabilidadEntity(TareaEntity idTarea, EmeHabilidadEntity emeHabilidad, String habilidad_requerida) {
        this.tarea = idTarea;
        this.emeHabilidad = emeHabilidad;
        this.habilidadRequerida = habilidad_requerida;
    }

    // Constructor vacio
    public TareaHabilidadEntity() {
        super();
    }

    // Getters an Setters
    public Long getIdTareaHabilidad() {
        return idTareaHabilidad;
    }

    public void setIdTareaHabilidad(Long idTareaHabilidad) {
        this.idTareaHabilidad = idTareaHabilidad;
    }

    public Long getIdTarea() {
        return tarea.getIdTarea();
    }

    public void setIdTarea(TareaEntity idTarea) {
        this.tarea = idTarea;
    }

    public Long getIdEmeHabilidad() {
        return emeHabilidad.getIdEmergenciaHabilidad();
    }

    public void setIdEmeHabilidad(EmeHabilidadEntity emeHabilidad) {
        this.emeHabilidad = emeHabilidad;
    }

    public String getHabilidadRequerida() {
        return habilidadRequerida;
    }

    public void setHabilidadRequerida(String habilidadRequerida) {
        this.habilidadRequerida = habilidadRequerida;
    }

    public TareaEntity getTarea() {
        return tarea;
    }

    public void setTarea(TareaEntity tarea) {
        this.tarea = tarea;
    }

    public EmeHabilidadEntity getEmeHabilidad() {
        return emeHabilidad;
    }

    public void setEmeHabilidad(EmeHabilidadEntity emeHabilidad) {
        this.emeHabilidad = emeHabilidad;
    }
}
