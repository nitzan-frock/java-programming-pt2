import java.util.*;
import java.io.*;
import edu.duke.*;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordsInFiles;
    
    public WordsInFiles () {
        wordsInFiles = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        for (String word : fr.words()) {
            if (wordsInFiles.containsKey(word)) {
                ArrayList files = wordsInFiles.get(word);
                if (!files.contains(f.getName())) {
                    files.add(f.getName());
                    wordsInFiles.put(word,files);
                }
            } else {
                ArrayList files = new ArrayList<String>();
                files.add(f.getName());
                wordsInFiles.put(word, files);
            }
        }
    }
    
    private void buildWordFileMap() {
        wordsInFiles.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber() {
        int max = 0;
        for (String key : wordsInFiles.keySet()) {
            int numFiles = wordsInFiles.get(key).size();
            if (max < numFiles) max = numFiles;
        } 
        return max;
    }
}
