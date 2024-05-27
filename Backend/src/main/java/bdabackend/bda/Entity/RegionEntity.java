package bdabackend.bda.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import org.locationtech.jts.geom.MultiPolygon;

@Entity
@Table(name = "regiones_chile")
public class RegionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "geometria", columnDefinition = "geometry(MultiPolygon,4326)")
    private MultiPolygon geometria;

    public RegionEntity() {
    }

    public RegionEntity(String nombre, MultiPolygon geometria) {
        this.nombre = nombre;
        this.geometria = geometria;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public MultiPolygon getGeometria() {
        return geometria;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setGeometria(MultiPolygon geometria) {
        this.geometria = geometria;
    }
}
