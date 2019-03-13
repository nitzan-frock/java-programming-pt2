import edu.duke.*;

/**
 * Write a description of testCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class testCaesarCipher {
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
    
    public void simpleTests() {
        FileResource fr = new FileResource("data/data.txt");
        
        int key = 15;
        
        CaesarCipher cc = new CaesarCipher(key);
        String encrypted = cc.encrypt(fr.asString());
        System.out.println("encrypted: \n" + encrypted);
        System.out.println("decrypted with object: \n" + cc.decrypt(encrypted));
        System.out.println("decrypted with breaker: \n" + breakCaesarCipher(encrypted));
    }
    
    private String breakCaesarCipher(String input) {
        int dkey = getKey(input);
        CaesarCipher cc = new CaesarCipher(dkey);
        return cc.encrypt(input);
    }
    
    private int getKey(String s) {
        int[] freqs = countLetters(s);
        int max = indexOfMax(freqs);
        
        return max < 4 ? 26-(26-(4-max)) : 26-(max-4);
    }
}
