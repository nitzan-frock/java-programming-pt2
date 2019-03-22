import edu.duke.*;
import java.util.ArrayList;
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        
        for (String s : fr.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1) {
                myWords.add(s);
                myFreqs.add(1);
            } else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value+1);
            }
        }
    }
    
    public int findIndexOfMax() {
        int max = 0; 
        int index = 0;
        for (int k = 0; k < myFreqs.size(); k++) {
            if (max < myFreqs.get(k)) {
                max = myFreqs.get(k);
                index = k;
            }
        }
        return index;
    }
    
    public void tester() {
        findUnique();
        for (int k = 0; k < myWords.size(); k++) {
            System.out.println(myFreqs.get(k)+"\t"+myWords.get(k));
        }
        
        int index = findIndexOfMax();
        System.out.println("# unique words: "+myWords.size());
        String output = "The word that occurs most often and its count are: ";
        System.out.println(output + myWords.get(index) + " "+ myFreqs.get(index));
    }
}
