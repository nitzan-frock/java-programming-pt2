import edu.duke.*;

/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarBreaker {
    public int[] countLetters(String message) {
        int[] counts = new int[26];
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        for (int k = 0; k < message.length(); k++) {
            char c = Character.toLowerCase(message.charAt(k));
            int idx = alpha.indexOf(c);
            if (idx != -1) counts[idx] += 1;
        }
        return counts;
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
    
    public int getKey(String s) {
        int[] freqs = countLetters(s);
        int max = indexOfMax(freqs);
        
        return max < 4 ? 26-(26-(4-max)) : 26-(max-4);
    }
    
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int decryptKey = getKey(encrypted);
        
        return cc.encrypt(encrypted, decryptKey);
    }
    
    public String decryptTwoKeys(String encrypted) {
        String first = halfOfString(encrypted, 0);
        String second = halfOfString(encrypted, 1);
        
        CaesarCipher cc = new CaesarCipher();
        int dkey1 = getKey(first);
        int dkey2 = getKey(second);
        
        System.out.println("original key 1: " + (26-dkey1) + "\noriginal key 2: " + (26-dkey2));
    
        return cc.encryptTwoKeys(encrypted, dkey1, dkey2);
    }
    
    public String halfOfString(String message, int start) {
        StringBuilder everyOther = new StringBuilder();
        for (int k = start; k < message.length(); k += 2) {
            everyOther.append(message.charAt(k));
        } 
        return everyOther.toString();
    }
    
    public void testDecrypt() {
        CaesarCipher cc = new CaesarCipher();
        
        FileResource fr = new FileResource("data/plain.txt");
        String plainFile = fr.asString();
        String encrypted2 = cc.encryptTwoKeys(plainFile, 17, 4);
        System.out.println(encrypted2);
        String decrypted2 = decryptTwoKeys(encrypted2);
        System.out.println(decrypted2);
    }
}
