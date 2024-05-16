package com.example.Clinic.rest.dto;

import com.example.Clinic.rest.dto.common.RestDto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VisitDto extends RestDto {
    private LocalDate date;
    private PetDto pet;
    private DoctorDto doctor;
    private ClinicDto clinic;
}
