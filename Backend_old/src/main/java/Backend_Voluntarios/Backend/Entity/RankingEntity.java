package Backend_Voluntarios.Backend.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ranking")
public class RankingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRanking;

    @ManyToOne
    @JoinColumn(name = "idTarea")
    private TareaEntity tarea;
    // private Long idTarea;

    @ManyToOne
    @JoinColumn(name = "idVoluntario")
    private VoluntarioEntity voluntario;

    private String nombreVoluntario;

    private String numeroDocumentoVoluntario;

    private Integer nivelRanking;

    private String tareaRanking;

    public RankingEntity(TareaEntity idTarea, VoluntarioEntity idVoluntario, String nombreVoluntario,
            String numeroDocumentoVoluntario, Integer nivelRanking, String tareaRanking) {
        this.tarea = idTarea;
        this.voluntario = idVoluntario;
        this.nombreVoluntario = nombreVoluntario;
        this.numeroDocumentoVoluntario = numeroDocumentoVoluntario;
        this.nivelRanking = nivelRanking;
        this.tareaRanking = tareaRanking;
    }

    public RankingEntity() {
        super();
    }

    public Long getIdRanking() {
        return idRanking;
    }

    public VoluntarioEntity getVoluntario() {
        return voluntario;
    }

    public String getNumeroDocumentoVoluntario() {
        return numeroDocumentoVoluntario;
    }

    public void setNumeroDocumentoVoluntario(String numeroDocumentoVoluntario) {
        this.numeroDocumentoVoluntario = numeroDocumentoVoluntario;
    }

    public String getNombreVoluntario() {
        return nombreVoluntario;
    }

    public void setNombreVoluntario(String nombreVoluntario) {
        this.nombreVoluntario = nombreVoluntario;
    }

    public void setVoluntario(VoluntarioEntity voluntario) {
        this.voluntario = voluntario;
    }

    public TareaEntity getTarea() {
        return tarea;
    }

    public void setTarea(TareaEntity tarea) {
        this.tarea = tarea;
    }

    public String getTareaRanking() {
        return tareaRanking;
    }

    public void setTareaRanking(String tarea_ranking) {
        this.tareaRanking = tarea_ranking;
    }

    public Integer getNivelRanking() {
        return nivelRanking;
    }

    public void setNivelRanking(Integer nivel_ranking) {
        this.nivelRanking = nivel_ranking;
    }

    public Long getIdVoluntario() {
        return voluntario.getIdVoluntario();
    }

    public void setIdVoluntario(VoluntarioEntity Voluntario) {
        this.voluntario = Voluntario;
    }

    public Long getIdTarea() {
        return tarea.getIdTarea();
    }

    public void setIdTarea(TareaEntity tarea) {
        this.tarea = tarea;
    }

    public void setIdRanking(Long idRanking) {
        this.idRanking = idRanking;
    }
}
