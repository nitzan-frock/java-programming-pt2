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
            char c = message.charAt(k);
            int idx = alpha.indexOf(c);
            if (idx != -1) counts[idx]++;
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
    
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int commonLetterIdx = indexOfMax(freqs);
        int decryptKey = commonLetterIdx - 4;
        if (commonLetterIdx < 4) decryptKey = 26 - (4 - commonLetterIdx);
        
        return cc.encrypt(encrypted, 26-decryptKey);
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
        String msg = "MY PASSWORD IS YOU SHALL NOT DECRYPT MEEEEEEEEEEEEEEEEEEEEEEEE!";
        int key = 11;
        String encrypted = cc.encrypt(msg, 26 - key);
        String decrypted = decrypt(encrypted);
        System.out.println(encrypted + "\n" + decrypted);
    }
}
