
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        if (ch == 'a' || 
            ch == 'e' ||
            ch == 'i' ||
            ch == 'o' ||
            ch == 'u') {
                return true;
        } else {
            return false;
        }
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder replaced = new StringBuilder();
        for (int i = 0; i < phrase.length(); i++) {
            char check = phrase.charAt(i);
            if (isVowel(check)) replaced.append(ch);
            else replaced.append(check);
        }
        return replaced.toString();
    }
    
    public String emphasize (String phrase, char ch) {
        StringBuilder replaced = new StringBuilder();
        for (int i = 0; i < phrase.length(); i++) {
            char check = Character.toLowerCase(phrase.charAt(i));
            if (check == Character.toLowerCase(ch)) {
                if (i % 2 == 0) replaced.append('*');
                else replaced.append('+');
            } else {
                replaced.append(phrase.charAt(i));
            }
        }
        return replaced.toString();
    }
    
    public void testVowel() {
        System.out.println("a " + isVowel('a')); 
        System.out.println("b " + isVowel('b'));
        System.out.println("R " + isVowel('R'));
        System.out.println("I " + isVowel('I'));
        System.out.println("4 " + isVowel('4'));
    }
    
    public void testReplaceVowels() {
        String phrase = "Hello, World!";
        System.out.println(phrase + " -> " + replaceVowels(phrase, '*'));
    }
    
    public void testEmphasize() {
        String phrase = "Mary Bella Abracadabra";
        System.out.println(emphasize(phrase, 'a'));
    }
}
