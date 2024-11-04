package StrayCare.prototype.controller;

import StrayCare.prototype.model.Dog;
import StrayCare.prototype.model.User;
import StrayCare.prototype.repository.UserRepository;
import StrayCare.prototype.service.implementation.DogServiceImpl;
import StrayCare.prototype.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/dog")
public class DogController {

    @Autowired
    private DogServiceImpl dogService;
    @Autowired
    private UserRepository userRepo;

    @PostMapping("/add")
    public ResponseEntity<String> addDog(@RequestParam("name") String name,
                                         @RequestParam("breed") String breed,
                                         @RequestParam("user") String email,
                                         @RequestParam("photo") MultipartFile photo) throws IOException
    {
        User user = userRepo.findByEmail(email);
//        Dog dog = new Dog();
//        dog.setName(name);
//        dog.setBreed(breed);
//        dog.setPhoto(photo.getBytes());
//        dog.setUser(user);
        Dog dog = new Dog(name, breed, photo.getBytes(), user);
        boolean dogAdded = dogService.addDog(dog);
        if(dogAdded){
            return new ResponseEntity<>("Dog has been added for adoption", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Problem encountered", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Dog>> getAllDogs(){
        return new ResponseEntity<>(dogService.getAllDogs(), HttpStatus.OK);
    }

}
