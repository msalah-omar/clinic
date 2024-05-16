package com.example.Clinic.rest.entitymapper;

import com.example.Clinic.entity.Visit;
import com.example.Clinic.rest.dto.VisitDto;
import com.example.Clinic.service.ClinicService;
import com.example.Clinic.service.DoctorService;
import com.example.Clinic.service.PetService;
import com.example.Clinic.utils.HibernateUtils;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class VisitMapper {
    @Autowired
    private PetMapper petMapper;
    @Autowired
    private PetService petService;
    @Autowired
    private ClinicMapper clinicMapper;
    @Autowired
    private ClinicService clinicService;
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private DoctorService doctorService;

    @Mappings({
            @Mapping(source = "pet", target = "pet", ignore = true),
            @Mapping(source = "clinic", target = "clinic", ignore = true),
            @Mapping(source = "doctor", target = "doctor", ignore = true),
    })

    public abstract VisitDto toDto(Visit entity);

    public abstract List<VisitDto> toDto(List<Visit> entityList);

    @AfterMapping
    public void toDtoAfterMapping(Visit entity, @MappingTarget VisitDto dto) {
        if (HibernateUtils.isConvertible(entity.getPet())) {
            dto.setPet(petMapper.toDto(entity.getPet()));
        }
        if (HibernateUtils.isConvertible(entity.getClinic())) {
            dto.setClinic(clinicMapper.toDto(entity.getClinic()));
        }
        if (HibernateUtils.isConvertible(entity.getDoctor())) {
            dto.setDoctor(doctorMapper.toDto(entity.getDoctor()));
        }
    }

    @InheritInverseConfiguration
    public abstract Visit toEntity(VisitDto dto);

    public abstract List<Visit> toEntity(List<VisitDto> dtoList);


    @AfterMapping
    public void toEntityAfterMapping(VisitDto dto, @MappingTarget Visit entity) {
        if (dto.getPet() != null && dto.getPet().getId() != null) {
            entity.setPet(petService.getReferenceById(dto.getPet().getId()));
        }
        if (dto.getClinic() != null && dto.getClinic().getId() != null) {
            entity.setClinic(clinicService.getReferenceById(dto.getClinic().getId()));
        }
        if (dto.getDoctor() != null && dto.getDoctor().getId() != null) {
            entity.setDoctor(doctorService.getReferenceById(dto.getDoctor().getId()));
        }
    }

    @InheritInverseConfiguration
    public abstract Visit updateEntityFromDto(VisitDto dto, @MappingTarget Visit entity);
}
