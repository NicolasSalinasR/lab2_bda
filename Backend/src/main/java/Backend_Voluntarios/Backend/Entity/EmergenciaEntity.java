package Backend_Voluntarios.Backend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.springframework.data.geo.Point;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "emergencia")
public class EmergenciaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmergencia;

    private String tipoEmergencia;

    private Point zonaEmergencia;
    private String condicionFisica;
    private String cantidadVoluntariosMinimo;
    private String cantidadVoluntariosMaximo;

    @OneToMany(mappedBy = "emergencia")
    private Set<EmeHabilidadEntity> emergenciaHabilidad = new HashSet<>();

    @OneToMany(mappedBy = "emergencia")
    private Set<TareaEntity> Tarea = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "idInstitucion")
    private InstitucionEntity institucion;

    // Constructor all
    public EmergenciaEntity(String tipoEmergencia, Point zonaEmergencia, String condicionFisica,
            String cantidadVoluntariosMinimo, String cantidadVoluntariosMaximo, InstitucionEntity institucion) {
        this.tipoEmergencia = tipoEmergencia;
        this.zonaEmergencia = zonaEmergencia;
        this.condicionFisica = condicionFisica;
        this.cantidadVoluntariosMinimo = cantidadVoluntariosMinimo;
        this.cantidadVoluntariosMaximo = cantidadVoluntariosMaximo;
        this.institucion = institucion;
    }

    // Constructor empty
    public EmergenciaEntity() {
        super();
    }

    // Getters and Setters
    public Long getIdEmergencia() {
        return idEmergencia;
    }

    public String getTipoEmergencia() {
        return tipoEmergencia;
    }

    public void setTipoEmergencia(String tipoEmergencia) {
        this.tipoEmergencia = tipoEmergencia;
    }

    public Point getZonaEmergencia() {
        return zonaEmergencia;
    }

    public void setZonaEmergencia(Point zonaEmergencia) {
        this.zonaEmergencia = zonaEmergencia;
    }

    public String getCondicionFisica() {
        return condicionFisica;
    }

    public void setCondicionFisica(String condicionFisica) {
        this.condicionFisica = condicionFisica;
    }

    public String getCantidadVoluntariosMinimo() {
        return cantidadVoluntariosMinimo;
    }

    public void setCantidadVoluntariosMinimo(String cantidadVoluntariosMinimo) {
        this.cantidadVoluntariosMinimo = cantidadVoluntariosMinimo;
    }

    public String getCantidadVoluntariosMaximo() {
        return cantidadVoluntariosMaximo;
    }

    public void setCantidadVoluntariosMaximo(String cantidadVoluntariosMaximo) {
        this.cantidadVoluntariosMaximo = cantidadVoluntariosMaximo;
    }

    public InstitucionEntity getInstitucion() {
        return institucion;
    }

    public void setInstitucion(InstitucionEntity institucion) {
        this.institucion = institucion;
    }
}
