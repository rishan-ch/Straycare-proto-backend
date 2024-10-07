package StrayCare.prototype.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dog_id;
    private String name;
    private String breed;
    @Lob
    private byte[] photo;

    public Dog(String name, String breed, byte[] photo) {
        this.name = name;
        this.breed = breed;
        this.photo = photo;
    }
}
