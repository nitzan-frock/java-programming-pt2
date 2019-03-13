
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shifted1;
    private String shifted2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        mainKey1 = key1;
        mainKey2 = key2;
        shifted1 = alphabet.substring(mainKey1) + alphabet.substring(0, mainKey1);
        shifted2 = alphabet.substring(mainKey2) + alphabet.substring(0, mainKey2);
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            boolean isUpperCase = Character.isUpperCase(currChar);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if (idx != -1) {
                if (i % 2 == 0) {
                    // Encrypt with key1
                    char newChar = shifted1.charAt(idx);
                    if (!isUpperCase) newChar = Character.toLowerCase(newChar);
                    encrypted.setCharAt(i, newChar);
                } else {
                    // Encrypt with key2
                    char newChar = shifted2.charAt(idx);
                    if (!isUpperCase) newChar = Character.toLowerCase(newChar);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        
        return encrypted.toString();
    }

    public String decrypt(String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo((26-mainKey1), (26-mainKey2));
        return cc.encrypt(input);
    }
}
