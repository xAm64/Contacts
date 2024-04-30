package fr.fms.controllers;

import fr.fms.buisness.IBusinessImpl;
import fr.fms.dao.ContactRepository;
import fr.fms.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class ContactController {
    private final IBusinessImpl buisness;
    @Autowired
    private ContactRepository contactRepository;

    public ContactController(IBusinessImpl buisness){
        this.buisness = buisness;
    }

    @GetMapping({"/", ""})
    public String index (Model model){
        List<Contact> contacts = contactRepository.findAll();
        model.addAttribute("contacts", contacts);
        return "index";
    }
}
