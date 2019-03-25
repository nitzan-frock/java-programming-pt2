import edu.duke.*;
import java.util.*;
/**
 * Write a description of Codon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Codon {
    private HashMap<String, Integer> codonCounts;
    
    public Codon(){
        codonCounts = new HashMap<String, Integer>();
    }
    
    private void buildCodonMap(int start, String dna) {
        int counter = 0;
        for (int k = start; k < dna.length(); k++) {
            counter++;
            if (counter != 0 && counter % 3 == 0) {
                String codon = dna.substring(k-2, counter+start);               
                if (!codonCounts.containsKey(codon)) {
                    codonCounts.put(codon, 1);
                } else {
                    int codonCount = codonCounts.get(codon);
                    codonCounts.put(codon, codonCount+1);
                }
            }
        }
    }
    
    private String getMostCommonCodon() {
        String common = "";
        int max = 0;
        for (String codon : codonCounts.keySet()) {
            if (max < codonCounts.get(codon)) {
                max = codonCounts.get(codon);
                common = codon;
            }
        }
        
        return common;
    }
    
    private void printCodonCounts(int start, int end) {
        for (String codon : codonCounts.keySet()) {
            int count = codonCounts.get(codon);
            if (count >= start && count <= end) System.out.println(codon+" "+count);
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        
        String dna = fr.asString().toUpperCase().trim();
        
        for (int k = 0; k < 3; k++) {
            codonCounts.clear();
            buildCodonMap(k, dna);
            
            int start = 1;
            int end = 5;
            
            System.out.println("Reading frame starting with "+k+" results in "+codonCounts.size()+" unique codons");
            System.out.println("Most common codon: "+getMostCommonCodon()+" "+codonCounts.get(getMostCommonCodon()));
            System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:");
            printCodonCounts(start, end);
            System.out.println();
        }
        
        
    }
}
