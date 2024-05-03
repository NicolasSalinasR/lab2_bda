package Backend_Voluntarios.Backend.Entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tarea")

public class TareaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarea;

    private String nombreTarea;
    private String descripcionTarea;
    private String tipoTarea;

    @OneToMany(mappedBy = "tarea")
    private Set<RankingEntity> Ranking = new HashSet<>();

    @OneToMany(mappedBy = "tarea")
    private Set<TareaHabilidadEntity> TareaHabilidad = new HashSet<>();

    @OneToMany(mappedBy = "tarea")
    private Set<EstadoTareaEntity> EstadoTarea = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "idEmergencia")
    private EmergenciaEntity emergencia;

    // Constructor
    public TareaEntity(String nombreTarea, String descripcionTarea, String tipoTarea) {
        this.nombreTarea = nombreTarea;
        this.descripcionTarea = descripcionTarea;
        this.tipoTarea = tipoTarea;
    }

    public TareaEntity(String nombreTarea, String descripcionTarea, String tipoTarea, EmergenciaEntity emergencia) {
        this.nombreTarea = nombreTarea;
        this.descripcionTarea = descripcionTarea;
        this.tipoTarea = tipoTarea;
        this.emergencia = emergencia;
    }

    // Constructor vacio
    public TareaEntity() {
        super();
    }

    // Getters and Setters
    public Long getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Long idTarea) {
        this.idTarea = idTarea;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getDescripcionTarea() {
        return descripcionTarea;
    }

    public void setDescripcionTarea(String descripcionTarea) {
        this.descripcionTarea = descripcionTarea;
    }

    public String getTipoTarea() {
        return tipoTarea;
    }

    public void setTipoTarea(String tipoTarea) {
        this.tipoTarea = tipoTarea;
    }

    public EmergenciaEntity getEmergencia() {
        return emergencia;
    }

    public void setEmergencia(EmergenciaEntity emergencia) {
        this.emergencia = emergencia;
    }

    public Long getIdEmergencia(){
        return emergencia.getIdEmergencia();
    }
}