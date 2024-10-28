package StrayCare.prototype.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dogId;
    private String name;
    private String breed;
    @Lob
    private byte[] photo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Dog(String name, String breed, byte[] photo) {
        this.name = name;
        this.breed = breed;
        this.photo = photo;
    }
}
