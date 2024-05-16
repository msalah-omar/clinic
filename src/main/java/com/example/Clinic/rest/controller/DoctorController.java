package com.example.Clinic.rest.controller;


import com.example.Clinic.rest.dto.DoctorDto;
import com.example.Clinic.rest.handler.DoctorHandler;
import com.example.Clinic.validation.InsertValidation;
import com.example.Clinic.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@AllArgsConstructor
@Tag(name = "doctor", description = "Doctor")
public class DoctorController {

    private DoctorHandler doctorHandler;

    @GetMapping
    @Operation(summary = "Get All Doctor ")
    public ResponseEntity getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return doctorHandler.getAll(page, size);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get Doctor by ID")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Integer id) {
        return doctorHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "Add Doctor")
    public ResponseEntity save(@Validated(InsertValidation.class)
                               @RequestBody DoctorDto dto) {
        return doctorHandler.save(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Doctor")
    public ResponseEntity update(@PathVariable(value = "id") Integer id,
                                 @Validated(UpdateValidation.class)
                                 @RequestBody DoctorDto dto) {
        return doctorHandler.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Doctor")
    public ResponseEntity delete(@PathVariable(value = "id") Integer id) {
        return doctorHandler.delete(id);
    }
    @GetMapping("/{clinicId}/doctors")
    public ResponseEntity<List<DoctorDto>> getAllDoctorsByClinicId(@PathVariable Integer clinicId) {
        return doctorHandler.getAllDoctorsByClinicId(clinicId);
    }
}
