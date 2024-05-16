package com.example.Clinic.rest.handler;

import com.example.Clinic.entity.Pet;
import com.example.Clinic.rest.dto.PetDto;
import com.example.Clinic.rest.dto.common.PaginatedResultDto;
import com.example.Clinic.rest.entitymapper.PetMapper;
import com.example.Clinic.rest.entitymapper.commen.PaginationMapper;
import com.example.Clinic.rest.exception.ErrorCodes;
import com.example.Clinic.rest.exception.ResourceNotFoundException;
import com.example.Clinic.rest.exception.ResourceRelatedException;
import com.example.Clinic.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
@Component
@AllArgsConstructor
public class PetHandler {
    private PetService petService;
    private PetMapper petMapper;
    private PaginationMapper paginationMapper;

    public ResponseEntity getAll(Integer page, Integer size) {
        Page<Pet> petPage = petService.getAll(page, size);
        List<PetDto> petDtoList = petMapper.toDto(petPage.getContent());
        PaginatedResultDto<PetDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(petDtoList);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(petPage));
        return ResponseEntity.ok(paginatedResult);
    }

    public ResponseEntity<?> getById(Integer id) {
        Pet pet = petService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(Pet.class.getSimpleName(), id));
        PetDto petDto = petMapper.toDto(pet);
        return ResponseEntity.ok(petDto);
    }

    public ResponseEntity<?> save(PetDto dto) {
        Pet pet = petMapper.toEntity(dto);
        petService.save(pet);
        PetDto petDto = petMapper.toDto(pet);
        return ResponseEntity.created(URI.create("/pet/" + pet.getId())).body(petDto);
    }

    public ResponseEntity<?> update(Integer id, PetDto dto) {
        Pet pet = petService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(Pet.class.getSimpleName(), id));
        Pet entity = petMapper.updateEntityFromDto(dto, pet);
        petService.update(entity);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> delete(Integer id) {
        Pet pet = petService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(Pet.class.getSimpleName(), id));
        try {
            petService.delete(pet);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Pet.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<PetDto>> getAllPetsByOwnerId(Integer ownerId) {
        List<Pet> pets = petService.getAllPetsByOwnerId(ownerId);
        List<PetDto> petDtoList = petMapper.toDto(pets);
        return ResponseEntity.ok(petDtoList);
    }
}
