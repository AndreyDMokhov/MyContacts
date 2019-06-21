package com.tel_ran.contacts.service;


import com.tel_ran.contacts.model.web.MabContactInfoRequest;
import com.tel_ran.contacts.model.web.MabContactInfoResponse;
import com.tel_ran.contacts.model.web.MabContactShortResponse;
import com.tel_ran.contacts.model.web.MabSearchRequest;

import java.util.List;

public interface MabContactService {

    void addContact(MabContactInfoRequest contactInfo);

    void updateContact(MabContactInfoResponse contactInfo, Long id);

    void deleteContact(Long id);

    MabContactInfoResponse getContactById(Long id);

    List<MabContactInfoResponse> getContactList();

    List<MabContactInfoResponse> getContactsBySearch(MabSearchRequest searchRequest);

}
