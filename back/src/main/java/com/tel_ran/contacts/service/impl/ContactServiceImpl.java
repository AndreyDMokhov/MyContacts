package com.tel_ran.contacts.service.impl;

import com.tel_ran.contacts.exception.InputException;
import com.tel_ran.contacts.exception.NotFoundException;
import com.tel_ran.contacts.model.entity.MabAddress;
import com.tel_ran.contacts.model.entity.MabContact;
import com.tel_ran.contacts.model.entity.MabPhoneNumber;
import com.tel_ran.contacts.model.web.*;
import com.tel_ran.contacts.repository.ContactRepository;
import com.tel_ran.contacts.repository.MabAddressRepository;
import com.tel_ran.contacts.repository.MabPhoneNumberRepository;
import com.tel_ran.contacts.service.MabContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContactServiceImpl implements MabContactService {

    private ContactRepository contactRepository;
    private MabAddressRepository addressRepository;
    private MabPhoneNumberRepository phoneNumberRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository,
                              MabAddressRepository addressRepository,
                              MabPhoneNumberRepository phoneNumberRepository) {
        this.contactRepository = contactRepository;
        this.addressRepository = addressRepository;
        this.phoneNumberRepository = phoneNumberRepository;
    }

    @Override
    public void addContact(MabContactInfoRequest contactInfo) {

        if (contactInfo == null) {
            throw new InputException();
        }

        MabContact contact = MabContact.builder()
                .createdDate(LocalDate.now())
                .firstName(contactInfo.getFirstName())
                .lastName(contactInfo.getLastName())
                .email(contactInfo.getEmail())
                .build();

        List<MabPhoneNumber> phoneNumbers = getPhonesNumberListFromWeb(contactInfo.getPhones(), contact);
        List<MabAddress> addresses = getAddressesListFromWeb(contactInfo.getAddresses(), contact);

        contactRepository.save(contact);
        phoneNumberRepository.saveAll(phoneNumbers);
        addressRepository.saveAll(addresses);


    }

    private List<MabAddress> getAddressesListFromWeb(List<MabAddressWeb> addressWebList, MabContact contact) {
        return addressWebList.stream().map(
                addressWeb -> MabAddress.builder()
                        .contact(contact)
                        .country(addressWeb.getCountry())
                        .city(addressWeb.getCity())
                        .street(addressWeb.getStreet())
                        .builder(addressWeb.getBuilder())
                        .apartment(addressWeb.getApartment())
                        .build()
        ).collect(Collectors.toList());
    }

    private List<MabPhoneNumber> getPhonesNumberListFromWeb(List<MabPhoneWeb> phoneWebList, MabContact contact) {
        return phoneWebList.stream().map(
                phoneWeb -> MabPhoneNumber.builder()
                        .contact(contact)
                        .phoneNumber(phoneWeb.getPhoneNumber())
                        .build()
        ).collect(Collectors.toList());
    }


    @Override
    public void updateContact(MabContactInfoResponse contactInfo, Long id) {

        MabContact contact = contactRepository.findById(id).orElseThrow(() -> new NotFoundException("A contact is not exists"));


        phoneNumberRepository.deleteAllByContact(contact);
        addressRepository.deleteAllByContact(contact);

        contact.setFirstName(contactInfo.getFirstName());
        contact.setLastName(contactInfo.getLastName());
        contact.setEmail(contactInfo.getEmail());

        contactRepository.save(contact);
        phoneNumberRepository.saveAll(getPhonesNumberListFromWeb(contactInfo.getPhones(), contact));
        addressRepository.saveAll(getAddressesListFromWeb(contactInfo.getAddresses(), contact));


    }

    @Override
    public void deleteContact(Long id) {
        MabContact contact = contactRepository.findById(id).orElseThrow(NotFoundException::new);
        phoneNumberRepository.deleteAllByContact(contact);
        addressRepository.deleteAllByContact(contact);
        contactRepository.delete(contact);
    }

    @Override
    public MabContactInfoResponse getContactById(Long id) {
        MabContact contact = contactRepository.findById(id).orElseThrow(NotFoundException::new);

        return MabContactInfoResponse.builder()
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .phones(getPhonesNumberListFromDb(contact))
                .addresses(getAddressesListFromDb(contact))
                .build();

    }

    private List<MabAddressWeb> getAddressesListFromDb(MabContact contact) {
        List<MabAddress> addresses = addressRepository.findAllByContact(contact).orElseThrow(NotFoundException::new);
        return addresses.stream().map(address -> MabAddressWeb.builder()
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getBuilder())
                .builder(address.getBuilder())
                .apartment(address.getApartment())
                .build()
        ).collect(Collectors.toList());
    }

    private List<MabPhoneWeb> getPhonesNumberListFromDb(MabContact contact) {
        List<MabPhoneNumber> phoneNumbers = phoneNumberRepository.findAllByContact(contact).orElseThrow(NotFoundException::new);
        return phoneNumbers.stream().map(pNum -> MabPhoneWeb.builder()
                .phoneNumber(pNum.getPhoneNumber())
                .build()).collect(Collectors.toList());
    }

    @Override

    public List<MabContactInfoResponse> getContactList() {
        List<MabContact> contacts = contactRepository.findAll();

        return getContactInfoByContactsList(contacts);

    }

    @Override
    public List<MabContactInfoResponse> getContactsBySearch(MabSearchRequest searchRequest) {
        List<MabContact> contacts = contactRepository.findAllByFirstNameOrLastNameOrPhoneNum(searchRequest.getRequest());

        return getContactInfoByContactsList(contacts);

    }

    private List<MabContactInfoResponse> getContactInfoByContactsList(List<MabContact> contacts) {

      return contacts.stream().map(contact -> MabContactInfoResponse.builder()
              .id(contact.getId())
              .createdDate(contact.getCreatedDate())
              .firstName(contact.getFirstName())
              .lastName(contact.getLastName())
              .email(contact.getEmail())
              .phones(getPhonesNumberListFromDb(contact))
              .addresses(getAddressesListFromDb(contact))
              .build()
      ).collect(Collectors.toList());
    }

}
