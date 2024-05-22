package bdabackend.bda.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "coordinador")
public class CoordinadorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo")
    private String correoCoordinador;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    // Constructores
    public CoordinadorEntity() {
    }

    public CoordinadorEntity(String nombre, String correoCoordinador, String contrasena, String numeroDocumento) {
        this.nombre = nombre;
        this.correoCoordinador = correoCoordinador;
        this.contrasena = contrasena;
        this.numeroDocumento = numeroDocumento;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreoCoordinador() {
        return correoCoordinador;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreoCoordinador(String correoCoordinador) {
        this.correoCoordinador = correoCoordinador;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
}
