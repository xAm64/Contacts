package fr.fms;

import fr.fms.buisness.IBusinessImpl;
import fr.fms.entities.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ContactsApplicationTests {

	@Autowired
	IBusinessImpl buisness;

	@Test
	void loadContact(){
		buisness.findAll();
	}

}
