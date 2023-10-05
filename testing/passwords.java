import java.util.Scanner;
import java.util.stream.IntStream;

public class passwords{
    public static void main(String[] args) {
        //get two things from the user: one password (string) and one key (integer)
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a password: ");
        String password = input.nextLine();
        System.out.print("Enter a key: ");
        int key = input.nextInt();
        System.out.println("Encrypt / decrypt? (0 / 1)");
        int eOrD = input.nextInt();
        boolean encrypt;
        if (eOrD == 0) {
            encrypt = true;
        }
        else {
            encrypt = false;
        }

        long keyInBaseN = convertToBaseN(key, 2);
        String keyAsString = "" + keyInBaseN;
        int[] encryptionSteps = new int[keyAsString.length()];
        if (encrypt) {
            for (int i = 0; i < encryptionSteps.length; i++) {
                encryptionSteps[i] = keyAsString.charAt(i) - '0';

            }
        }
        else {
            //same as above, but reverse
            for (int i = 0; i < encryptionSteps.length; i++) {
                encryptionSteps[encryptionSteps.length - i - 1] = keyAsString.charAt(i) - '0';
            }
        }
        int key0 = IntStream.of(encryptionSteps).sum();
        int key1 = key % 26;
        int key2 = (key - key1) / 26;
        while (!areCoprime(key1, 26)) {
            key1 = (key1 + key0) % 26;
        }
        for (int i = 0; i < encryptionSteps.length; i++) {
            password = shiftString(password, encrypt);
            if (encryptionSteps[i] == 0) {
                password = caesarCipher(password, key1, encrypt);
            }
            else if (encryptionSteps[i] == 1) {
                password = affineCipher(password, key1, key2, encrypt);
            }
        }
        System.out.println(password);
        
    }
    public static String caesarCipher(String password, int key, boolean encrypt) {
        String finalPassword = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < password.length(); i++) {
            if (alphabet.indexOf(Character.toLowerCase(password.charAt(i))) >= 0) {
                int charCase;
                if (Character.isUpperCase(password.charAt(i))) {
                    charCase = 1;
                }
                else {
                    charCase = 0;
                }
                char character0 = Character.toLowerCase(password.charAt(i));
                int index = alphabet.indexOf(character0);
                if (encrypt) {
                    index += key;
                    if (index > 25) {
                        index -= 26;
                    }
                }
                else {
                    index -= key;
                    if (index < 0) {
                        index += 26;
                    }
                }
                if (charCase == 1) {
                    finalPassword += Character.toUpperCase(alphabet.charAt(index));
                }
                else {
                    finalPassword += Character.toLowerCase(alphabet.charAt(index));
                }
            }
            else {
                finalPassword += password.charAt(i);
            }
        }
        return finalPassword;
    }
    public static String affineCipher(String password, int key1, int key2, boolean encrypt) {
        String finalPassword = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < password.length(); i++) {
            if (alphabet.indexOf(Character.toLowerCase(password.charAt(i))) >= 0) {
                int charCase;
                if (Character.isUpperCase(password.charAt(i))) {
                    charCase = 1;
                }
                else {
                    charCase = 0;
                }
                char character0 = Character.toLowerCase(password.charAt(i));
                int index = alphabet.indexOf(character0);
                if (encrypt) {
                    index = index * key1 + key2;
                    index %= 26;
                }
                else {
                    int aInverse = modInverse(key1, 26);

                    // Decrypt using the formula x = (a_inverse * (y - b)) % 26
                    index = (aInverse * (index - key2 + 26)) % 26;
                    while (index < 0) {
                        index += 26;
                    }
                }
                if (charCase == 1) {
                    finalPassword += Character.toUpperCase(alphabet.charAt(index));
                }
                else {
                    finalPassword += Character.toLowerCase(alphabet.charAt(index));
                }
            }
            else {
                finalPassword += password.charAt(i);
            }
        }
        return finalPassword;
    }
    public static int modInverse(int a, int m) {
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return -1; // No modular inverse exists
    }
    public static long convertToBaseN(long number, int targetBase) {
        if (number == 0) {
            return 0;
        }

        StringBuilder result = new StringBuilder();

        while (number > 0) {
            long remainder = number % targetBase;
            result.insert(0, remainder);
            number /= targetBase;
        }

        return Long.parseLong(result.toString());
    }

    // Function to check if two numbers are coprime
    public static int gcd(int a, int b) {
        // stores minimum(a, b)
        int i;
        if (a < b)
            i = a;
        else
            i = b;
 
        // take a loop iterating through smaller number to 1
        for (i = i; i > 1; i--) {
 
            // check if the current value of i divides both
            // numbers with remainder 0 if yes, then i is
            // the GCD of a and b
            if (a % i == 0 && b % i == 0)
                return i;
        }
 
        // if there are no common factors for a and b other
        // than 1, then GCD of a and b is 1
        return 1;
    }
    public static boolean areCoprime(int a, int b) {
        return gcd(a, b) == 1;
        

    }
    public static String shiftString(String str, boolean forwards) {
        if (forwards) {
            return str.charAt(str.length() - 1) + str.substring(0, str.length() - 1);
        }
        else {
            return str.substring(1, str.length()) + str.charAt(0);
        }
        
    }
}