package com.example.Clinic.repository;

import com.example.Clinic.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor , Integer> {
    @Query("SELECT d FROM Doctor d WHERE d.clinic.id = :clinicId")
    List<Doctor> findDoctorsByClinicId(@Param("clinicId") Integer clinicId);
}
