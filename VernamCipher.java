import java.util.Scanner;

public class VernamCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input plaintext
        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine().toUpperCase(); // Convert to uppercase for simplicity

        // Input key
        System.out.print("Enter the key (same length as plaintext): ");
        String key = scanner.nextLine().toUpperCase(); // Convert to uppercase for simplicity

        // Check if plaintext and key have the same length
        if (plaintext.length() != key.length()) {
            System.out.println("Error: The length of the plaintext and key must be the same.");
            System.out.println("Please enter a key with the same length as the plaintext.");
            return;
        }

        // Encrypt the plaintext
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + ciphertext);

        // Decrypt the ciphertext
        String decryptedText = decrypt(ciphertext, key);
        System.out.println("\nDecrypted Text: " + decryptedText);
    }

    // Method to encrypt the plaintext using the key
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char encryptedChar = (char) ((plaintext.charAt(i) - 'A' + key.charAt(i) - 'A') % 26 + 'A');
            ciphertext.append(encryptedChar);
        }

        return ciphertext.toString();
    }

    // Method to decrypt the ciphertext using the key
    public static String decrypt(String ciphertext, String key) {
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i++) {
            char decryptedChar = (char) ((ciphertext.charAt(i) - key.charAt(i) + 26) % 26 + 'A');
            decryptedText.append(decryptedChar);
        }

        return decryptedText.toString();
    }
}
