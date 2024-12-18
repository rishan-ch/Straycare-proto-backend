package StrayCare.prototype.service.implementation;

import StrayCare.prototype.model.Dog;
import StrayCare.prototype.repository.DogRepository;
import StrayCare.prototype.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DogServiceImpl implements DogService {

    @Autowired
    private DogRepository dogRepo;

    @Override
    public boolean addDog(Dog dog) {
        try {
            dogRepo.save(dog);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Dog> getAllDogs() {
        return dogRepo.findAll();
    }
}
