package fr.fms.buisness;

import fr.fms.dao.*;
import fr.fms.entities.*;
import fr.fms.exceprions.ContactException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.*;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class IBusinessImpl implements IBusiness {

    @Autowired
    ContactRepository contactRepository;
    @Autowired
    CategoryRepository categoryRepository;

    //contacts
    /**
     * Récupère tous les articles
     *
     * @return Liste contenant tous les articles
     */
    public List<Contact> findAllArticle() { return contactRepository.findAll(); }

    @Override
    public List<Contact> findAll() {
        return Collections.emptyList();
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Category> findAllCategories() {
        return Collections.emptyList();
    }

    public void createContact(Contact newContact) throws ContactException{
        List<Contact> contactList = contactRepository.findAll();
        for (Contact contact: contactList){
            if (    newContact.getFirstName().equals(contact.getFirstName()) &&
                    newContact.getLastName().equals(contact.getLastName()) &&
                    newContact.getEmail().equals(contact.getEmail()) &&
                    newContact.getPhone().equals(contact.getPhone())){
                throw new ContactException("Ce contact existe déjà");
            }
        }
        contactRepository.save(newContact);
    }

    public Page<Contact> findContactByFirstNameContains(String keyword, Pageable pageable) {
        return contactRepository.findContactByFirstNameContains(keyword, pageable);
    }
    public Page<Contact>findContactByCategoryId(Long categoryId, Pageable pageable){
        return contactRepository.findByCategoryId(categoryId, pageable);
    }

    //Authentification
    public boolean isUserAuthenticated(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken);
    }
}
