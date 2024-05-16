package com.example.Clinic.entity.common;

import com.example.Clinic.entity.common.JPAEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@EqualsAndHashCode(of = {"arabicName", "englishName", "code"}, callSuper = true)
@ToString(of = {"arabicName", "englishName", "enabled", "code"}, callSuper = true)
public class LookupEntity extends JPAEntity {


    private String arabicName;

    private String englishName;

    private String code;

    @Column(name = "is_enabled")
    private Boolean enabled;

}

