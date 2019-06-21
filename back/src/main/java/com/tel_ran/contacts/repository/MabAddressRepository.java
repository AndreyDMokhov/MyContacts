package com.tel_ran.contacts.repository;

import com.tel_ran.contacts.model.entity.MabAddress;
import com.tel_ran.contacts.model.entity.MabContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MabAddressRepository extends JpaRepository<MabAddress,Long> {
   Optional<List<MabAddress>> findAllByContact(MabContact contact);
   void deleteAllByContact(MabContact contact);
}
