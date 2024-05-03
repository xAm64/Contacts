package fr.fms;

import fr.fms.dao.CategoryRepository;
import fr.fms.dao.ContactRepository;
import fr.fms.entities.Category;
import fr.fms.entities.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContactsApplicationTests {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
            public void addContactTest(){
        Category personnel = categoryRepository.save(new Category("Amis", "Mes amis", null));
        contactRepository.save(new Contact("Donovan", "Ges", "donovan@eval.com", "0559943568", "rue de l'évaluation", personnel));
    }

}
