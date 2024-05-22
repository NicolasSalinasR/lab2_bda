package Backend_Voluntarios.Backend.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "coordinador")
public class CoordinadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCoordinador;

    private String correoCoordinador;

    private String contrasenaCoordinador;

    private String nombre;

    private String numeroDocumentoCoordinador;

    public CoordinadorEntity(String nombre, String contrasenaCoordinador, String correoCoordinador,
            String numeroDocumentoCoordinador) {
        this.nombre = nombre;
        this.contrasenaCoordinador = contrasenaCoordinador;
        this.correoCoordinador = correoCoordinador;
        this.numeroDocumentoCoordinador = numeroDocumentoCoordinador;
    }

    public String getCorreoCoordinador() {
        return correoCoordinador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenaCoordinador() {
        return contrasenaCoordinador;
    }

    public void setContrasenaCoordinador(String contrasenaCoordinador) {
        this.contrasenaCoordinador = contrasenaCoordinador;
    }

    public void setCorreoCoordinador(String correoCoordinador) {
        this.correoCoordinador = correoCoordinador;
    }

    public void setIdCoordinador(Long idCoordinador) {
        this.idCoordinador = idCoordinador;
    }

    public CoordinadorEntity() {
        super();
    }

    public Long getIdCoordinador() {
        return idCoordinador;
    }

    public String getNumeroDocumentoCoordinador() {
        return numeroDocumentoCoordinador;
    }

    public void setNumeroDocumentoCoordinador(String numeroDocumentoCoordinador) {
        this.numeroDocumentoCoordinador = numeroDocumentoCoordinador;
    }
}