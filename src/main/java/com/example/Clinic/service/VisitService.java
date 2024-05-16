package com.example.Clinic.service;

import com.example.Clinic.entity.Visit;
import com.example.Clinic.repository.VisitRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VisitService {
    private VisitRepository visitRepository;

    public Page<Visit> getAll(Integer page, Integer size) {
        return visitRepository.findAll(PageRequest.of(page, size));
    }
    public Visit getReferenceById(Integer id) {
        return visitRepository.getById(id);
    }

    public Optional<Visit> getById(Integer id) {
        return visitRepository.findById(id);
    }

    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }

    public void update(Visit visit) {visitRepository.save(visit);
    }

    public void delete(Visit visit) {
        visitRepository.delete(visit);
    }
}
