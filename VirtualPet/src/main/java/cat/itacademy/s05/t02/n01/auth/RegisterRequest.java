package cat.itacademy.s05.t02.n01.auth;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NonNull
    String username;
    @NonNull
    String password;
    @NonNull
    String firstname;
    @NonNull
    String lastname;
    @NonNull
    String email;
}
