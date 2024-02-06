import java.util.Scanner;

public class CaesarCipher {

    static String caesarEncrypt(String text, int shift) {
        char[] result = new char[text.length()];

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            if (Character.isLetter(currentChar)) {
                char baseChar = Character.isUpperCase(currentChar) ? 'A' : 'a';
                result[i] = (char)(((currentChar - baseChar + shift) % 26) + baseChar);
            } else {
                result[i] = currentChar; // Non-alphabetic characters remain unchanged
            }
        }

        return new String(result);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the text to encrypt:");
        String plaintext = scanner.nextLine();

        System.out.println("Enter the shift value (an integer):");
        if (scanner.hasNextInt()) {
            int shiftValue = scanner.nextInt();
            String cipheredText = caesarEncrypt(plaintext, shiftValue);

            System.out.println("Original text: " + plaintext);
            System.out.println("Ciphered text: " + cipheredText);
        } else {
            System.out.println("Invalid shift value. Please enter a valid integer.");
        }

        scanner.close();
    }
}
