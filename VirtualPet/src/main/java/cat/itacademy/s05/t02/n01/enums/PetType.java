package cat.itacademy.s05.t02.n01.enums;

import java.util.Arrays;
import java.util.List;

public enum PetType {
    STARWARS(Arrays.asList("Pistol", "Machine Gun", "Lightsaber")),
    LORDRINGS(Arrays.asList("Sword", "Axe", "Bow"));

    private final List<String> allowedWeapons;

    PetType(List<String> allowedWeapons) {
        this.allowedWeapons = allowedWeapons;
    }

    public List<String> getAllowedWeapons() {
        return allowedWeapons;
    }
}
