package bdabackend.bda.Entity;

import jakarta.persistence.*;
import org.springframework.data.geo.Point;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "voluntario")
public class VoluntarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo")
    private String correo;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @Column(name = "zona_vivienda", columnDefinition = "geometry(Point,4326)")
    private Point zonaVivienda;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "equipamiento")
    private String equipamiento;

    @OneToMany(mappedBy = "voluntario")
    private Set<RankingEntity> ranking = new HashSet<>();

    @OneToMany(mappedBy = "voluntario")
    private Set<VoluntarioHabilidadEntity> voluntarioHabilidad = new HashSet<>();

    // Constructor
    public VoluntarioEntity() {
    }

    public VoluntarioEntity(String nombre, String correo, String numeroDocumento, Point zonaVivienda,
            String contrasena, String equipamiento) {
        this.nombre = nombre;
        this.correo = correo;
        this.numeroDocumento = numeroDocumento;
        this.zonaVivienda = zonaVivienda;
        this.contrasena = contrasena;
        this.equipamiento = equipamiento;
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

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumeroDocumento() {
        return this.numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getZonaVivienda() {
        return this.zonaVivienda;
    }

    public void setZonaVivienda(String zonaVivienda) {
        this.zonaVivienda = zonaVivienda;
    }

    public String getContrasena() {
        return this.contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEquipamiento() {
        return this.equipamiento;
    }

    public void setEquipamiento(String equipamiento) {
        this.equipamiento = equipamiento;
    }

    public Set<RankingEntity> getRanking() {
        return this.ranking;
    }

    public void setRanking(Set<RankingEntity> ranking) {
        this.ranking = ranking;
    }

    public Set<VoluntarioHabilidadEntity> getVoluntarioHabilidad() {
        return this.voluntarioHabilidad;
    }

    public void setVoluntarioHabilidad(Set<VoluntarioHabilidadEntity> voluntarioHabilidad) {
        this.voluntarioHabilidad = voluntarioHabilidad;
    }

    public void addRanking(RankingEntity ranking) {
        this.ranking.add(ranking);
    }

    public void removeRanking(RankingEntity ranking) {
        this.ranking.remove(ranking);
    }

    public void addVoluntarioHabilidad(VoluntarioHabilidadEntity voluntarioHabilidad) {
        this.voluntarioHabilidad.add(voluntarioHabilidad);
    }

    public void removeVoluntarioHabilidad(VoluntarioHabilidadEntity voluntarioHabilidad) {
        this.voluntarioHabilidad.remove(voluntarioHabilidad);
    }
}
