package com.example.Clinic.rest.handler;

import com.example.Clinic.entity.Visit;
import com.example.Clinic.rest.dto.VisitDto;
import com.example.Clinic.rest.dto.common.PaginatedResultDto;
import com.example.Clinic.rest.entitymapper.VisitMapper;
import com.example.Clinic.rest.entitymapper.commen.PaginationMapper;
import com.example.Clinic.rest.exception.ErrorCodes;
import com.example.Clinic.rest.exception.ResourceNotFoundException;
import com.example.Clinic.rest.exception.ResourceRelatedException;

import com.example.Clinic.service.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

@Component
@AllArgsConstructor
public class VisitHandler {

    private VisitService visitService;
    private VisitMapper visitMapper;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> getAll(Integer page, Integer size) {
        Page<Visit> visitPage = visitService.getAll(page, size);
        List<VisitDto> visitDtoList = visitMapper.toDto(visitPage.getContent());
        PaginatedResultDto<VisitDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(visitDtoList);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(visitPage));
        return ResponseEntity.ok(paginatedResult);
    }

    public ResponseEntity<?> getById(Integer id) {
        Visit visit = visitService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(Visit.class.getSimpleName(), id));
        VisitDto visitDto = visitMapper.toDto(visit);
        return ResponseEntity.ok(visitDto);
    }

    public ResponseEntity<?> save(VisitDto dto) {
        Visit visit = visitMapper.toEntity(dto);
        visitService.save(visit);
        VisitDto visitDto = visitMapper.toDto(visit);
        return ResponseEntity.created(URI.create("/visit/" + visit.getId())).body(visitDto);
    }

    public ResponseEntity<?> update(Integer id, VisitDto dto) {
        Visit visit = visitService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(Visit.class.getSimpleName(), id));
        Visit entity = visitMapper.updateEntityFromDto(dto, visit);
        visitService.update(entity);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> delete(Integer id) {
        Visit visit = visitService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(Visit.class.getSimpleName(), id));
        try {
            visitService.delete(visit);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Visit.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.noContent().build();
    }
}
