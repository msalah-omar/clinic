package com.example.Clinic.entity;

import com.example.Clinic.entity.common.JPAEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "clinic")
public class Clinic extends JPAEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "working_days")
    private String workingDays;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = " social_networks_urls")
    private String  socialNetworksUrls;
}
