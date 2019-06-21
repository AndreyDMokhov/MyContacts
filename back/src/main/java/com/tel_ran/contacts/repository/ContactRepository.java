package com.tel_ran.contacts.repository;

import com.tel_ran.contacts.model.entity.MabContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<MabContact, Long> {

/*    @Query("SELECT c.id, c.firstName, c.lastName from MabContact c inner join MabPhoneNumber p on c.id=p.contact " +
            "where c.lastName like concat('%', :req, '%') or c.lastName like concat('%', :req, '%') or p.phoneNumber like concat('%', :req, '%')")*/

    @Query("SELECT c.id, c.firstName, c.lastName from  MabPhoneNumber p " +
            "join p.contact c  " +
            "where c.firstName like concat('%', ?1, '%') or  c.lastName like concat('%', ?1, '%') or p.phoneNumber like concat('%', ?1, '%') ")
    List<MabContact> findAllByFirstNameOrLastNameOrPhoneNum(String searchRequest);

}
