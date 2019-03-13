import edu.duke.*;

/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipherTwo {
    private String halfOfString(String message, int start) {
        StringBuilder everyOther = new StringBuilder();
        for (int k = start; k < message.length(); k += 2) {
            everyOther.append(message.charAt(k));
        } 
        return everyOther.toString();
    }
    
    private int[] countLetters(String message) {
        int[] counts = new int[26];
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        for (int k = 0; k < message.length(); k++) {
            char c = Character.toLowerCase(message.charAt(k));
            int idx = alpha.indexOf(c);
            if (idx != -1) counts[idx] += 1;
        }
        return counts;
    }
    
    private int indexOfMax(int[] values) {
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
    
    private int getKey(String s) {
        int[] freqs = countLetters(s);
        int max = indexOfMax(freqs);
        
        return max < 4 ? 26-(26-(4-max)) : 26-(max-4);
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource("data/data.txt");
        
        int key1 = 0;
        int key2 = 0;
        
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        
        String encrypted = cc.encrypt(fr.asString());
        System.out.println("encrypted: \n" + encrypted);
        System.out.println("decrypted with object: \n" + cc.decrypt(encrypted));
        System.out.println("decrypted with breaker: \n" + breakCaesarCipher(encrypted));
    }
    
    private String breakCaesarCipher(String input){
        String first = halfOfString(input, 0);
        String second = halfOfString(input, 1);
        
        int dkey1 = getKey(first);
        int dkey2 = getKey(second);
        
        System.out.println("original key 1: " + (26-dkey1) + "\noriginal key 2: " + (26-dkey2));
        
        CaesarCipherTwo cc = new CaesarCipherTwo(dkey1, dkey2);
        return cc.encrypt(input);
    }
}
