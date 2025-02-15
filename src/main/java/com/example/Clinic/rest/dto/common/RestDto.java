package com.example.Clinic.rest.dto.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@ToString(of = "id", callSuper = false)
public class RestDto {

    private Integer id;
    private Integer version;

}
