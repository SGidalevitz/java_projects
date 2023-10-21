import java.util.Scanner;
public class A1886 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for (int t = 0; t < T; t++) {
            System.out.println(solve(input.nextInt()));
        }
    }
    public static String solve(int num) {
        if (num < 7) {
            return "NO";
        }
        for (int i = 1; i < num - 3; i++) {
            if (i % 3 == 0) {
                continue;
            }
            for (int j = 1; j < num - i; j++) {
                if ((num - i - j != i && i != j && j != num - i - j) && ((num - i - j) % 3 != 0 && i % 3 != 0 && j % 3 != 0)) {
                    return "YES\n" + (num - i - j) + " " + i + " " + j;
                }
            } 
        }
        return "NO";
        
    }
}