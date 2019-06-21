package com.tel_ran.contacts.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MabAddressWeb {

    //country: String , city : String, street : String, builder: String ,  apartment: int
    private String country;

    private String city;

    private String street;

    private String builder;

    private String apartment;
}
