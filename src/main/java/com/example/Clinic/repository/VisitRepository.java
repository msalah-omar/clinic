package com.example.Clinic.repository;

import com.example.Clinic.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<Visit,Integer> {
}
