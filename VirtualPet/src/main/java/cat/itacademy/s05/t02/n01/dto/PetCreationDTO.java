package cat.itacademy.s05.t02.n01.dto;

import cat.itacademy.s05.t02.n01.enums.PetType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PetCreationDTO {
    private String name;
    private PetType type;
}
