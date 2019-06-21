package com.tel_ran.contacts.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MabContactInfoRequest {
    private String firstName;
    private String lastName;
    private String email;
    private List<MabPhoneWeb> phones;
    private List<MabAddressWeb> addresses;

}
