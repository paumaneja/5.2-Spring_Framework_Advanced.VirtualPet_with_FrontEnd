package cat.itacademy.s05.t02.n01.auth;

import cat.itacademy.s05.t02.n01.enums.Role;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    @NonNull
    String token;
    Role role;
    Long userId;
}
