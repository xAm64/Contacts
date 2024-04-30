package fr.fms.buisness;

import fr.fms.dao.*;
import fr.fms.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
}
