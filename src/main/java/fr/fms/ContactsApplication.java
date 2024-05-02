package fr.fms;

import fr.fms.dao.*;
import fr.fms.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactsApplication  implements CommandLineRunner{

	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private CategoryRepository categoryRepository;


	public static void main(String[] args) {
		SpringApplication.run(ContactsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createContacts();
	}

	//pour créer les contacts
	public void createContacts(){
		Category personnel = categoryRepository.save(new Category("Amis", "Mes amis", null));
		Category profesionnels = categoryRepository.save(new Category("Profesionnels", "Mes contacts d'entreprise", null));
		Category famille = categoryRepository.save(new Category("Famille", "Membres de famille", null));
		Category divers = categoryRepository.save(new Category("Divers", "Les autres contact", null));

		contactRepository.save(new Contact("Manex", "", "manex@eval.com", "0559705494", "chemin de l'évaluation", personnel));
		contactRepository.save(new Contact("Donovan", "Ges", "donovan@eval.com", "0559943568", "rue de l'évaluation", personnel));
		contactRepository.save(new Contact("Tristan", "Uewhrd", "tristan@eval.com", "0559042672", "route des inventeurs", profesionnels));
		contactRepository.save(new Contact("Claire", "yowgcw", "claire@eval.com", "0559837707", "chemin des rosiers", personnel));
		contactRepository.save(new Contact("Arthur", "Leeroy", "leeroy@eval.com", "0558772543", "Rue de Leeroy Jenkins", divers));
		contactRepository.save(new Contact("Alejandra", "Ramirez", "alejandra@eval.com", "0554946214", "Porte de l'españa", divers));
		contactRepository.save(new Contact("Frederic", "Bec", "fred@eval.com", "0554925677", "Route des aventuriers", personnel));
		contactRepository.save(new Contact("Claire", "hods", "claire@famile.com", "0194236723", "", famille));
		contactRepository.save(new Contact("Mohamed", "Elbabili", "mohamed@eval.com", "0783157603", "", profesionnels));
		contactRepository.save(new Contact("Arnaud", "Richard", "arnaud@eval.com", "0482248396", "Crp de Beterette TAI", divers));
		contactRepository.save(new Contact("Mathieu", "Fix", "mathieu@eval.com", "0358435176", "Crp de Beterette TAI", divers));
		contactRepository.save(new Contact("Remy", "", "remy@eval.com", "", "", divers));
		contactRepository.save(new Contact("Julien", "Lapertot", "julien@eval.com", "0610579344", "chemin de la passion de Python", divers));
		contactRepository.save(new Contact("Isnaa", "Toffah", "isnaa@eval.com", "", "Rue du massacre", personnel));
		contactRepository.save(new Contact("Dudiir", "", "dudiir@eval.com", "", "Chemin du chaman", personnel));
		contactRepository.save(new Contact("Oatscar", "", "oatscar@eval.com", "", "Chemin du meuuuuh", personnel));
		contactRepository.save(new Contact("Gilles", "", "gilles@eval.com", "0505487065", "Saint palais", personnel));
	}
}
