package Backend_Voluntarios.Backend.Entity;

public class AuthenticationResponse {
    private String token;

    // Constructor con un solo parametro
    public AuthenticationResponse(String token) {
        this.token = token;
    }

    // Constructor sin parametros
    public AuthenticationResponse() {
    }

    // Getters y Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class Builder {
        private String token;

        public Builder() {
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public AuthenticationResponse build() {
            return new AuthenticationResponse(token);
        }
    }

}
