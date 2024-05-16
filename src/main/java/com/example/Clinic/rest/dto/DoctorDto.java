package com.example.Clinic.rest.dto;

import com.example.Clinic.rest.dto.common.RestDto;
import com.example.Clinic.validation.InsertValidation;
import com.example.Clinic.validation.UpdateValidation;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class DoctorDto extends RestDto {
    private String name;
    @Size(min = 11 , max = 11, message = "Phone Number's length allowed is 11 characters", groups = {InsertValidation.class, UpdateValidation.class})
    @Pattern(regexp = "^01[0125][0-9]{8}$",message = "Invalid Phone Number", groups = {InsertValidation.class, UpdateValidation.class})
    private String phoneNumber;
    @NotBlank(message = "Add an image", groups = {InsertValidation.class, UpdateValidation.class})
    private byte[] photo;
    private String bio;
    private ClinicDto clinic;

}
