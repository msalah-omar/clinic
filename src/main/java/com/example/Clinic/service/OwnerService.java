package com.example.Clinic.service;

import com.example.Clinic.entity.Owner;
import com.example.Clinic.repository.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OwnerService {
    private OwnerRepository ownerRepository;
    public Page<Owner> getAll(Integer page, Integer size) {
        return ownerRepository.findAll(PageRequest.of(page, size));
    }
    public Owner getReferenceById(Integer id) {
        return ownerRepository.getById(id);
    }

    public Optional<Owner> getById(Integer id) {
        return ownerRepository.findById(id);
    }
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    public void update(Owner owner) {ownerRepository.save(owner);
    }

    public void delete(Owner owner) {
        ownerRepository.delete(owner);
    }
}
