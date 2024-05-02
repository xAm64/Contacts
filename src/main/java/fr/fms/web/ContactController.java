package fr.fms.web;

import fr.fms.buisness.IBusinessImpl;
import fr.fms.dao.ContactRepository;
import fr.fms.entities.*;
import fr.fms.exceprions.ContactException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ContactController {
    private final IBusinessImpl buisness;

    @Autowired
    private ContactRepository contactRepository;

    public ContactController(IBusinessImpl buisness){
        this.buisness = buisness;
    }

    //page d'accueil
    @GetMapping({"/", ""})
    public String index (Model model){
        boolean isUserAuthenticated = buisness.isUserAuthenticated();
        List<Contact> contacts = contactRepository.findAll();
        List<Category> categories = buisness.findAllCategories();
        model.addAttribute("contacts", contacts);
        model.addAttribute("listCategories", categories);
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
