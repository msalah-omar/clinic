package com.example.Clinic.rest.controller;

import com.example.Clinic.rest.dto.ClinicDto;
import com.example.Clinic.rest.dto.OwnerDto;
import com.example.Clinic.rest.handler.ClinicHandler;
import com.example.Clinic.rest.handler.OwnerHandler;
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
@RequestMapping("/clinic")
@AllArgsConstructor
@Tag(name = "clinic", description = "Clinic")
public class ClinicController {
    private ClinicHandler clinicHandler;
    @GetMapping
    @Operation(summary = "Get All Clinic ")
    public ResponseEntity getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return clinicHandler.getAll(page, size);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get Clinic by ID")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Integer id) {
        return clinicHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "Add Clinic")
    public ResponseEntity save(@Validated(InsertValidation.class)
                               @RequestBody ClinicDto dto) {
        return clinicHandler.save(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Clinic")
    public ResponseEntity update(@PathVariable(value = "id") Integer id,
                                 @Validated(UpdateValidation.class)
                                 @RequestBody ClinicDto dto) {
        return clinicHandler.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Clinic")
    public ResponseEntity delete(@PathVariable(value = "id") Integer id) {
        return clinicHandler.delete(id);
    }
    @GetMapping("/search")
    public ResponseEntity<List<ClinicDto>> searchClinics(@RequestParam(required = false) String phoneNumber,
                                                         @RequestParam(required = false) String address) {
        return clinicHandler.searchClinics(phoneNumber, address);
    }
}
