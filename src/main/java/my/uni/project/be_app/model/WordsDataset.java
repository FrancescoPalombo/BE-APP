package my.uni.project.be_app.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class WordsDataset {
    private ArrayList<String> dataset = new ArrayList<>();
    
    public WordsDataset(){   
        try(
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
    public int getLength(){
        return dataset.size();
    }
    public String getDataset(int n){
        return dataset.get(n);        
    }
    public void printDataset(){
        for(String word : dataset){
            System.out.println(word);
        }
    }
}
