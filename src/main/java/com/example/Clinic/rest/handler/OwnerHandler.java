package com.example.Clinic.rest.handler;

import com.example.Clinic.entity.Owner;
import com.example.Clinic.rest.dto.OwnerDto;
import com.example.Clinic.rest.dto.common.PaginatedResultDto;
import com.example.Clinic.rest.entitymapper.OwnerMapper;
import com.example.Clinic.rest.entitymapper.commen.PaginationMapper;
import com.example.Clinic.rest.exception.ErrorCodes;
import com.example.Clinic.rest.exception.ResourceNotFoundException;
import com.example.Clinic.rest.exception.ResourceRelatedException;
import com.example.Clinic.service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

@Component
@AllArgsConstructor
public class OwnerHandler {
    private OwnerService ownerService;
    private OwnerMapper ownerMapper;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> getAll(Integer page, Integer size) {
        Page<Owner> ownerPage = ownerService.getAll(page, size);
        List<OwnerDto> ownerDtoList = ownerMapper.toDto(ownerPage.getContent());
        PaginatedResultDto<OwnerDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(ownerDtoList);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(ownerPage));
        return ResponseEntity.ok(paginatedResult);
    }

    public ResponseEntity<?> getById(Integer id) {
        Owner owner = ownerService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(Owner.class.getSimpleName(), id));
        OwnerDto ownerDto = ownerMapper.toDto(owner);
        return ResponseEntity.ok(ownerDto);
    }

    public ResponseEntity<?> save(OwnerDto dto) {
        Owner owner = ownerMapper.toEntity(dto);
        ownerService.save(owner);
        OwnerDto ownerDto = ownerMapper.toDto(owner);
        return ResponseEntity.created(URI.create("/owner/" + owner.getId())).body(ownerDto);
    }

    public ResponseEntity<?> update(Integer id, OwnerDto dto) {
        Owner owner = ownerService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(Owner.class.getSimpleName(), id));
        Owner entity = ownerMapper.updateEntityFromDto(dto, owner);
        ownerService.update(entity);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> delete(Integer id) {
        Owner owner = ownerService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(Owner.class.getSimpleName(), id));
        try {
            ownerService.delete(owner);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Owner.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.noContent().build();
    }
}
