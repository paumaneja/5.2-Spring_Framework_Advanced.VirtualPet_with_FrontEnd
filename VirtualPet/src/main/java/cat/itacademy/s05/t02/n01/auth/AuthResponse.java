package cat.itacademy.s05.t02.n01.auth;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    @NonNull
    String token;
}
