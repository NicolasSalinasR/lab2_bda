package bdabackend.bda.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegionDto {
    private Long id;
    private String nombre;
    private String wkt;

    public RegionDto(Long id, String nombre, String wkt) {
        this.id = id;
        this.nombre = nombre;
        this.wkt = wkt;
    }

    // Getters y setters
    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @JsonProperty("geometria")
    public String getGeometria() {
        return wkt;
    }

    public void setGeometria(String geometria) {
        this.wkt = geometria;
    }
}
