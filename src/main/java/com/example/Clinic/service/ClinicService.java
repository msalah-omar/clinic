package com.example.Clinic.service;

import com.example.Clinic.entity.Clinic;
import com.example.Clinic.repository.ClinicRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClinicService {
    private ClinicRepository clinicRepository;
    public Page<Clinic> getAll(Integer page, Integer size) {
        return clinicRepository.findAll(PageRequest.of(page, size));
    }
    public Clinic getReferenceById(Integer id) {
        return clinicRepository.getById(id);
    }

    public Optional<Clinic> getById(Integer id) {
        return clinicRepository.findById(id);
    }
    public Clinic save(Clinic clinic) {
        return clinicRepository.save(clinic);
    }

    public void update(Clinic clinic) {clinicRepository.save(clinic);
    }

    public void delete(Clinic clinic) {
        clinicRepository.delete(clinic);
    }

    public List<Clinic> searchClinics(String phoneNumber, String address) {
        return clinicRepository.findClinicsByPhoneNumberOrAddress(phoneNumber, address);
    }
}
