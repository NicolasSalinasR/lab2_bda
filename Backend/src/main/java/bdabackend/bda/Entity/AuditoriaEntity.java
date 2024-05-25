package bdabackend.bda.Entity;

import jakarta.persistence.*;

public class AuditoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idUsuario;
    private String tipoOperacion;
    private String descripcion;

    public AuditoriaEntity() {
    }

    public AuditoriaEntity(Long idUsuario, String tipoOperacion, String descripcion) {
        this.idUsuario = idUsuario;
        this.tipoOperacion = tipoOperacion;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
