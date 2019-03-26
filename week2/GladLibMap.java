import edu.duke.*;
import java.util.*;

/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GladLibMap {
    private ArrayList<String> usedWords;
    private ArrayList<String> categories;
    private HashMap<String, ArrayList<String>> words;
        
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(String source){
        initialize();
        initializeFromSource(source);
    }
    
    public GladLibMap() {
        initialize();
        initializeFromSource(dataSourceDirectory);
    }
    
    private void initialize() {
        words = new HashMap<String, ArrayList<String>>();
        usedWords = new ArrayList<String>(); 
        categories = new ArrayList<String>();
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"country", "noun", "adjective", 
                           "color", "name", "animal",
                           "timeframe", "verb", "fruit"};
        for (String label : labels) {
            ArrayList<String> list = readIt(source+"/"+label+".txt");
            words.put(label, list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        
        if (!categories.contains(label)) categories.add(label);
        return randomFrom(words.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        
        if (usedWords.size() == 0) {
            usedWords.add(sub);
        } else {
            while (usedWords.contains(sub)) {
                sub = getSubstitute(w.substring(first+1, last));
            }
            usedWords.add(sub);
        }
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap() {
        int total = 0;
        for (String key : words.keySet()) {
            total += words.get(key).size();
        }
        return total;        
    }
    
    private int totalWordsConsidered() {
        int total = 0;
        for (String key : categories) {
            total += words.get(key).size();
        }
        return total;        
    }
    
    public void makeStory(){
        usedWords.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate3.txt");
        printOut(story, 60);
        System.out.println("\n\nTotal # of words replaced: "+usedWords.size());
        System.out.println("Total possible words: "+totalWordsInMap());
        System.out.println("Total words considered: "+totalWordsConsidered());
    }
}
