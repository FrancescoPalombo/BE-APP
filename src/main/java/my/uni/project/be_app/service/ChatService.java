package my.uni.project.be_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import my.uni.project.be_app.model.Message;
import my.uni.project.be_app.model.WordsDataset;

@Service
public class ChatService {
    
    private List<Message> messages = new ArrayList<>();

    private final WordsDataset bannedWords;
    private final int n;

    public ChatService(WordsDataset bannedWords){
        this.bannedWords=bannedWords;
        this.n=this.bannedWords.getLength();
    }

    public List<Message> getMessages(){
        return messages;
    }

    public void setMessage(Message message){
        if (message == null || message.getMessage() == null) {
            System.out.println("Messaggio nullo, restituito messaggio vuoto.");
        }
        else{
            boolean changed=false;
            String[] words = message.getMessage().split(" ");
            int size = words.length;
            boolean found;
            for(int i=0; i<size; i++){
                found = binarySearch(words[i]);
                if(found){words[i]="***"; changed=true;}
            }
            Message newMessage = new Message("");
            if(changed){
                String moderatedMessage = String.join(" ", words);
                newMessage.setMessage(moderatedMessage);
                System.out.println(moderatedMessage);
            }
            else{
                newMessage.setMessage(message.getMessage());
            }
            messages.add(newMessage);
        }
    }

    private boolean binarySearch(String word){
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
