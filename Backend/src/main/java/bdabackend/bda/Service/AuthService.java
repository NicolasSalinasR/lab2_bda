package bdabackend.bda.Service;

import org.springframework.stereotype.Service;

import bdabackend.bda.Entity.AuthenticationResponse;
import bdabackend.bda.Entity.LoginRequest;
import bdabackend.bda.Entity.User;
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
