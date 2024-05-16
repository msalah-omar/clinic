package com.example.Clinic.service;

import com.example.Clinic.entity.Pet;
import com.example.Clinic.repository.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PetService {
    private PetRepository petRepository;
    public Page<Pet> getAll(Integer page, Integer size) {
        return petRepository.findAll(PageRequest.of(page, size));
    }
    public Pet getReferenceById(Integer id) {
        return petRepository.getById(id);
    }

    public Optional<Pet> getById(Integer id) {
        return petRepository.findById(id);
    }
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    public void update(Pet pet) {petRepository.save(pet);
    }

    public void delete(Pet pet) {
        petRepository.delete(pet);
    }

    public List<Pet> getAllPetsByOwnerId(Integer ownerId) {
        return petRepository.findPetsByOwnerId(ownerId);
    }
}
