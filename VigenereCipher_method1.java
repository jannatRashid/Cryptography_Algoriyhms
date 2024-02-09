import java.util.Scanner;
//2nd version
public class VigenereCipher_method1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Define the Vigenere table
        char[][] vigenereTable = new char[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                vigenereTable[i][j] = (char) ((i + j) % 26 + 'A');
            }
        }

        // Define the key
        String key = "LOCK";

        // Print the Vigenere table with headings
        System.out.println("Vigenere Cipher Table:");
        System.out.print("    ");
        for (int i = 0; i < 26; i++) {
            System.out.printf("%-3c", (char) ('A' + i));
        }
        System.out.println();
        for (int i = 0; i < 26; i++) {
            System.out.printf("%-4c", (char) ('A' + i));
            for (int j = 0; j < 26; j++) {
                System.out.printf("%-3c", vigenereTable[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        // Get the plaintext from the user
        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine().toUpperCase();

        // Encrypt the plaintext using the Vigenere Cipher
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0, j = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            if (Character.isLetter(c)) {
                int row = key.charAt(j % key.length()) - 'A';
                int col = c - 'A';
                encryptedText.append(vigenereTable[row][col]);
                j++;
            } else {
                encryptedText.append(c);
            }
        }

        // Display the encrypted text
        System.out.println("Encrypted text: " + encryptedText.toString());

        scanner.close();
    }
}
