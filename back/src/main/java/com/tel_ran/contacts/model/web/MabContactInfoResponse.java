package com.tel_ran.contacts.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MabContactInfoResponse {

    private Long id;
    private LocalDate createdDate;
    private String firstName;
    private String lastName;
    private String email;
    private List<MabPhoneWeb> phones;
    private List<MabAddressWeb> addresses;

}
