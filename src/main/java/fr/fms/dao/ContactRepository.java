package fr.fms.dao;

import java.util.List;
import java.util.Optional;

import fr.fms.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository  extends JpaRepository<Contact, Integer> {
    Page<Contact> findContactByFirstNameContains(String description, Pageable pageable);
    Page<Contact> findByCategoryId(Long id, Pageable pageable);
    Optional<Contact> findById(Long id);
    void deleteContactById(Long id);
}
