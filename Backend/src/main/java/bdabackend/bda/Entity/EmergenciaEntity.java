package bdabackend.bda.Entity;

import org.springframework.data.geo.Point;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "emergencia")
public class EmergenciaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_emergencia")
    private String tipoEmergencia;

    @Column(name = "zona_emergencia", columnDefinition = "Geometry(Point,4326)")
    private Point zonaEmergencia;

    @Column(name = "condicion_fisica")
    private String condicionFisica;

    @Column(name = "cantidad_voluntarios_min")
    private Integer cantidadVoluntariosMin;

    @Column(name = "cantidad_voluntarios_max")
    private Integer cantidadVoluntariosMax;

    @OneToMany(mappedBy = "emergencia")
    private Set<EmergenciaHabilidadEntity> emergenciaHabilidad = new HashSet<>();

    @OneToMany(mappedBy = "emergencia")
    private Set<TareaEntity> tarea = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_institucion")
    private InstitucionEntity institucion;

    // Constructores
    public EmergenciaEntity() {
    }

    public EmergenciaEntity(String tipoEmergencia, Point zonaEmergencia, String condicionFisica,
            Integer cantidadVoluntariosMin, Integer cantidadVoluntariosMax, InstitucionEntity institucion) {
        this.tipoEmergencia = tipoEmergencia;
        this.zonaEmergencia = zonaEmergencia;
        this.condicionFisica = condicionFisica;
        this.cantidadVoluntariosMin = cantidadVoluntariosMin;
        this.cantidadVoluntariosMax = cantidadVoluntariosMax;
        this.institucion = institucion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getTipoEmergencia() {
        return tipoEmergencia;
    }

    public Point getZonaEmergencia() {
        return zonaEmergencia;
    }

    public String getCondicionFisica() {
        return condicionFisica;
    }

    public Integer getCantidadVoluntariosMin() {
        return cantidadVoluntariosMin;
    }

    public Integer getCantidadVoluntariosMax() {
        return cantidadVoluntariosMax;
    }

    public Set<EmergenciaHabilidadEntity> getEmergenciaHabilidad() {
        return emergenciaHabilidad;
    }

    public Set<TareaEntity> getTarea() {
        return tarea;
    }

    public InstitucionEntity getInstitucion() {
        return institucion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipoEmergencia(String tipoEmergencia) {
        this.tipoEmergencia = tipoEmergencia;
    }

    public void setZonaEmergencia(Point zonaEmergencia) {
        this.zonaEmergencia = zonaEmergencia;
    }

    public void setCondicionFisica(String condicionFisica) {
        this.condicionFisica = condicionFisica;
    }

    public void setCantidadVoluntariosMin(Integer cantidadVoluntariosMin) {
        this.cantidadVoluntariosMin = cantidadVoluntariosMin;
    }

    public void setCantidadVoluntariosMax(Integer cantidadVoluntariosMax) {
        this.cantidadVoluntariosMax = cantidadVoluntariosMax;
    }

    public void setEmergenciaHabilidad(Set<EmergenciaHabilidadEntity> emergenciaHabilidad) {
        this.emergenciaHabilidad = emergenciaHabilidad;
    }

    public void setTarea(Set<TareaEntity> tarea) {
        this.tarea = tarea;
    }

    public void setInstitucion(InstitucionEntity institucion) {
        this.institucion = institucion;
    }

    public void addEmergenciaHabilidad(EmergenciaHabilidadEntity emergenciaHabilidad) {
        this.emergenciaHabilidad.add(emergenciaHabilidad);
    }

    public void removeEmergenciaHabilidad(EmergenciaHabilidadEntity emergenciaHabilidad) {
        this.emergenciaHabilidad.remove(emergenciaHabilidad);
    }

    public void addTarea(TareaEntity tarea) {
        this.tarea.add(tarea);
    }

    public void removeTarea(TareaEntity tarea) {
        this.tarea.remove(tarea);
    }

}
