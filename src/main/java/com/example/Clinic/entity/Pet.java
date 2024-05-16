package com.example.Clinic.entity;

import com.example.Clinic.entity.common.JPAEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "pet")
public class Pet extends JPAEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "animal_Kind")
    private String animalKind;

    @Lob
    private byte[] photo;

    @Column(name = "weight")
    private String weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

}
