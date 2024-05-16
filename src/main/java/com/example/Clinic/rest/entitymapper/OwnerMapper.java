package com.example.Clinic.rest.entitymapper;

import com.example.Clinic.entity.Owner;

import com.example.Clinic.rest.dto.OwnerDto;
import com.example.Clinic.rest.entitymapper.commen.JPAEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OwnerMapper extends JPAEntityMapper<Owner, OwnerDto> {
}
