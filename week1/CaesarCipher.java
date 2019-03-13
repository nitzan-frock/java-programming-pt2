
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private String alphabet;
    private String shifted;
    private int mainKey;
    
    public CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        mainKey = key;
        shifted = alphabet.substring(key) + alphabet.substring(0, key);
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        
        for (int i = 0; i < input.length(); i++) {
            char currChar = encrypted.charAt(i);
            boolean isUpperCase = Character.isUpperCase(currChar);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            
            if (idx != -1) {
                char newChar = shifted.charAt(idx);
                if (!isUpperCase) {
                    newChar = Character.toLowerCase(newChar);
                }
                encrypted.setCharAt(i, newChar);
            }
        }
        
        return encrypted.toString();
    }
    
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        return cc.encrypt(input);
    }
}
