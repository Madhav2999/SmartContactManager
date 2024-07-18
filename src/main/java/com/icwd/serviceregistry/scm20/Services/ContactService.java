package com.icwd.serviceregistry.scm20.Services;

import com.icwd.serviceregistry.scm20.entities.Contact;
import com.icwd.serviceregistry.scm20.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContactService {
    Contact save(Contact contact);

    Contact update(Contact contact);

    List<Contact> getAll();

    void delete(String id);

    Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order, User user);

    Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order, User user);

    Page<Contact> searchByPhoneNumber(String phoneNumberKeyword, int size, int page, String sortBy, String order,
                                      User user);

    List<Contact> getByUserId(String userId);

    Contact getById(String id);

    Page<Contact> getByUser(User user, int page, int size, String sortField, String sortDirection);
}
