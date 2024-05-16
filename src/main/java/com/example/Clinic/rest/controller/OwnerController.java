package com.example.Clinic.rest.controller;

import com.example.Clinic.rest.dto.OwnerDto;
import com.example.Clinic.rest.dto.PetDto;
import com.example.Clinic.rest.handler.OwnerHandler;
import com.example.Clinic.rest.handler.PetHandler;
import com.example.Clinic.validation.InsertValidation;
import com.example.Clinic.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/owner")
@AllArgsConstructor
@Tag(name = "owner", description = "Owner")
public class OwnerController {
    private OwnerHandler ownerHandler;
    @GetMapping
    @Operation(summary = "Get All Owner ")
    public ResponseEntity getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ownerHandler.getAll(page, size);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get Owner by ID")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Integer id) {
        return ownerHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "Add Owner")
    public ResponseEntity save(@Validated(InsertValidation.class)
                               @RequestBody OwnerDto dto) {
        return ownerHandler.save(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Owner")
    public ResponseEntity update(@PathVariable(value = "id") Integer id,
                                 @Validated(UpdateValidation.class)
                                 @RequestBody OwnerDto dto) {
        return ownerHandler.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Owner")
    public ResponseEntity delete(@PathVariable(value = "id") Integer id) {
        return ownerHandler.delete(id);
    }
}