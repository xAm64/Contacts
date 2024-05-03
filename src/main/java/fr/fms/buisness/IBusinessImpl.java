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

    //########## Catégories #############
    //Liste des catégories
    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
    //trouver une par son id
    public Optional<Category>findCategoryById(Long id){
        return categoryRepository.findById(id);
    }
    //########## Contacts #############
    //liste des contacts
    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }
    //créer un contact
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
    //recherche par nom de famille
    public Page<Contact> findContactByFirstNameContains(String keyword, Pageable pageable) {
        return contactRepository.findContactByFirstNameContains(keyword, pageable);
    }
    //recherche par catégories
    public Page<Contact>findContactByCategoryId(Long categoryId, Pageable pageable){
        return contactRepository.findByCategoryId(categoryId, pageable);
    }
    //recherche par id
    public Optional<Contact> findContactById(Long id){
        return contactRepository.findById(id);
    }
    //Mise a jour d'un contact
    public void updateContact(Contact contact){
        Optional<Contact>optionalContact = contactRepository.findById(contact.getId());
        if (optionalContact.isPresent()){
            Contact exisistContact = optionalContact.get();
            exisistContact.setFirstName(contact.getFirstName());
            exisistContact.setLastName(contact.getLastName());
            exisistContact.setEmail(contact.getEmail());
            exisistContact.setPhone(contact.getPhone());
            exisistContact.setAddress(contact.getAddress());
            exisistContact.setCategory(contact.getCategory());
            contactRepository.save(exisistContact);
        }
    }
    //Authentification
    public boolean isUserAuthenticated(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken);
    }
}