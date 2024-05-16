package com.example.Clinic.repository;

import com.example.Clinic.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic,Integer>{
    @Query("SELECT c FROM Clinic c WHERE c.phoneNumber = :phoneNumber OR c.address = :address")
    List<Clinic> findClinicsByPhoneNumberOrAddress(@Param("phoneNumber") String phoneNumber,
                                                   @Param("address") String address);
}
