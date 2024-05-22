package bdabackend.bda.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ranking")
public class RankingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nivel")
    private String nivel;

    @Column(name = "tarea_ranking")
    private String tareaRanking;

    @Column(name = "nombre_voluntario")
    private String nombreVoluntario;

    @Column(name = "numero_docuemto_voluntario")
    private String numeroDocumentoVoluntario;

    @ManyToOne
    @JoinColumn(name = "id_tarea")
    private TareaEntity tarea;

    @ManyToOne
    @JoinColumn(name = "id_voluntario")
    private VoluntarioEntity voluntario;

    // Constructor
    public RankingEntity() {
    }

    public RankingEntity(String nivel, String tareaRanking, String nombreVoluntario, String numeroDocumentoVoluntario) {
        this.nivel = nivel;
        this.tareaRanking = tareaRanking;
        this.nombreVoluntario = nombreVoluntario;
        this.numeroDocumentoVoluntario = numeroDocumentoVoluntario;
    }

    // Getters and Setters

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNivel() {
        return this.nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getTareaRanking() {
        return this.tareaRanking;
    }

    public void setTareaRanking(String tareaRanking) {
        this.tareaRanking = tareaRanking;
    }

    public String getNombreVoluntario() {
        return this.nombreVoluntario;
    }

    public void setNombreVoluntario(String nombreVoluntario) {
        this.nombreVoluntario = nombreVoluntario;
    }

    public String getNumeroDocumentoVoluntario() {
        return this.numeroDocumentoVoluntario;
    }

    public void setNumeroDocumentoVoluntario(String numeroDocumentoVoluntario) {
        this.numeroDocumentoVoluntario = numeroDocumentoVoluntario;
    }

    public TareaEntity getTarea() {
        return this.tarea;
    }

    public void setTarea(TareaEntity tarea) {
        this.tarea = tarea;
    }

    public VoluntarioEntity getVoluntario() {
        return this.voluntario;
    }

    public void setVoluntario(VoluntarioEntity voluntario) {
        this.voluntario = voluntario;
    }
}
