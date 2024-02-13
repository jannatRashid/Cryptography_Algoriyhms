
import java.util.Scanner;

public class VigenereCipher_method2 {
    public static void main(String[] args) {
        System.out.println("Vigenere Cipher _ Method2");
        Scanner scanner = new Scanner(System.in);

        // Define the key
        String key = "PASCAL";

        // Get the plaintext from the user
        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine().toUpperCase();

        // Encrypt the plaintext using the Vigenere Cipher method 2
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0, j = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            if (Character.isLetter(c)) {
                char keyChar = key.charAt(j % key.length());
                int shift = keyChar - 'A';
                char encryptedChar = (char) ((c + shift - 'A') % 26 + 'A');
                encryptedText.append(encryptedChar);
                j++;
            } else {
                encryptedText.append(c);
            }
        }

        // Display the encrypted text
        System.out.println("Encrypted text: " + encryptedText.toString());

        // Decrypt the encrypted text
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0, j = 0; i < encryptedText.length(); i++) {
            char c = encryptedText.charAt(i);
            if (Character.isLetter(c)) {
                char keyChar = key.charAt(j % key.length());
                int shift = keyChar - 'A';
                char decryptedChar = (char) ((c - shift - 'A' + 26) % 26 + 'A');
                decryptedText.append(decryptedChar);
                j++;
            } else {
                decryptedText.append(c);
            }
        }

        // Display the decrypted text
        System.out.println("Decrypted text: " + decryptedText.toString());

        scanner.close();
    }
}
