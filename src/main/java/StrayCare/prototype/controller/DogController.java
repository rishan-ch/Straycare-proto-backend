package StrayCare.prototype.controller;

import StrayCare.prototype.model.Dog;
import StrayCare.prototype.service.implementation.DogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/dog")
@CrossOrigin(origins = "http://localhost:3000")
public class DogController {

    @Autowired
    private DogServiceImpl dogService;

    @PostMapping("/add")
    public ResponseEntity<String> addDog(@RequestParam("name") String name,
                                         @RequestParam("breed") String breed,
                                         @RequestParam("photo") MultipartFile photo) throws IOException {
        Dog dog = new Dog(name,breed,photo.getBytes());
        boolean dogAdded = dogService.addDog(dog);
        if(dogAdded){
            return new ResponseEntity<>("Dog has been added for adoption", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Problem encountered", HttpStatus.CONFLICT);
        }
    }

}
