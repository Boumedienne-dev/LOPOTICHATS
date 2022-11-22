package fr.codewise.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.codewise.entities.Kitten;

@Repository
public interface KittenRepository extends JpaRepository<Kitten, Integer> {
    public List<Kitten> findAllKittensByIsAdopted(Boolean isAdopted);
}