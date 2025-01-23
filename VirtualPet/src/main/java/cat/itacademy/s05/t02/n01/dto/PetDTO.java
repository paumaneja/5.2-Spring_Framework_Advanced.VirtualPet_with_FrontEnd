package cat.itacademy.s05.t02.n01.dto;

import cat.itacademy.s05.t02.n01.enums.Mood;
import cat.itacademy.s05.t02.n01.enums.PetType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PetDTO {
    private Integer id;
    private String name;
    private PetType type;
    private Mood mood;
    private int energy;
    private String weapon;
    private Long ownerId;
}

