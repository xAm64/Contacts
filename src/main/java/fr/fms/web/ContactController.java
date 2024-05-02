package fr.fms.web;

import fr.fms.buisness.IBusinessImpl;
import fr.fms.dao.CategoryRepository;
import fr.fms.dao.ContactRepository;
import fr.fms.entities.*;
import fr.fms.exceprions.ContactException;
import groovy.util.logging.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class ContactController {
    private static final Logger log = LoggerFactory.getLogger(ContactController.class);
    private final IBusinessImpl buisness;
    private static final String VALIDATION_ERROR_MSG = "Erreur de validation du formulaire: {}";
    private static final String CONTACT_LOAD_SUCCESS_MSG = "Chargement des articles réussi.";

    public ContactController(IBusinessImpl buisness){
        this.buisness = buisness;
    }

    //page d'accueil
    @GetMapping({"/", ""})
    public String index (Model model, Long id , @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "keyword", defaultValue = "") String kw, Authentication authentication){
        boolean isUserAuthenticated = buisness.isUserAuthenticated();
        model.addAttribute("isUserAuthenticated", isUserAuthenticated);
        List<Category> listCategory = buisness.findAllCategories();
        Page<Contact> contacts;
        if (id == null){
            contacts = buisness.findContactByFirstNameContains(kw,(Pageable) PageRequest.of(page, 15));
            model.addAttribute("keyword", kw);
            log.info(CONTACT_LOAD_SUCCESS_MSG);
        } else {
            contacts = buisness.findContactByCategoryId(id,(Pageable) PageRequest.of(page, 15));
            model.addAttribute("idCat", id);
            log.info(CONTACT_LOAD_SUCCESS_MSG);
        }
        model.addAttribute("listContact", contacts.getContent());
        model.addAttribute("listCategory", listCategory);
        model.addAttribute("pages", new int[contacts.getTotalPages()]);
        model.addAttribute("currentPage", page);
        return "index";
    }

    //ajouter un contact
    @GetMapping("/addContact")
    public String addContact(Model model){
        boolean isUserAuthenticated = buisness.isUserAuthenticated();
        List<Category> categories = buisness.findAllCategories();
        model.addAttribute("isUserAuthenticated", isUserAuthenticated);
        model.addAttribute("listCategories", categories);
        model.addAttribute("contact", new Contact());
        return "createContact";
    }
    //sauvegarder un contact
    @PostMapping("/saveContact")
    public String save(Model model, @Valid Contact contact, BindingResult bindingResult) throws ContactException{
        boolean isUserAuthenticated = buisness.isUserAuthenticated();
        List<Category> categories = buisness.findAllCategories();
        model.addAttribute("isUserAuthenticated", isUserAuthenticated);
        model.addAttribute("listCategories", categories);
        if (bindingResult.hasErrors()){
            return "/addContact";
        }
        try {
            buisness.createContact(contact);
        } catch (ContactException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "/addContact";
        }
        return "redirect:/";
    }

    //page d'accès refusé.
    @GetMapping("/403")
    public String error(Model model) {
        boolean isUserAuthenticated = buisness.isUserAuthenticated();
        model.addAttribute("isUserAuthenticated", isUserAuthenticated);
        return "403";
    }

    //pour tester la page 500
    /*
    @GetMapping("test500")
    public String test500(){
        return "jExistePas";
    }
     */
}
