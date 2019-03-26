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
        for (String word : wordsInFiles.keySet()) {
            int numFiles = wordsInFiles.get(word).size();
            if (max < numFiles) max = numFiles;
        } 
        return max;
    }
    
    private ArrayList wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<String>();
        for (String word : wordsInFiles.keySet()) {
            if (number == wordsInFiles.get(word).size()) words.add(word);
        }
        return words;
    }
    
    private void printFilesIn(String word) {
        ArrayList<String> files = wordsInFiles.get(word);
        for (String file : files) {
            System.out.println(file);
        }
    }
    
    public void tester() {
        buildWordFileMap();
        int max = maxNumber();
        ArrayList<String> words = wordsInNumFiles(max);
        System.out.println("Greatest number of files a word appears in is "+max+", and there are "+words.size()+" such words: ");
        for (int k = 0; k < words.size(); k++) {
            String word = words.get(k);
            if (k == words.size()-1) System.out.print("\""+word+"\"\n");
            else System.out.print("\""+word+"\",");
        }
        
        System.out.println("Words appearing in 4 files: "+wordsInNumFiles(4).size());
        
        for (String file : wordsInFiles.get("tree")) {
            System.out.println(file);
        }
        /*
        for (String word : words) {
            ArrayList<String> files = wordsInFiles.get(word);
            System.out.print("\""+word+"\" appears in the files: ");
            for (int k = 0; k < files.size(); k++) {
                if (k == files.size()-1) System.out.print(files.get(k)+"\n");
                else System.out.print(files.get(k)+", ");                
            }
        }
        */
    }
}
