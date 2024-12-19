package cat.itacademy.s05.t02.n01.auth;

import cat.itacademy.s05.t02.n01.enums.Role;
import cat.itacademy.s05.t02.n01.jwt.JwtService;
import cat.itacademy.s05.t02.n01.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import cat.itacademy.s05.t02.n01.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request) {
        return null;
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
