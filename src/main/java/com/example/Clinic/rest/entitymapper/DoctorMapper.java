package com.example.Clinic.rest.entitymapper;

import com.example.Clinic.entity.Doctor;
import com.example.Clinic.rest.dto.DoctorDto;
import com.example.Clinic.service.ClinicService;
import com.example.Clinic.utils.HibernateUtils;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DoctorMapper  {

    @Autowired
    private ClinicMapper clinicMapper;
    @Autowired
    private ClinicService clinicService;


    @Mappings({
            @Mapping(source = "clinic", target = "clinic", ignore = true)
    })

    public abstract DoctorDto toDto(Doctor entity);

    public abstract List<DoctorDto> toDto(List<Doctor> entityList);

    @AfterMapping
    public void toDtoAfterMapping(Doctor entity, @MappingTarget DoctorDto dto) {

        if (HibernateUtils.isConvertible(entity.getClinic())) {
            dto.setClinic(clinicMapper.toDto(entity.getClinic()));
        }

    }

    @InheritInverseConfiguration
    public abstract Doctor toEntity(DoctorDto dto);

    public abstract List<Doctor> toEntity(List<DoctorDto> dtoList);


    @AfterMapping
    public void toEntityAfterMapping(DoctorDto dto, @MappingTarget Doctor entity) {
        if (dto.getClinic() != null && dto.getClinic().getId() != null) {
            entity.setClinic(clinicService.getReferenceById(dto.getClinic().getId()));
        }
    }

    @InheritInverseConfiguration
    public abstract Doctor updateEntityFromDto(DoctorDto dto, @MappingTarget Doctor entity);
}

