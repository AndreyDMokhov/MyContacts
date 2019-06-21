package com.tel_ran.contacts.repository;

import com.tel_ran.contacts.model.entity.MabContact;
import com.tel_ran.contacts.model.entity.MabPhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MabPhoneNumberRepository extends JpaRepository<MabPhoneNumber, Long> {
  Optional<List<MabPhoneNumber>> findAllByContact(MabContact contact);
  void deleteAllByContact(MabContact contact);
}
