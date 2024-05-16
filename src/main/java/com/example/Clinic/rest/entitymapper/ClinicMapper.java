package com.example.Clinic.rest.entitymapper;

import com.example.Clinic.entity.Clinic;
import com.example.Clinic.rest.dto.ClinicDto;
import com.example.Clinic.rest.entitymapper.commen.JPAEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClinicMapper extends JPAEntityMapper<Clinic, ClinicDto> {
}
