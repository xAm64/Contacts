package fr.fms.buisness;

import fr.fms.entities.*;
import fr.fms.exceprions.ContactException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface IBusiness {

    //contact
    public List<Contact> findAll();
    public void createContact(Contact contact) throws ContactException;
    public Page<Contact> findContactByFirstNameContains(String keyword, Pageable pageable);
    public Page<Contact> findContactByCategoryId(Long categoryId, Pageable pageable);
    public Optional<Contact>findContactById(Long id);

    //catégories
    public List<Category> findAllCategories();

}
