package my.uni.project.be_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import my.uni.project.be_app.model.Message;
import my.uni.project.be_app.service.ChatService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")  // Abilita CORS solo per questo controller
public class ChatController {

    @Autowired
    private ChatService service;
    
    @GetMapping("/chat")    //Invio Messaggio moderato
    public List<Message> getMessage(){
        return service.getMessages();
    }
    @PostMapping("/chat")    //Ricevo Messaggio da moderare
    public void setMessage(@RequestBody Message message) {
        System.out.println("Messaggio ricevuto nel controller: " + message);
        if (message != null && message.getMessage() != null) {
            service.setMessage(message);    //Richiamo servizio per moderare il messaggio
        } else {    //Controllo contenuto NULL
            System.out.println("Messaggio ricevuto è nullo.");
        }
    }
    
}
