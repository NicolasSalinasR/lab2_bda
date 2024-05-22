package Backend_Voluntarios.Backend.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "estadoTarea")
public class EstadoTareaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstadoTarea;

    @ManyToOne
    @JoinColumn(name = "idTarea")
    private TareaEntity tarea;

    private boolean estadoTarea;

    public EstadoTareaEntity(TareaEntity idTarea, boolean estadoTarea) {
        this.tarea = idTarea;
        this.estadoTarea = estadoTarea;
    }

    public EstadoTareaEntity() {
        super();
    }
    // getters

    public Long getIdEstadoTarea() {
        return idEstadoTarea;
    }

    public Long getIdTarea() {
        return tarea.getIdTarea();
    }

    public boolean isEstadoTarea() {
        return estadoTarea;
    }

    // seters

    public void setEstadoTarea(boolean estadoTarea) {
        this.estadoTarea = estadoTarea;
    }

    public void setIdEstadoTarea(Long idEstadoTarea) {
        this.idEstadoTarea = idEstadoTarea;
    }

    public void setIdTarea(TareaEntity idTarea) {
        this.tarea = idTarea;
    }
}
