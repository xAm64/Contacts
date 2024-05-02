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

    //catégories
    public Optional<Category> findCategoryById(Long id);
    public List<Category> findAllCategories();
    public void createContact(Contact contact) throws ContactException;
}
