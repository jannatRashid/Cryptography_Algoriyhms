import java.util.*;

public class PlayfairCipher {
    private char[][] keySquare;

    // Constructor to initialize the key square
    public PlayfairCipher(String key) {
        key = key.toUpperCase().replaceAll("[^A-Z]", "");
        key = key.replace("J", "I");

        // Initialize the key square
        keySquare = new char[5][5];
        Set<Character> set = new HashSet<>();
        int row = 0, col = 0;

        // Fill the key square with the key
        for (char c : key.toCharArray()) {
            if (!set.contains(c)) {
                keySquare[row][col] = c;
                set.add(c);
                col++;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
        }

        // Fill the key square with remaining characters
        char letter = 'A';
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (keySquare[r][c] == 0) {
                    while (set.contains(letter) || letter == 'J') {
                        letter++;
                    }
                    keySquare[r][c] = letter;
                    set.add(letter);
                    letter++;
                }
            }
        }
    }

    // Encrypt the plaintext
    public String encrypt(String plaintext) {
        plaintext = prepareText(plaintext);
        StringBuilder ciphertext = new StringBuilder();

        // Encrypt digraphs
        for (int i = 0; i < plaintext.length(); i += 2) {
            char a = plaintext.charAt(i);
            char b = (i + 1 < plaintext.length()) ? plaintext.charAt(i + 1) : 'X';

            // If the two letters are the same, insert a bogus letter
            if (a == b) {
                char bogus = (a != 'Z') ? 'Z' : 'X';
                b = bogus;
                plaintext = plaintext.substring(0, i + 1) + bogus + plaintext.substring(i + 1);
                // Increment i to skip the bogus letter in the next iteration
                i++;
            } else if (i == plaintext.length() - 3 && plaintext.charAt(i + 2) == 'X') {
                // If there are two letters left and the last one is 'X', add a bogus letter
                char bogus = (plaintext.charAt(i + 1) != 'Z') ? 'Z' : 'X';
                plaintext = plaintext.substring(0, i + 2) + bogus;
            }

            ciphertext.append(encryptPair(a, b));
        }

        return ciphertext.toString();
    }



    // Decrypt the ciphertext
    public String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();

        // Decrypt digraphs
        for (int i = 0; i < ciphertext.length(); i += 2) {
            char a = ciphertext.charAt(i);
            char b = ciphertext.charAt(i + 1);

            plaintext.append(decryptPair(a, b));
        }

        return plaintext.toString();
    }

    // Encrypt a pair of characters
    private String encryptPair(char a, char b) {
        int[] posA = findPosition(a);
        int[] posB = findPosition(b);

        StringBuilder sb = new StringBuilder();
        // Same row
        if (posA[0] == posB[0]) {
            sb.append(keySquare[posA[0]][(posA[1] + 1) % 5]);
            sb.append(keySquare[posB[0]][(posB[1] + 1) % 5]);
        }
        // Same column
        else if (posA[1] == posB[1]) {
            sb.append(keySquare[(posA[0] + 1) % 5][posA[1]]);
            sb.append(keySquare[(posB[0] + 1) % 5][posB[1]]);
        }
        // Forming a rectangle
        else {
            sb.append(keySquare[posA[0]][posB[1]]);
            sb.append(keySquare[posB[0]][posA[1]]);
        }
        return sb.toString();
    }

    // Decrypt a pair of characters
    private String decryptPair(char a, char b) {
        int[] posA = findPosition(a);
        int[] posB = findPosition(b);

        StringBuilder sb = new StringBuilder();
        // Same row
        if (posA[0] == posB[0]) {
            sb.append(keySquare[posA[0]][(posA[1] + 4) % 5]);
            sb.append(keySquare[posB[0]][(posB[1] + 4) % 5]);
        }
        // Same column
        else if (posA[1] == posB[1]) {
            sb.append(keySquare[(posA[0] + 4) % 5][posA[1]]);
            sb.append(keySquare[(posB[0] + 4) % 5][posB[1]]);
        }
        // Forming a rectangle
        else {
            sb.append(keySquare[posA[0]][posB[1]]);
            sb.append(keySquare[posB[0]][posA[1]]);
        }
        return sb.toString();
    }

    // Prepare the text for encryption or decryption
    private String prepareText(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "");
        text = text.replace("J", "I");
        StringBuilder sb = new StringBuilder(text);
        for (int i = 1; i < sb.length(); i += 2) {
            if (sb.charAt(i) == sb.charAt(i - 1)) {
                sb.insert(i, 'X');
            }
        }
        if (sb.length() % 2 != 0) {
            sb.append('X');
        }
        return sb.toString();
    }

    // Find the position of a character in the key square
    private int[] findPosition(char c) {
        int[] pos = new int[2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (keySquare[i][j] == c) {
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
            }
        }
        return pos;
    }

    // Print the key square
    public void printKeySquare() {
        System.out.println("Key Square (5x5):");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(keySquare[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the key:");
        String key = scanner.nextLine();
        System.out.println("Enter the plaintext:");
        String plaintext = scanner.nextLine();

        PlayfairCipher cipher = new PlayfairCipher(key);
        String ciphertext = cipher.encrypt(plaintext);

        System.out.println("Encrypted Text:");
        System.out.println(ciphertext);

        String decryptedText = cipher.decrypt(ciphertext);
        System.out.println("Decrypted Text:");
        System.out.println(decryptedText);

        cipher.printKeySquare();
    }
}
