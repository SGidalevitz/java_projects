import java.util.*;
public class CreatingStrings {
    static ArrayList<String> perms = new ArrayList<String>();
    static int[] charCount = new int[26];
    static String s;
    static void search(String curr) {
        if (curr.length() == s.length()) {
            perms.add(curr);
            return;
        }
        for (int i = 0; i < 26; i++) {
            if (charCount[i] > 0) {
                charCount[i]--;
                search(curr + (char)('a' + i));
                charCount[i]++;
            }
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        s = input.nextLine();
        for (int i = 0; i < s.length(); i++) {
            charCount[(int)(s.charAt(i) - 'a')]++;
        }
        search("");
        System.out.println(perms.size());
        for (String perm : perms) {
            System.out.println(perm);
        }
    }
    public static int search(String in, ArrayList<String> allPerms) {
        return 5;
    }

    
}