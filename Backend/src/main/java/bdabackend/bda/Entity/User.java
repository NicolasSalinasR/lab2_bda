package bdabackend.bda.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class User implements UserDetails {
    private String correo;
    private String nombre;
    private String contrasena;
    private String numeroDocumento;
    private String ROL;

    public User(String correo, String nombre, String contrasena, String numeroDocumento) {
        this.correo = correo;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.numeroDocumento = numeroDocumento;
    }

    public User() {
    }

    @Override
    public String getUsername() {
        return correo;
    }

    public void setUsername(String correo) {
        this.correo = correo;
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    public void setPassword(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getROL() {
        return ROL;
    }

    public void setROL(String ROL) {
        this.ROL = ROL;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(ROL));
    }

    public void setAuthorities(String ROL) {
        this.ROL = ROL;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static User voluntarioToUser(VoluntarioEntity voluntario) {
        User user = new User(voluntario.getCorreo(), voluntario.getNombre(),
                voluntario.getContrasena(), voluntario.getNumeroDocumento());
        user.setROL("VOLUNTARIO");
        return user;
    }

    public static User coordinadorToUser(CoordinadorEntity coordinador) {
        User user = new User(coordinador.getCorreoCoordinador(), coordinador.getNombre(),
                coordinador.getContrasena(), coordinador.getNumeroDocumento());
        user.setROL("COORDINADOR");
        return user;
    }

    public Map<String, Object> generateExtraClaims() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("nombreVoluntario", this.nombre);
        claims.put("numeroDocumentoVoluntario", this.numeroDocumento);
        return claims;
    }

}
