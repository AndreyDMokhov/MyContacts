package com.tel_ran.contacts.model.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "ADDRESSES")
public class MabAddress extends MabBaseEntity{

    @ManyToOne
    @JoinColumn(name = "CONTACT_ID")
    private MabContact contact;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STREET")
    private String street;

    @Column(name = "BUILDER")
    private String builder;

    @Column(name = "APARTMENT")
    private String apartment;
}
