import edu.duke.*;
import java.util.*;
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> freqs;
    
    public CharactersInPlay() {
        characters = new ArrayList<String>();
        freqs = new ArrayList<Integer>();
    }
    
    private void update(String person) {
        System.out.println(person);
        if (characters.contains(person)) {
            int idx = characters.indexOf(person);
            int freq = freqs.get(idx);
            freqs.set(idx, freq+1);
        } else {
            characters.add(person);
            freqs.add(1); 
        }
    }
    
    private void findAllCharacters() {
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            for (int k = 0; k < line.length(); k++) {
                if (line.charAt(k) == '.') {
                    update(line.substring(0,k));
                    break;
                }
            }
        }
    }
    
    private void charactersWithNumParts(int num1, int num2) {
        for (int k = 0; k < characters.size(); k++) {
            if (freqs.get(k) >= num1 && freqs.get(k) <= num2) {
                System.out.println(characters.get(k)+" "+ freqs.get(k));
            }
        }
    }
    
    public void tester() {
        characters.clear();
        freqs.clear();
        findAllCharacters();
        
        
        charactersWithNumParts(10, 15);
    }
}
