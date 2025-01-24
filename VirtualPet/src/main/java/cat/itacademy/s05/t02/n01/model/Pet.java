package cat.itacademy.s05.t02.n01.model;

import cat.itacademy.s05.t02.n01.enums.Mood;
import cat.itacademy.s05.t02.n01.enums.PetType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private PetType type;
    @Enumerated(EnumType.STRING)
    private Mood mood;
    private int energy;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    private String weapon;


    public void play() {
        this.energy = Math.max(this.energy - 15, 0);
        if (this.energy <= 60) {
            this.mood = Mood.ANGRY;
            if (this.energy <= 20) {
                this.mood = Mood.TIRED;
            }
        }
    }

    public void feed() {
        this.energy = Math.min(this.energy + 5, 100);
        if (this.energy > 60) {
            this.mood = Mood.HAPPY;
        }
    }

    public void sleep() {
        if (this.mood == Mood.TIRED) {
            this.energy = 80;
            this.mood = Mood.SAD;
        } else {
            throw new IllegalStateException("Pet is not tired and cannot sleep.");
        }
    }

    public void changeWeapon(String newWeapon) {
        this.weapon = newWeapon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return energy == pet.energy &&
                Objects.equals(id, pet.id) &&
                Objects.equals(name, pet.name) &&
                type == pet.type &&
                mood == pet.mood &&
                Objects.equals(owner, pet.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, mood, energy, owner);
    }
}