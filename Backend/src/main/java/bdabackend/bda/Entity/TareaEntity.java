package bdabackend.bda.Entity;

import jakarta.persistence.*;
import org.springframework.data.geo.Point;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tarea")
public class TareaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "zona", columnDefinition = "geometry(Point,4326)")
    private Point zona;

    @OneToMany(mappedBy = "tarea")
    private Set<RankingEntity> ranking = new HashSet<>();

    @OneToMany(mappedBy = "tarea")
    private Set<TareaHabilidadEntity> tareaHabilidad = new HashSet<>();

    @OneToMany(mappedBy = "tarea")
    private Set<EstadoTareaEntity> estadoTarea = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_emergencia")
    private EmergenciaEntity emergencia;

    // Constructor
    public TareaEntity() {
    }

    public TareaEntity(String nombre, String descripcion, String tipo, Point zona) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.zona = zona;
    }

    // Getters and Setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Point getZona() {
        return this.zona;
    }

    public void setZona(Point zona) {
        this.zona = zona;
    }

    public Set<RankingEntity> getRanking() {
        return this.ranking;
    }

    public void setRanking(Set<RankingEntity> ranking) {
        this.ranking = ranking;
    }

    public Set<TareaHabilidadEntity> getTareaHabilidad() {
        return this.tareaHabilidad;
    }

    public void setTareaHabilidad(Set<TareaHabilidadEntity> tareaHabilidad) {
        this.tareaHabilidad = tareaHabilidad;
    }

    public Set<EstadoTareaEntity> getEstadoTarea() {
        return this.estadoTarea;
    }

    public void setEstadoTarea(Set<EstadoTareaEntity> estadoTarea) {
        this.estadoTarea = estadoTarea;
    }

    public EmergenciaEntity getEmergencia() {
        return this.emergencia;
    }

    public void setEmergencia(EmergenciaEntity emergencia) {
        this.emergencia = emergencia;
    }

    public void addRanking(RankingEntity rankingEntity) {
        this.ranking.add(rankingEntity);
        rankingEntity.setTarea(this);
    }

    public void removeRanking(RankingEntity rankingEntity) {
        this.ranking.remove(rankingEntity);
        rankingEntity.setTarea(null);
    }

    public void addTareaHabilidad(TareaHabilidadEntity tareaHabilidadEntity) {
        this.tareaHabilidad.add(tareaHabilidadEntity);
        tareaHabilidadEntity.setTarea(this);
    }

    public void removeTareaHabilidad(TareaHabilidadEntity tareaHabilidadEntity) {
        this.tareaHabilidad.remove(tareaHabilidadEntity);
        tareaHabilidadEntity.setTarea(null);
    }

    public void addEstadoTarea(EstadoTareaEntity estadoTareaEntity) {
        this.estadoTarea.add(estadoTareaEntity);
        estadoTareaEntity.setTarea(this);
    }

    public void removeEstadoTarea(EstadoTareaEntity estadoTareaEntity) {
        this.estadoTarea.remove(estadoTareaEntity);
        estadoTareaEntity.setTarea(null);
    }

}
