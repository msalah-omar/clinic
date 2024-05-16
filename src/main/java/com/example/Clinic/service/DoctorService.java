package com.example.Clinic.service;

import com.example.Clinic.entity.Doctor;
import com.example.Clinic.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DoctorService {
    private DoctorRepository doctorRepository;
    public Page<Doctor> getAll(Integer page, Integer size) {
        return doctorRepository.findAll(PageRequest.of(page, size));
    }
    public Doctor getReferenceById(Integer id) {
        return doctorRepository.getById(id);
    }

    public Optional<Doctor> getById(Integer id) {
        return doctorRepository.findById(id);
    }
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void update(Doctor doctor) {doctorRepository.save(doctor);
    }

    public void delete(Doctor doctor) {
        doctorRepository.delete(doctor);
    }
    public List<Doctor> getAllDoctorsByClinicId(Integer clinicId) {
        return doctorRepository.findDoctorsByClinicId(clinicId);
    }
}
