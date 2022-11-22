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

import fr.codewise.lopotichat.entities.Kitten;
import fr.codewise.lopotichat.repositories.KittenRepository;

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
    public boolean delete(@PathVariable int id){
        KittenRepository.deleteById(id);
        return true;
    }

    @PostMapping("/kittens")
    public Kitten create(@RequestBody Kitten kitten){
        return kittenRepository.save(kitten);
    }

    @PutMapping("/kittens/{id}")
    public Kitten update(@PathVariable int id, @RequestBody Kitten kitten){
        // getting kitten
        Kitten blogToUpdate = KittenRepository.findById(id).get();
        kittenToUpdate.setTitle(kitten.getName());
        kittenToUpdate.setContent(kitten.getContent());
        return kittenRepository.save(kittenToUpdate);
    }
}