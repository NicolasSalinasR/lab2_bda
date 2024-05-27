package bdabackend.bda.DTO;

public class RegionDTO {
    private Long id;
    private String nombre;
    private String wkt;

    public RegionDTO(Long id, String nombre, String wkt) {
        this.id = id;
        this.nombre = nombre;
        this.wkt = wkt;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getWkt() {
        return wkt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setWkt(String wkt) {
        this.wkt = wkt;
    }
}
