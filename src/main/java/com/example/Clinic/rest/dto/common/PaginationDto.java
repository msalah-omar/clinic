package com.example.Clinic.rest.dto.common;

import lombok.Data;

@Data
public class PaginationDto {
    private int totalPages;
    private long itemCount;
    private int currentPage;
    private int size;
}
