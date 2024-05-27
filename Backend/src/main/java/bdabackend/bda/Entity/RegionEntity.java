package bdabackend.bda.Entity;

import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.MultiPolygon;

import jakarta.persistence.*;

@Entity
@Table(name = "regiones_chile")
public class RegionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "geometria")
    public MultiPolygon geometria;

    public RegionEntity(String nombre, MultiPolygon geometria){
        this.nombre = nombre;
        this.geometria = geometria;
    }

    public  RegionEntity(){}
    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public MultiPolygon getGeometria() {
        return geometria;
    }

    public void setGeometria(MultiPolygon geometria) {
        this.geometria = geometria;
    }

}
