package StrayCare.prototype.service;

import StrayCare.prototype.model.Dog;
import org.springframework.stereotype.Service;

@Service
public interface DogService {
    boolean addDog(Dog dog);
}
