package my.uni.project.be_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import my.uni.project.be_app.model.Message;
import my.uni.project.be_app.model.WordsDataset;

@Service
public class ChatService {    //Servizio Gestione Messaggi Ricevuti dal Controllore
    
    private List<Message> messages = new ArrayList<>();

    private final WordsDataset bannedWords;
    private final int n;

    public ChatService(WordsDataset bannedWords){
        this.bannedWords=bannedWords;
        this.n=this.bannedWords.getLength();
    }

    public List<Message> getMessages(){    //Metodo Get Cronologia Messaggi Inviati
        return messages;
    }

    public void setMessage(Message message){    //Metodo iniziale per Moderazione Messaggio
        if (message == null || message.getMessage() == null) {    //Gestione errore in caso di messaggio vuoto
            System.out.println("Messaggio nullo, restituito messaggio vuoto.");
        }
        else{
            //Imposto le variabili per gestire la moderazione del Messaggio
            boolean changed=false;
            String[] words = message.getMessage().split(" ");
            int size = words.length;
            boolean found;
            //ciclo for per ogni parola del messaggio, usa Metodo di ricerca binaria per confrontarlo col Dataset
            for(int i=0; i<size; i++){
                found = binarySearch(words[i]);
                if(found){words[i]="***"; changed=true;}
            }
            Message newMessage = new Message(""); //Creo un oggetto Messaggio per salvare il messaggio ricevuto Moderato o meno
            if(changed){
                String moderatedMessage = String.join(" ", words);
                newMessage.setMessage(moderatedMessage);
                System.out.println(moderatedMessage);
            }
            else{
                newMessage.setMessage(message.getMessage());
            }
            messages.add(newMessage); //Aggiungo  il messaggio alla cronologia dei messaggi ricevuti
        }
    }

    private boolean binarySearch(String word){    //Metodo ricerca binaria sul dataset data una parola del messaggio
        int first=0, last=n-1, med=(first+last)/2;
        boolean found=false;
        while((first<=last)&&(!found)){
            if(word.equals(bannedWords.getDataset(med))){
                found=true;
            } else{
                if(word.compareTo(bannedWords.getDataset(med))<0){
                    last=med-1;
                } else{
                    first=med+1;
                }
                med=(first+last)/2;
            }
        }
        return found;
    }
}
