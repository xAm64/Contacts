package fr.fms.exceprions;

import fr.fms.entities.Contact;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ContactException extends  Exception{
    public ContactException(String message){
        super(message);
    }
}
