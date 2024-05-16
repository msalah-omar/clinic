package com.example.Clinic.rest.controller;

import com.example.Clinic.rest.dto.PetDto;
import com.example.Clinic.rest.handler.PetHandler;
import com.example.Clinic.validation.InsertValidation;
import com.example.Clinic.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;


@RestController
@RequestMapping("/pet")
@AllArgsConstructor
@Tag(name = "pet", description = "REST API for Pet")
public class PetController {
    private PetHandler petHandler;
    @GetMapping
    @Operation(summary = "Get All Pet ")
    public ResponseEntity getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return petHandler.getAll(page, size);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get Pet by ID")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Integer id) {
        return petHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "Add Pet")
    public ResponseEntity<?> save(@Validated(InsertValidation.class)
                               @RequestBody PetDto dto) {
        return petHandler.save(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Pet")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                 @Validated(UpdateValidation.class)
                                 @RequestBody PetDto dto) {
        return petHandler.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Pet")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer id) {
        return petHandler.delete(id);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<PetDto>> getPetsByOwnerId(@PathVariable Integer ownerId) {
        return petHandler.getAllPetsByOwnerId(ownerId);
    }

}

