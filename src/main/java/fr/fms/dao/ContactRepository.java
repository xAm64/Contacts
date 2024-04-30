package fr.fms.dao;

import java.util.List;
import fr.fms.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository  extends JpaRepository<Contact, Integer> {
    public List<Contact> findByFirstName(String firstName);
    public List<Contact> findByLastName(String lastName);
}
