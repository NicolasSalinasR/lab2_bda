package Backend_Voluntarios.Backend.Entity;

import jakarta.persistence.*;
import org.springframework.data.geo.Point;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "voluntario")
public class VoluntarioEntity {
    // Se crea el id del voluntario de tipo serial
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVoluntario;

    @Column(nullable = false, length = 100)
    private String nombreVoluntario;

    @Column(nullable = false, length = 100)
    private String correoVoluntario;

    @Column(unique = true)
    private String numeroDocumentoVoluntario;
    @Column(name = "zona_vivienda_voluntario", columnDefinition = "geometry(Point, 4326)")
    private Point zonaViviendaVoluntario;

    @Column(nullable = false, length = 100)
    private String contrasenaVoluntario;

    @Column(nullable = false)
    private String equipamientoVoluntario;


    @OneToMany(mappedBy = "voluntario")
    private Set<RankingEntity> Ranking = new HashSet<>();

    @OneToMany(mappedBy = "voluntario")
    private Set<VoluntarioHabilidadEntity> VoluntarioHabilidad = new HashSet<>();

    public VoluntarioEntity(String nombreVoluntario, String correoVoluntario, String numeroDocumentoVoluntario,
            String equipamientoVoluntario, Point zonaViviendaVoluntario, String contrasenaVoluntario) {
        this.nombreVoluntario = nombreVoluntario;
        this.contrasenaVoluntario = contrasenaVoluntario;
        this.correoVoluntario = correoVoluntario;
        this.numeroDocumentoVoluntario = numeroDocumentoVoluntario;
        this.equipamientoVoluntario = equipamientoVoluntario;
        this.zonaViviendaVoluntario = zonaViviendaVoluntario;
    }

    public VoluntarioEntity() {
        super();
    }

    public String getContrasenaVoluntario() {
        return contrasenaVoluntario;
    }

    public void setContrasenaVoluntario(String contrasenaVoluntario) {
        this.contrasenaVoluntario = contrasenaVoluntario;
    }

    public Long getIdVoluntario() {
        return idVoluntario;
    }

    public String getNombreVoluntario() {
        return nombreVoluntario;
    }

    public void setNombreVoluntario(String nombreVoluntario) {
        this.nombreVoluntario = nombreVoluntario;
    }

    public String getCorreoVoluntario() {
        return correoVoluntario;
    }

    public String getNumeroDocumentoVoluntario() {
        return numeroDocumentoVoluntario;
    }

    public void setNumeroDocumentoVoluntario(String numeroDocumentoVoluntario) {
        this.numeroDocumentoVoluntario = numeroDocumentoVoluntario;
    }

    public void setCorreoVoluntario(String correoVoluntario) {
        this.correoVoluntario = correoVoluntario;
    }

    public Point getZonaViviendaVoluntario() {
        return zonaViviendaVoluntario;
    }

    public void setZonaViviendaVoluntario(Point zona_vivienda_voluntario) {
        this.zonaViviendaVoluntario = zona_vivienda_voluntario;
    }

    public String getEquipamientoVoluntario() {
        return equipamientoVoluntario;
    }

    public void setEquipamientoVoluntario(String equipamiento_voluntario) {
        this.equipamientoVoluntario = equipamiento_voluntario;
    }

    public void setIdVoluntario(Long idVoluntario) {
        this.idVoluntario = idVoluntario;
    }

}
