package com.icwd.serviceregistry.scm20.Repository;

import com.icwd.serviceregistry.scm20.entities.Contact;
import com.icwd.serviceregistry.scm20.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ContactRepo extends JpaRepository<Contact, String>
{
   Page<Contact> findByUser(User user, Pageable pageable);

   @Query("SELECT c FROM Contact c WHERE c.user.userId = :userId")
   List<Contact>findByUserId(@Param("userId")String userId);

   Page<Contact> findByUserAndNameContaining(User user, String namekeyword, Pageable pageable);

   Page<Contact> findByUserAndEmailContaining(User user, String emailkeyword, Pageable pageable);

   Page<Contact> findByUserAndPhoneNumberContaining(User user, String phonekeyword, Pageable pageable);
}
