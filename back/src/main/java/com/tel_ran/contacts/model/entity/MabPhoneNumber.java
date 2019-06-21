package com.tel_ran.contacts.model.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "PHONE_NUMBERS")
public class MabPhoneNumber extends MabBaseEntity{

    @ManyToOne
    @JoinColumn(name = "CONTACT_ID")
    private MabContact contact;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
}
