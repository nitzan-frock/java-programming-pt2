
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shifted = alphabet.substring(key) + alphabet.substring(0, key);
        
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
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shifted1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shifted2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        
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
    
    public void testCaesar() {
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key = 15;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
        
        String encrypted2 = encryptTwoKeys(message, 8, 21);
        System.out.println(encrypted2);
    }
}
