package com.example.Clinic.rest.controller;

import com.example.Clinic.rest.dto.VisitDto;
import com.example.Clinic.rest.handler.VisitHandler;
import com.example.Clinic.validation.InsertValidation;
import com.example.Clinic.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/visit")
@AllArgsConstructor
@Tag(name = "visit", description = "Visit")
public class VisitController {
    private VisitHandler visitHandler;

    @GetMapping
    @Operation(summary = "Get All Visit ")
    public ResponseEntity getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return visitHandler.getAll(page, size);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get Visit by ID")
    public ResponseEntity<?> getById(@PathVariable(value = "id") Integer id) {
        return visitHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "Add Visit")
    public ResponseEntity save(@Validated(InsertValidation.class)
                               @RequestBody VisitDto dto) {
        return visitHandler.save(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Visit")
    public ResponseEntity update(@PathVariable(value = "id") Integer id,
                                 @Validated(UpdateValidation.class)
                                 @RequestBody VisitDto dto) {
        return visitHandler.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Visit")
    public ResponseEntity delete(@PathVariable(value = "id") Integer id) {
        return visitHandler.delete(id);
    }
}
