package com.tel_ran.contacts.controller;

import com.tel_ran.contacts.model.web.MabContactInfoRequest;
import com.tel_ran.contacts.model.web.MabContactInfoResponse;
import com.tel_ran.contacts.model.web.MabContactShortResponse;
import com.tel_ran.contacts.model.web.MabSearchRequest;
import com.tel_ran.contacts.service.MabContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class MabContactController {

    @Autowired
    private MabContactService contactService;

    @GetMapping("/")
    public List<MabContactInfoResponse> getContactsShortList() {
        List<MabContactInfoResponse> contacts = new ArrayList<>();

        return contactService.getContactList();
    }

    @GetMapping("/{id}")
    public MabContactInfoResponse getContactById(@PathVariable("id") Long id) {
        return contactService.getContactById(id);
    }

    @PostMapping("/")
    public void addContact(@RequestBody MabContactInfoRequest contactInfo) {
        contactService.addContact(contactInfo);
    }

    @PutMapping("/{id}")
    public void updateContact(@PathVariable("id") Long id,
                                          @RequestBody MabContactInfoResponse contactInfo) {
         contactService.updateContact(contactInfo, id);
    }
    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable("id") Long id) {
        contactService.deleteContact(id);
    }

    @PostMapping("/search")
    public List<MabContactInfoResponse> getContactsBySearch(@RequestBody MabSearchRequest searchRequest) {
        return contactService.getContactsBySearch(searchRequest);
    }

}
