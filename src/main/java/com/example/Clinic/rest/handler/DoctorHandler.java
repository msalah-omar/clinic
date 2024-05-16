package com.example.Clinic.rest.handler;

import com.example.Clinic.entity.Doctor;
import com.example.Clinic.entity.Pet;
import com.example.Clinic.rest.dto.DoctorDto;
import com.example.Clinic.rest.dto.PetDto;
import com.example.Clinic.rest.dto.common.PaginatedResultDto;
import com.example.Clinic.rest.entitymapper.DoctorMapper;
import com.example.Clinic.rest.entitymapper.commen.PaginationMapper;
import com.example.Clinic.rest.exception.ErrorCodes;
import com.example.Clinic.rest.exception.ResourceNotFoundException;
import com.example.Clinic.rest.exception.ResourceRelatedException;
import com.example.Clinic.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

@Component
@AllArgsConstructor
public class DoctorHandler {
    private DoctorService doctorService;
    private DoctorMapper doctorMapper;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> getAll(Integer page, Integer size) {
        Page<Doctor> doctorPage = doctorService.getAll(page, size);
        List<DoctorDto> doctorDtoList = doctorMapper.toDto(doctorPage.getContent());
        PaginatedResultDto<DoctorDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(doctorDtoList);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(doctorPage));
        return ResponseEntity.ok(paginatedResult);
    }

    public ResponseEntity<?> getById(Integer id) {
        Doctor doctor = doctorService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(Doctor.class.getSimpleName(), id));
        DoctorDto doctorDto = doctorMapper.toDto(doctor);
        return ResponseEntity.ok(doctorDto);
    }

    public ResponseEntity<?> save(DoctorDto dto) {
        Doctor doctor = doctorMapper.toEntity(dto);
        doctorService.save(doctor);
        DoctorDto doctorDto = doctorMapper.toDto(doctor);
        return ResponseEntity.created(URI.create("/doctor/" + doctor.getId())).body(doctorDto);
    }

    public ResponseEntity<?> update(Integer id, DoctorDto dto) {
        Doctor doctor = doctorService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(Doctor.class.getSimpleName(), id));
        Doctor entity = doctorMapper.updateEntityFromDto(dto, doctor);
        doctorService.update(entity);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> delete(Integer id) {
        Doctor doctor = doctorService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(Doctor.class.getSimpleName(), id));
        try {
            doctorService.delete(doctor);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Doctor.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.noContent().build();
    }
    public ResponseEntity<List<DoctorDto>> getAllDoctorsByClinicId(Integer clinicId) {
        List<Doctor> doctors = doctorService.getAllDoctorsByClinicId(clinicId);
        List<DoctorDto> doctorDtoList = doctorMapper.toDto(doctors);
        return ResponseEntity.ok(doctorDtoList);
    }
}
