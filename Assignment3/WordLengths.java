import edu.duke.*;
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        for (String word : resource.words()) {
            int nonLetters = 0;
            int letters = 0;
            for (int k = 0; k < word.length(); k++){
                char currChar = word.charAt(k);
                if (currChar == '-' 
                    && Character.isLetter(word.charAt(k-1)) 
                    && Character.isLetter(word.charAt(k+1))){
                        letters++;
                } else if (Character.isLetter(currChar)){
                    letters++;
                }
            }
            
            if (letters >= 30) counts[30] += 1;
            else counts[letters] += 1;
        }
        
        for (int k = 1; k < counts.length; k++) {
            System.out.println(counts[k] + " words of length " + k);
        }
    }
    
    public int indexOfMax(int[] values) {
        int index = 0;
        int max = 0;
        for (int k = 0; k < values.length; k++) {
            if (values[k] > max) {
                max = values[k];
                index = k;
            }
        }
        return index;
    }

    public void testCountWordLengths() {
        FileResource fr = new FileResource("data/data.txt");
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        System.out.println("Most common length of word: " + indexOfMax(counts));
    }
}
