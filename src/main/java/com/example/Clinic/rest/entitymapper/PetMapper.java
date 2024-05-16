package com.example.Clinic.rest.entitymapper;

import com.example.Clinic.entity.Pet;
import com.example.Clinic.rest.dto.PetDto;
import com.example.Clinic.service.OwnerService;
import com.example.Clinic.utils.HibernateUtils;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PetMapper  {
    @Autowired
    private OwnerMapper ownerMapper;
    @Autowired
    private OwnerService ownerService;


    @Mappings({
            @Mapping(source = "owner", target = "owner", ignore = true)
    })

    public abstract PetDto toDto(Pet entity);

    public abstract List<PetDto> toDto(List<Pet> entityList);

    @AfterMapping
    public void toDtoAfterMapping(Pet entity, @MappingTarget PetDto dto) {

        if (HibernateUtils.isConvertible(entity.getOwner())) {
            dto.setOwner(ownerMapper.toDto(entity.getOwner()));
        }

    }

    @InheritInverseConfiguration
    public abstract Pet toEntity(PetDto dto);

    public abstract List<Pet> toEntity(List<PetDto> dtoList);


    @AfterMapping
    public void toEntityAfterMapping(PetDto dto, @MappingTarget Pet entity) {
        if (dto.getOwner() != null && dto.getOwner().getId() != null) {
            entity.setOwner(ownerService.getReferenceById(dto.getOwner().getId()));
        }
    }

    @InheritInverseConfiguration
    public abstract Pet updateEntityFromDto(PetDto dto, @MappingTarget Pet entity);
}
