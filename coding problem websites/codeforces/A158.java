import java.util.Scanner;
public class A158 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = input.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = input.nextInt();
        }
        int kScore = a[k - 1];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] >= kScore && a[i] != 0) {
                count++;
            }
        }
        
        input.close();
        System.out.println(count);
    }

}
