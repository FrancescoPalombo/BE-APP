package my.uni.project.be_app.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class WordsDataset {    //Modello Oggetto Dataset parole bannate
    private ArrayList<String> dataset = new ArrayList<>();
    
    public WordsDataset(){   
        try(    //Gestione Eccezzioni per apertura file con gestione degli errori
            InputStream is = getClass().getClassLoader().getResourceAsStream("lista_badwords.txt");
            BufferedReader inFile = new BufferedReader(new InputStreamReader(is));){
                String line;
                while((line = inFile.readLine())!=null){
                    dataset.add(line);
                }
        }
        catch(FileNotFoundException e){
            System.out.println("File not Found");
        } catch (IOException e) {
            throw new IllegalStateException("Error reading the file: lista_badwords.txt", e);
        }        
    }
    public int getLength(){    //Metodo get Lunghezza ArrayList Dataset
        return dataset.size();
    }
    public String getDataset(int n){    //Metodo get Elemento n Dataset
        return dataset.get(n);        
    }
    public void printDataset(){    //Metodo stampa a video controlloro presenza Dataset
        for(String word : dataset){
            System.out.println(word);
        }
    }
}
