package com.example.Clinic.rest.dto;

import com.example.Clinic.rest.dto.common.RestDto;
import com.example.Clinic.validation.InsertValidation;
import com.example.Clinic.validation.UpdateValidation;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class PetDto extends RestDto {
    private String name;
    private LocalDate birthDate;
    private Boolean gender;
    private String animalKind;
    @NotBlank(message = "Add an image", groups = {InsertValidation.class, UpdateValidation.class})
    private byte[] photo;
    private String weight;
    private OwnerDto owner;
}
