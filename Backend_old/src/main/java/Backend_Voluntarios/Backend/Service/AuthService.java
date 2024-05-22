package Backend_Voluntarios.Backend.Service;

import org.springframework.stereotype.Service;

import Backend_Voluntarios.Backend.Entity.AuthenticationResponse;
import Backend_Voluntarios.Backend.Entity.LoginRequest;
import Backend_Voluntarios.Backend.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
public class AuthService {
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getContrasena()));

        User user = userService.getUserByEmail(request.getCorreo());
        String token = jwtService.generateToken(user.generateExtraClaims(), user);
        return new AuthenticationResponse.Builder()
                .token(token)
                .build();
    }
}
