package StrayCare.prototype.service;

import StrayCare.prototype.model.Dog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DogService {
    boolean addDog(Dog dog);
    List<Dog> getAllDogs();
}
