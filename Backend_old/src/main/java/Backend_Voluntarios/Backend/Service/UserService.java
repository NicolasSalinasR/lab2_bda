package Backend_Voluntarios.Backend.Service;

import org.springframework.stereotype.Service;
import Backend_Voluntarios.Backend.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    VoluntarioService voluntarioService;

    @Autowired
    CoordinadorService coordinadorService;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        return getUserByEmail(correo);
    }

    public User getUserByEmail(String correo) {
        if (voluntarioService.buscarPorCorreo(correo) != null) {
            return User.voluntarioToUser(voluntarioService.buscarPorCorreo(correo));
        }
        if (coordinadorService.buscarPorCorreo(correo) != null) {
            return User.coordinadorToUser(coordinadorService.buscarPorCorreo(correo));
        }
        return null;
    }

}
