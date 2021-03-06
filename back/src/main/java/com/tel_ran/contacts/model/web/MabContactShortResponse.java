package com.tel_ran.contacts.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MabContactShortResponse {

    private Long id;
    private String firstName;
    private String lastName;
}
