import java.util.Scanner;
public class U1_1_12_2015 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int a = input.nextInt();
        int b = input.nextInt();
        int c = input.nextInt();
        int d = input.nextInt();
        
        int[] painted = new int[100];
        for (int i = a + 1; i <= b; i++) {
            painted[i] = 1;
        }
        for (int i = c + 1; i <= d; i++) {
            painted[i] = 1;
        }
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            if (painted[i] == 1) {
                sum++;
            }
        }
        System.out.println(sum);
    }
}