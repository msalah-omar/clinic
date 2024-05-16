package com.example.Clinic.rest.handler;

import com.example.Clinic.entity.Clinic;
import com.example.Clinic.rest.dto.ClinicDto;
import com.example.Clinic.rest.dto.common.PaginatedResultDto;
import com.example.Clinic.rest.entitymapper.ClinicMapper;
import com.example.Clinic.rest.entitymapper.commen.PaginationMapper;
import com.example.Clinic.rest.exception.ErrorCodes;
import com.example.Clinic.rest.exception.ResourceNotFoundException;
import com.example.Clinic.rest.exception.ResourceRelatedException;
import com.example.Clinic.service.ClinicService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ClinicHandler {
    private ClinicService clinicService;
    private ClinicMapper clinicMapper;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> getAll(Integer page, Integer size) {
        Page<Clinic> clinicPage = clinicService.getAll(page, size);
        List<ClinicDto> clinicDtoList = clinicMapper.toDto(clinicPage.getContent());
        PaginatedResultDto<ClinicDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(clinicDtoList);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(clinicPage));
        return ResponseEntity.ok(paginatedResult);
    }

    public ResponseEntity<?> getById(Integer id) {
        Clinic clinic = clinicService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(Clinic.class.getSimpleName(), id));
        ClinicDto clinicDto = clinicMapper.toDto(clinic);
        return ResponseEntity.ok(clinicDto);
    }

    public ResponseEntity<?> save(ClinicDto dto) {
        Clinic clinic = clinicMapper.toEntity(dto);
        clinicService.save(clinic);
        ClinicDto clinicDto = clinicMapper.toDto(clinic);
        return ResponseEntity.created(URI.create("/clinic/" + clinic.getId())).body(clinicDto);
    }

    public ResponseEntity<?> update(Integer id, ClinicDto dto) {
        Clinic clinic = clinicService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(Clinic.class.getSimpleName(), id));
        Clinic entity = clinicMapper.updateEntityFromDto(dto, clinic);
        clinicService.update(entity);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> delete(Integer id) {
        Clinic clinic = clinicService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(Clinic.class.getSimpleName(), id));
        try {
            clinicService.delete(clinic);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Clinic.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<ClinicDto>> searchClinics(String phoneNumber, String address) {
        List<Clinic> clinics = clinicService.searchClinics(phoneNumber, address);
        if (clinics.isEmpty()) {
            throw new ResourceNotFoundException("Clinics", "phoneNumber=" + phoneNumber + ", address=" + address);
        }
        List<ClinicDto> clinicDtos = clinics.stream()
                .map(clinicMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clinicDtos);
    }
}