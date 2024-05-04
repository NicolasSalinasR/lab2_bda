package Backend_Voluntarios.Backend.Entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "institucion")
public class InstitucionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInstitucion;


    @OneToMany(mappedBy = "institucion")
    private Set<EmergenciaEntity> emergencia = new HashSet<>();

    private String nombreInstitucion;

    // Constructor all
    public InstitucionEntity(String nombreInstitucion) {
        super();
        this.nombreInstitucion = nombreInstitucion;
    }

    // Constructor empty
    public InstitucionEntity() {
        super();
    }

    // Getters
    public Long getIdInstitucion() {
        return idInstitucion;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

}
