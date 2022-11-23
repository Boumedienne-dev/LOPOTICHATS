package fr.codewise.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.codewise.entities.Kitten;
import fr.codewise.repositories.KittenRepository;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/kittens")
public class KittenController {

    @Autowired
    KittenRepository kittenRepository;
    
    @GetMapping
    public List<Kitten> findAllKittensByIsAdopted(Boolean isAdopted) {
        return this.kittenRepository.findAllKittensByIsAdopted(isAdopted);
    }

    @DeleteMapping("kittens/{id}")
    public void deleteKittenById(@PathVariable int id){
        this.kittenRepository.deleteById(id);
    }

    @PostMapping("/kittens")
    public Kitten create(@RequestBody Kitten kitten){
        return this.kittenRepository.save(kitten);
    }

    @PutMapping("/kittens/{id}") 
    public Kitten update(@PathVariable int id, @RequestBody Kitten kitten){
        // getting kitten
        Kitten kittenToUpdate = this.kittenRepository.findById(id).get();
        kittenToUpdate.setName(kitten.getName());
        kittenToUpdate.setAge(kitten.getAge());
        kittenToUpdate.setColor(kitten.getColor());
        kittenToUpdate.setRace(kitten.getRace());
        kittenToUpdate.setIsAdopted(kitten.getIsAdopted());
        return this.kittenRepository.save(kittenToUpdate);
    }

    @PostMapping("/kittens/adopt?catId={id}")
    public void adoptKittenById(int catId) {
    Kitten kitten = this.kittenRepository.findById(catId).get();
    kitten.setIsAdopted(true);
    this.kittenRepository.save(kitten);
    }
}