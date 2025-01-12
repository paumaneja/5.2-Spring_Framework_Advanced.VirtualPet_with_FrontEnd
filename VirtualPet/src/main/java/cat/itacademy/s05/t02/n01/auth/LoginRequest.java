package cat.itacademy.s05.t02.n01.auth;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NonNull
    String username;
    @NonNull
    String password;
}
